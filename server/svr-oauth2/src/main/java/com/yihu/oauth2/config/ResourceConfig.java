package com.yihu.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by chenweida on 2017/11/28.
 */

@Configuration
@EnableResourceServer  //开启资源服务器
public class ResourceConfig {
}
