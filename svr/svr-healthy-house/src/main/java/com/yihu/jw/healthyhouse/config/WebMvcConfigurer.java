package com.yihu.jw.healthyhouse.config;

import com.yihu.jw.healthyhouse.interceptor.ActivatedInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author HZY
 * @created 2018/10/9 9:24
 */
//@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }

    @Bean
    public ActivatedInterceptor authenticationInterceptor() {
        return new ActivatedInterceptor();
    }



}
