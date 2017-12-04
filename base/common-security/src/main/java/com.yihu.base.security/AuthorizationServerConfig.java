package com.yihu.base.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by chenweida on 2017/12/4.
 */
@Configuration
@EnableAuthorizationServer  //开启授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
}
