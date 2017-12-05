package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by chenweida on 2017/11/3.
 */

@SpringBootApplication
@ComponentScan(basePackages={"com"})
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy = true)
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

