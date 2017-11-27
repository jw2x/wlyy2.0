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
@ComponentScan("com.yihu.jw.controller.**")
public class SwaggerConfig {
    public static final String base_API = "base";
    public static final String wlyy_API = "wlyy";
    public static final String login_API = "login";


    @Bean
    public Docket baseAPI() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(base_API)
                .useDefaultResponseMessages(false)
                .apiInfo(baseApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.base"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo baseApiInfo() {
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


    @Bean
    public Docket wlyyAPI() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(wlyy_API)
                .useDefaultResponseMessages(false)
                .apiInfo(wlyyApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.wlyy"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo wlyyApiInfo() {
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


    @Bean
    public Docket loginAPI() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(login_API)
                .useDefaultResponseMessages(false)
                .apiInfo(loginApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.login"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo loginApiInfo() {
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