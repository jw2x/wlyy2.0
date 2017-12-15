package com.yihu.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by chenweida on 2017/11/27.
 */
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages={"com"})
public class IOTApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOTApplication.class, args);
    }
}
