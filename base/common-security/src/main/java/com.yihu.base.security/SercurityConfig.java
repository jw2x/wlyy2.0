package com.yihu.base.security;

import com.yihu.base.security.rbas.provider.DefaultRbasService;
import com.yihu.base.security.sms.sender.DefaultSmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by chenweida on 2017/12/4.
 */
@Configuration
public class SercurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultSmsCodeSender defaultSmsCodeSender() {
        return new DefaultSmsCodeSender();
    }


    @Bean
    @ConditionalOnMissingBean
    public DefaultRbasService rasService() {
        return new DefaultRbasService();
    }
}
