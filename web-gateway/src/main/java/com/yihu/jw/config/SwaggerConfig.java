package com.yihu.jw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ComponentScan("com.yihu.jw.controller.**")
public class SwaggerConfig {
    public static final String base_API = "base";
    public static final String wlyy_API = "wlyy";
    public static final String login_API = "login";
    public static final String iot_API = "iot";

    final String userAgentJson = "{\"id\":int,\"uid\":string,\"openid\":string,\"token\":string,\"lastUid\":string,\"platform\":int}";


    @Bean
    public Docket baseAPI() {
        List<Parameter> pars = addToken();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(base_API)
                .useDefaultResponseMessages(false)
                .apiInfo(baseApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.base"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
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

    private List<Parameter> addToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder userAgentPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        userAgentPar.name("Authorization").description("\"Authorization\":\"bearer 5fe6b2c3-f69c-4ddc-a36a-367cdf9479a3\"").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("").build();
        tokenPar.name("accesstoken").description("accesstoken").modelRef(new ModelRef("string")).parameterType("header").required(false).defaultValue("").build();
        pars.add(tokenPar.build());
        pars.add(userAgentPar.build());
        return pars;
    }

    @Bean
    public Docket wlyyAPI() {
        List<Parameter> pars = addToken();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(wlyy_API)
                .useDefaultResponseMessages(false)
                .apiInfo(wlyyApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.wlyy"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
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
        List<Parameter> pars = addToken();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(login_API)
                .useDefaultResponseMessages(false)
                .apiInfo(loginApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.login"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
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

    @Bean
    public Docket iotAPI() {
        List<Parameter> pars = addToken();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(iot_API)
                .useDefaultResponseMessages(false)
                .apiInfo(iotApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yihu.jw.controller.iot"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo iotApiInfo() {
        ApiInfo apiInfo = new ApiInfo("基卫2.0API",
                "基卫2.0API，提供物联网相关服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return apiInfo;
    }

    @Bean
    public Docket allAPL() {
        List<Parameter> pars = addToken();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("all")
                .useDefaultResponseMessages(false)
                .apiInfo(wlyyApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(or(
                        regex("/.*")
                ))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo appApiInfo() {
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