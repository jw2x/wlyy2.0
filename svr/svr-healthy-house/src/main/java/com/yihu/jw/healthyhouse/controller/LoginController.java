package com.yihu.jw.healthyhouse.controller;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.cache.WlyyRedisVerifyCodeService;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.service.user.LoginService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.util.security.RandomValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;

/**
 * @author HZY
 * @created 2018/9/18 19:55
 */
@Api(value = "LoginController", description = "登录管理", tags = {"1登录及验证"})
@RestController
public class LoginController extends EnvelopRestEndpoint {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;

    /******************************************    用户相关    **********************************/

    @ApiOperation(value = "发送短信验证码")
    @GetMapping(value = "/captcha/send")
    public ResponseEntity<HashMap> captcha(
            @ApiParam(name = "clientId", value = "应用id",defaultValue = "EwC0iRSrcS", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "msgType", value = "消息类型（login：登录验证，checkPhone：验证安全手机，resetPhone：重设安全手机", required = true)@RequestParam(required = true, name = "msgType") String msgType,
            @ApiParam(name = "username", value = "手机账号", required = true)@RequestParam(required = true, name = "username") String username ) throws  Exception{
        if (StringUtils.isEmpty(clientId)) {
            throw new InvalidRequestException("clientId");
        }
        if (StringUtils.isEmpty(username)){
            throw new InvalidRequestException("username");
        }
        //验证请求间隔超时，防止频繁获取验证码
//        if (!wlyyRedisVerifyCodeService.isIntervalTimeout(clientId, username)) {
//            throw new IllegalAccessException("SMS request frequency is too fast");
//        }
        //发送短信获取验证码
        ResponseEntity<HashMap> result = loginService.sendDemoSms(clientId,msgType,username);
        return result;

    }

    @PostMapping("/captcha/check")
    @ApiOperation(value = "验证短信验证码")
    public Envelop checkCaptcha(
            HttpServletRequest request,
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "username", value = "登录账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "captcha", value = "短信验证码", required = true)@RequestParam(required = true, name = "captcha") String captcha) throws ManageException, ParseException {
        if (wlyyRedisVerifyCodeService.verification(clientId, username, captcha)) {
            return ObjEnvelop.getSuccess("验证码正确");
        } else {
            return ObjEnvelop.getError("验证码错误");
        }
    }

    @PostMapping("/mobile/login")
    @ApiOperation(value = "【普通用户】-手机登录注册")
    public ObjEnvelop<User> mobileLogin(
            HttpServletRequest request,
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "captcha", value = "短信验证码", required = true)@RequestParam(required = true, name = "captcha") String captcha) throws ManageException, ParseException {
        if (wlyyRedisVerifyCodeService.verification(clientId, username, captcha)) {
            User user = loginService.phoneLogin(request,username);
//            ObjEnvelop envelop = new ObjEnvelop();
//            envelop.setStatus(200);
//            envelop.setMessage("登录成功");
//            envelop.setObj(user);
            return success(user);

        } else {
            return ObjEnvelop.getError("验证码错误");
        }
    }

    @PostMapping("/ijk/login")
    @ApiOperation(value = "【普通用户】-i健康用户登陆")
    public ObjEnvelop ijkLogin(
            HttpServletRequest request,
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "password", value = "密码", required = true)@RequestParam(required = true, name = "password") String password) throws ManageException {
        User user = loginService.iJklogin(request,clientId,username, password);
        if (user !=null) {
            ObjEnvelop envelop = new ObjEnvelop();
            envelop.setStatus(200);
            envelop.setMessage("登录成功");
            envelop.setObj(user);
            return envelop;
        }else {
            return ObjEnvelop.getError("登录失败");
        }
    }

    @PostMapping("/loginout")
    @ApiOperation(value = "登出")
    public Envelop loginout(
            HttpServletRequest request,
            @ApiParam(name = "userCode", value = "用户code", required = true)@RequestParam(required = true, name = "userCode") String userCode) {
        try {
            //修改用户状态  离线
           return ObjEnvelop.getSuccess("登出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ObjEnvelop.getError("登出成功:" + e.getMessage(), -1);
        }
    }


    /***************************  管理员相关 **************************************/

    @PostMapping("/mobile/manage/login")
    @ApiOperation(value = "【管理员】-手机验证登录")
    public ObjEnvelop administratorMobileLogin(
            HttpServletRequest request,
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "captcha", value = "短信验证码", required = true)@RequestParam(required = true, name = "captcha") String captcha) throws ManageException, ParseException {
        if (wlyyRedisVerifyCodeService.verification(clientId, username, captcha)) {
            User user = loginService.managerPhoneLogin(request,username);
            return ObjEnvelop.getSuccess("登录成功",user);
        } else {
            return ObjEnvelop.getError("验证码错误");
        }
    }

    @PostMapping("/manage/login")
    @ApiOperation(value = "【管理员】-用户账号登陆")
    public ObjEnvelop administratorLogin(
            HttpServletRequest request,
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "password", value = "密码", required = true)@RequestParam(required = true, name = "password") String password) throws ManageException {
        User user = loginService.managerLogin(request,clientId,username, password);
        if (user !=null) {
            return ObjEnvelop.getSuccess("登录成功",user);
        }else {
            return ObjEnvelop.getError("登录失败");
        }
    }

    @GetMapping(value = "/getRandomImageCode")
    @ApiOperation(value = "修改密码时生成图形验证码",notes = "修改密码时生成图形验证码")
    public Envelop getImageCode (HttpServletRequest request, HttpServletResponse response)throws Exception{
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCode randomValidateCode = new RandomValidateCode();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
            return ObjEnvelop.getSuccess("获取验证码成功");
        } catch (Exception e) {
            return ObjEnvelop.getError("获取验证码失败");
        }
    }

    @PostMapping(value = "/checkRandomImageCode")
    @ApiOperation(value = "检验图片验证码",notes = "检验图片验证码")
    public Envelop checkImageCode(@ApiParam(name = "code",value = "输入的验证码")@RequestParam(value = "code",required = true)String code,
                                     HttpServletRequest request){
        if (StringUtils.isEmpty(code)){
            return ObjEnvelop.getError("请输入验证码！");
        }
        String codeRescource = String.valueOf(request.getSession().getAttribute(RandomValidateCode.RANDOMCODEKEY));
        if (code.toLowerCase().equals(codeRescource.toLowerCase())){
            request.getSession().removeAttribute(RandomValidateCode.RANDOMCODEKEY);
            return ObjEnvelop.getSuccess("验证码正确！");
        }else {
            return ObjEnvelop.getError("验证码错误！");
        }
    }


}
