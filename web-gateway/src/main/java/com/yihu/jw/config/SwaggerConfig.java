package com.yihu.jw.config;

import com.yihu.jw.commnon.base.base.BaseContants;
import com.yihu.jw.commnon.base.wx.WechatContants;
import com.yihu.jw.commnon.wlyy.AgreementContants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
    public static final String gateway_API = "gateway";


    @Bean
    public Docket gatewayAPI() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(gateway_API)
                .useDefaultResponseMessages(false)
                .apiInfo(gatewayApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo gatewayApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基卫2.0API",
                "基卫2.0API，提供基础卫生相关服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

}