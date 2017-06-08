package com.yihu.jw.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chenweida on 2017/5/31.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 支持跨域问题
     * 资料:http://blog.csdn.net/dalangzhonghangxing/article/details/51994812
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
