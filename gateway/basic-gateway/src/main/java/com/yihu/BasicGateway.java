package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by progr1mmer on 2018/8/8.
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class BasicGateway extends SpringBootServletInitializer {

    public static void main(String [] args) {
        SpringApplication.run(BasicGateway.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BasicGateway.class);
    }

}
