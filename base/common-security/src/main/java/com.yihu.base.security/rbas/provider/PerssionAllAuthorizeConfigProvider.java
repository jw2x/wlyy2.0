package com.yihu.base.security.rbas.provider;

import com.yihu.base.security.properties.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/12/5.
 * 允许通过的路径
 */
@Component
@Order(Integer.MIN_VALUE)
public class PerssionAllAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public void condfig(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config
                .antMatchers(
                        SecurityProperties.formLogin,
                        SecurityProperties.formLoginPage,
                        SecurityProperties.mobileLogin,
                        SecurityProperties.mobileSendSms
                ).permitAll();
    }
}
