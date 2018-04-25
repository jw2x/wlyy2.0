package com.yihu.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication(exclude = OAuth2AutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SvrWlyySpecialistApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvrWlyySpecialistApplication.class, args);
    }
}
