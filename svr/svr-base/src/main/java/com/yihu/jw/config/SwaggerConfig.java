package com.yihu.jw.config;

import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.WechatRequestMapping;
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
@ComponentScan("com.yihu.jw.**")
public class SwaggerConfig {
    public static final String Base_API = "Base";


    @Bean
    public Docket jwBaseAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Base_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(
                         regex("/"+ BaseRequestMapping.api_base_common +"/.*")
                        ,regex("/"+ WechatRequestMapping.api_common+"/.*")
                ))
                .build()
                .apiInfo(jwBaseApiInfo());
    }

    private ApiInfo jwBaseApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基卫2.0API",
                "基卫2.0基础扩展API，提供基卫2.0基础扩展相关服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }
}