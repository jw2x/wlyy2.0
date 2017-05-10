package com.yihu.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ComponentScan("com.yihu.**.controller")
public class SwaggerConfig {
    public static final String LEGACY_API = "Legacy";
    public static final String PUBLIC_API = "Default";

    @Bean
    public Docket publicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(PUBLIC_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(regex("/api.*")))
                .build()
                .apiInfo(publicApiInfo());
    }


    @Bean
    public Docket legacyAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(LEGACY_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/rest/v.*")))
                .build()
                .apiInfo(legacyApiInfo());
    }


    private ApiInfo legacyApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基卫2.0API",
                "基卫2.0API(历史兼容接口)，此部分API因为设计不规范，但已经发布，所以继续提供兼容。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

    private ApiInfo publicApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基卫2.0API",
                "基卫2.0API，提供健康档案服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }


}