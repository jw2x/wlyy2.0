package com.yihu.jw.manage.config;

import com.yihu.jw.manage.interceptors.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by chenweida on 2017/6/8.
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserInterceptor userInterceptor;

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

    /**
     * 判断是否登陆的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("/**");
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
