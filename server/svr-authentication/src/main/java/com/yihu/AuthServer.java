package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by progr1mmer on 2018/8/29.
 */
@SpringBootApplication
public class AuthServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuthServer.class);
    }
}
