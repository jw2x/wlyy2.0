package com.yihu.jw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by chenweida on 2017/11/29.
 */
@Configuration
@EnableAuthorizationServer  //开启授权服务器
@EnableResourceServer  //开启资源服务器
public class Oauth2Config {

}
