package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;


/**
 * Created by chenweida on 2017/5/18.
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinTrackServer {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinTrackServer.class, args);
    }
}
