/**
 *
 */
package com.yihu.base.security.sms.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenweida
 *         默认的短信发送 没有具体实现
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    private Logger logger = LoggerFactory.getLogger(DefaultSmsCodeSender.class);

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
     */
    @Override
    public void send(String mobile, String code) {
        logger.info("向手机" + mobile + "发送短信验证码" + code);
    }

}
