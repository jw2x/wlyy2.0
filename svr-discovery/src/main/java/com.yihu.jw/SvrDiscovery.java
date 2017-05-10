package com.yihu.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by chenweida on 2017/5/10.
 */
@SpringBootApplication
@EnableEurekaServer
public class SvrDiscovery {
    public static void main(String[] args) {
        SpringApplication.run(SvrDiscovery.class, args);
    }
}
