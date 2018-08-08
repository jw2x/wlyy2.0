package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by chenweida on 2017/5/14.
 */
@SpringBootApplication
@EnableConfigServer
public class SvrConfiguration extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SvrConfiguration.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SvrConfiguration.class);
    }
}
