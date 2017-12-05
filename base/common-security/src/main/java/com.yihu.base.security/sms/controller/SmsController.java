package com.yihu.base.security.sms.controller;

import com.yihu.base.security.properties.SecurityProperties;
import com.yihu.base.security.sms.process.SmsValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenweida on 2017/12/5.
 */
@RestController
public class SmsController {
    @Autowired
    private SmsValidateCodeProcessor smsValidateCodeProcessor;

    /**
     * 创建验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping(SecurityProperties.mobileSendSms)
    public void createCode(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
        //获取手机号
        String mobile = request.getParameter(SecurityProperties.mobileLoginAccountKey);
        //发送短信验证码并且保存到redis中
        smsValidateCodeProcessor.create(new ServletWebRequest(request, response));

    }
}
