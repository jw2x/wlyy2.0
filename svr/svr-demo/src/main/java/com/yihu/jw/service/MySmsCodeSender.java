package com.yihu.jw.service;

import com.yihu.base.security.sms.sender.SmsCodeSender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/12/8.
 */
@Service
@Primary
public class MySmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("发送号码:"+mobile);
        System.out.println("发送验证码:"+code);
    }
}
