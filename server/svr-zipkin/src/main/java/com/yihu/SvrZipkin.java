package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by chenweida on 2017/5/18.
 */
@SpringBootApplication
@EnableZipkinServer
public class SvrZipkin {

    public static void main(String[] args) {
        SpringApplication.run(SvrZipkin.class, args);
    }
}
