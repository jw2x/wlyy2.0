package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by chenweida on 2017/11/27.
 */
@EnableJpaAuditing
@SpringBootApplication
public class IOTApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(IOTApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(IOTApplication.class);
    }
}
