package com.yihu.base.security;

import com.yihu.base.security.sms.sender.DefaultSmsCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/12/4.
 */
@Configuration
public class SercurityConfig {
    private Logger logger = LoggerFactory.getLogger(SercurityConfig.class);

    @Autowired(required = false)
    List<AuthenticationTrustResolver> trustResolvers = new ArrayList<>();

    @Autowired(required = false)
    List<PermissionEvaluator> permissionEvaluators = new ArrayList<>();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultSmsCodeSender defaultSmsCodeSender() {
        logger.info("使用默认的短信发送DefaultSmsCodeSender");
        return new DefaultSmsCodeSender();
    }

}
