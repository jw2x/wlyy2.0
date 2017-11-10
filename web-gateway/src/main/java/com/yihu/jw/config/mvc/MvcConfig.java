package com.yihu.jw.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by chenweida on 2017/6/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    /**
     * 全局异常定义
     * @return
     */
    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
