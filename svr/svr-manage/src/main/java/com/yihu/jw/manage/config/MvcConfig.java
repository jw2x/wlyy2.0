package com.yihu.jw.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chenweida on 2017/6/8.
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    //会在默认的基础上增加/bootstrap/**映射到classpath:/bootstrap/，
    // 不会影响默认的方式，可以同时使用。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/webapp/html/");
        registry
                .addResourceHandler("/demo/**")
                .addResourceLocations("classpath:/webapp/hplus/");
        registry
                .addResourceHandler("/common/**")
                .addResourceLocations("classpath:/webapp/common/");
    }
}
