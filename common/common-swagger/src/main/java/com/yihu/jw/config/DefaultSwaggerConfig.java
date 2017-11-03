package com.yihu.jw.config;

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
public class DefaultSwaggerConfig {
    public static final String default_API = "default";

    @Bean
    public Docket defaultAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(default_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/.*")
                ))
                .build()
                .apiInfo(defaultApiInfo());
    }

    private ApiInfo defaultApiInfo() {
        ApiInfo apiInfo = new ApiInfo("API",
                "API，提供基础服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

}