package com.yihu.jw.config;

import com.yihu.jw.version.JWRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by chenweida on 2017/6/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new JWRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);//顺序是第一
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }
}
