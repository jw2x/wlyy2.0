package com.yihu.jw.controller.base.login;

import com.yihu.base.security.properties.SecurityProperties;
import com.yihu.base.security.sms.mobile.MobileCheck;
import com.yihu.base.security.sms.process.SmsValidateCodeProcessor;
import com.yihu.jw.common.base.base.BaseContants;
import com.yihu.jw.fegin.base.base.FunctionFeign;
import com.yihu.jw.fegin.base.login.LoginFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 刘文彬 on 2018/4/20.
 */
@RestController
@RequestMapping(BaseLoginRequestMapping.api_gateway_common)
@Api(value = "登录模块", description = "登录模块")
public class LoginContorller {

    @Autowired
    private LoginFeign loginFeign;
    @Autowired
    private MobileCheck mobileCheck;
    @Autowired
    private SmsValidateCodeProcessor smsValidateCodeProcessor;

    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_checkoutInfo, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册校验信息", notes = "注册校验姓名、身份证、医保卡号信息")
    public Envelop checkoutInfo(){

        return loginFeign.checkoutInfo();
    }

    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_accountSub, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册账号", notes = "注册校验姓名、身份证、医保卡号信息")
    public Envelop register(
            @ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = true) @RequestParam(value = "mobilePhone", required = true) String mobilePhone,
            @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
            @ApiParam(name = "type", value = "1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录", required = true) @RequestParam(value = "type", required = true) int type,
            @ApiParam(name = "captcha", value = "短信验证码", required = true) @RequestParam(value = "captcha", required = true) String captcha,
            @ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
            @ApiParam(name = "password", value = "账户密码", required = true) @RequestParam(value = "password", required = true) String password,
            @ApiParam(name = "idcard", value = "身份证", required = true) @RequestParam(value = "idcard", required = true) String idcard,
            @ApiParam(name = "ssc", value = "医保卡号", required = true) @RequestParam(value = "ssc", required = true) String ssc) {

        return loginFeign.register(mobilePhone,saasId,type,captcha,name,password,idcard,ssc);
    }

    @PostMapping(BaseLoginRequestMapping.BaseLoginAccount.mobileSendSms)
    @ApiOperation(value = "发送短信登录的验证码", notes = "不走校验框架")
    public BaseEnvelop createCode(@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam(value = "mobile", required = true) String mobile,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        //验证手机号是否正确
        String[] mobileSaas = mobile.split(",");
        if (!mobileCheck.checkMobile(mobileSaas[0])) {
            return BaseEnvelop.getError("手机格式错误！");
        } else {
            //发送短信验证码并且保存到redis中
            smsValidateCodeProcessor.create(new ServletWebRequest(request, response));
            return BaseEnvelop.getSuccess("发送成功！");
        }
    }

    @PostMapping(BaseLoginRequestMapping.BaseLoginAccount.api_login)
    public Envelop login(@ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = false) @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
                       @ApiParam(name = "password", value = "password", required = false) @RequestParam(value = "password", required = false) String password,
                       @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
                       @ApiParam(name = "captcha", value = "短信验证码", required = false) @RequestParam(value = "captcha", required = false) String captcha){
        return loginFeign.login(mobilePhone,password,saasId,captcha);
    }
//    @PostMapping("/logout")
//    public Envelop logout(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "*/*");
//        headers.add("Cache-Control", "no-cache");
//        RestTemplate restTemplate = new RestTemplate();
//        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
//        //设置http请求实体
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//        restTemplate.postForObject("http://localhost:8088/logout", requestEntity, String.class);
//        return new Envelop();
//    }
}
