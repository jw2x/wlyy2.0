package com.yihu.jw.business.login.contorller;

//import com.yihu.base.security.properties.SecurityProperties;
//import com.yihu.base.security.sms.mobile.MobileCheck;
//import com.yihu.base.security.sms.process.SmsValidateCodeProcessor;
import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.business.login.service.LoginService;
import com.yihu.jw.business.user.dao.EmployDao;
import com.yihu.jw.business.user.service.EmployService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.fegin.common.security.LoginSmsFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.common.base.BaseEnvelop;
import com.yihu.jw.rm.base.BaseLoginRequestMapping;
import com.yihu.jw.rm.base.BaseUserRequestMapping;
import com.yihu.jw.util.common.ConvertToSpellUtils;
import com.yihu.jw.util.security.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

/**
 * Created by 刘文彬 on 2018/4/11.
 */
@RestController
@RequestMapping(BaseLoginRequestMapping.api_common)
@Api(description = "注册账户（医生端、微信端、用户端）")
public class LoginController extends EnvelopRestController {

    @Autowired
    private LoginService loginService;


    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_checkoutInfo, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册校验信息", notes = "注册校验姓名、身份证、医保卡号信息")
    public BaseEnvelop checkoutInfo(@ApiParam(name = "ssc", value = "医保卡号", required = true) @RequestParam(value = "ssc", required = true) String ssc,
                                    @ApiParam(name = "idcard", value = "身份证", required = true) @RequestParam(value = "idcard", required = true) String idcard) throws ApiException{

        //校验姓名、身份证以及医保卡号信息是否正确
//        throw new IOException();
        return null;
    }

    /**
     * 注册账号-提交
     * @param mobilePhone
     * @param saasId
     * @param type
     * @param captcha
     * @param name
     * @param password
     * @param idcard
     * @param ssc
     * @return
     */
    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_accountSub, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "注册账号", notes = "注册账号")
    public Envelop register(
            @ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = true) @RequestParam(value = "mobilePhone", required = true) String mobilePhone,
            @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
            @ApiParam(name = "type", value = "1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录", required = true) @RequestParam(value = "type", required = true) int type,
            @ApiParam(name = "captcha", value = "短信验证码", required = true) @RequestParam(value = "captcha", required = true) String captcha,
            @ApiParam(name = "name", value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
            @ApiParam(name = "password", value = "账户密码", required = true) @RequestParam(value = "password", required = true) String password,
            @ApiParam(name = "idcard", value = "身份证", required = true) @RequestParam(value = "idcard", required = true) String idcard,
            @ApiParam(name = "ssc", value = "医保卡号", required = true) @RequestParam(value = "ssc", required = true) String ssc) throws Exception{
        //判断验证码是否正确
        Envelop envelop = new Envelop();//smsService.checkSms(mobilePhone,saasId,type,captcha);
        if(true){//if(envelop.getStatus()==200){
            return loginService.register(mobilePhone,password,saasId,name,idcard,ssc);
        }else{
            return envelop;
        }
    }

    @PostMapping(value = BaseLoginRequestMapping.BaseLoginAccount.api_login, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Envelop login(@ApiParam(name = "mobilePhone", value = "电话号码（账号）", required = false) @RequestParam(value = "mobilePhone", required = false) String mobilePhone,
                      @ApiParam(name = "password", value = "password", required = false) @RequestParam(value = "password", required = false) String password,
                      @ApiParam(name = "saasId", value = "saasID", required = true) @RequestParam(value = "saasId", required = true) String saasId,
                         @ApiParam(name = "captcha", value = "短信验证码", required = false) @RequestParam(value = "captcha", required = false) String captcha){
       try{
           return loginService.login(mobilePhone,password,saasId,captcha);
       }catch (ApiException e){
           e.printStackTrace();
           return Envelop.getError(e.getMessage(),e.getErrorCode());
       }catch (Exception e){
           e.printStackTrace();
           return Envelop.getError("",100);
       }
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
