package com.yihu.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by chenweida on 2017/5/14.
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class SvrConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(SvrConfiguration.class, args);
    }
}
