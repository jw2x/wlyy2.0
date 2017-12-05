package com.yihu.base.security.rbas.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by chenweida on 2017/12/5.
 * 授权路径
 */
@Component
public class AuthorizeConfigProviderManager {
    @Autowired
    Set<AuthorizeConfigProvider> authorizeConfigProviders;

   public void condfig(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
            authorizeConfigProvider.condfig(config);
        }
    }
}
