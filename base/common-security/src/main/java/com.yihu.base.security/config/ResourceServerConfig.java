package com.yihu.base.security.config;

import com.yihu.base.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by chenweida on 2017/12/4.
 */

@Configuration
@EnableResourceServer  //开启资源服务器
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private OAuth2AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore redisTokenStore;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        //这是账号密码登陆
        http
                .formLogin()//设置验证码 账号密码登陆
                .loginPage(SecurityProperties.formLoginPage)
                .loginProcessingUrl(SecurityProperties.formLogin)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityProperties.formLogin,
                        SecurityProperties.formLoginPage,
                        SecurityProperties.mobileLogin).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.
                authenticationManager(authenticationManager).
                tokenStore(redisTokenStore);
    }
}
