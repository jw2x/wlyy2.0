package com.yihu.jw.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 *
 * Created by progr1mmer on 2018/8/29.
 */
@Configuration
@EnableAuthorizationServer
public class WlyyAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {
}
