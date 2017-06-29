package com.yihu.jw.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by chenweida on 2017/6/8.
 */
@EnableJpaAuditing
@SpringBootApplication
public class SvrMamageApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SvrMamageApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SvrMamageApp.class);
    }
}
