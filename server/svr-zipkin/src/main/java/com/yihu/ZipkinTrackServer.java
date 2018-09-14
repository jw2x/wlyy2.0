package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * @author chenweida
 * @date Created on 2017/5/18.
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinTrackServer {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinTrackServer.class, args);
    }
}
