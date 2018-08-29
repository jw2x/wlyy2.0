package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by chenweida on 2017/5/10.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServer.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DiscoveryServer.class);
    }
}
