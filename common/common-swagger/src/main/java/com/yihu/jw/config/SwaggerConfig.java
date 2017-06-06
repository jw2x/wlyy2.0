package com.yihu.jw.config;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.restmodel.wx.WxContants;
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
    public static final String PUBLIC_API = "Default";
    public static final String Base_API = "JwBase";
    public static final String Wlyy_API = "Wlyy";


    @Bean
    public Docket publicAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(PUBLIC_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/patient/.*")
                        , regex("/"+ BaseContants.Function.api_common+"/.*")
                        ,regex("/"+ WxContants.Wechat.api_common+"/.*")
                        ,regex("/"+WxContants.WxAccessToken.api_common+"/.*")
                        ,regex("/"+WxContants.WxMenu.api_common+"/.*")
                        ,regex("/"+WxContants.WxTemplate.api_common+"/.*")
                        ,regex("/"+WxContants.WxGraphicMessage.api_common+"/.*")
                        ))
                .build()
                .apiInfo(publicApiInfo());
    }

    private ApiInfo publicApiInfo() {
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
                .groupName(Wlyy_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(
                        regex("/" + WlyyContants.Agreement.api_common + "/.*")
                        ,regex("/"+WlyyContants.AgreementKpi.api_common+"/.*")
                        ,regex("/"+WlyyContants.AgreementKpiLog.api_common+"/.*")
                        ,regex("/"+WlyyContants.SignFamily.api_common+"/.*")
                ))
                .build()
                .apiInfo(wlyyApiInfo());
    }

    private ApiInfo wlyyApiInfo() {
        ApiInfo wlyyInfo = new ApiInfo("基卫2.0API",
                "基卫2.0API，提供基础卫生相关服务。",
                "1.0",
                "No terms of service",
                "wenfujian@jkzl.com",
                "The Apache License, Version 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );

        return wlyyInfo;
    }


    @Bean
    public Docket jwBaseAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Base_API)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(
                         regex("/"+ BaseContants.Function.api_common+"/.*")
                        ,regex("/"+ BaseContants.Saas.api_common+"/.*")
                        ,regex("/"+ BaseContants.Module.api_common+"/.*")

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