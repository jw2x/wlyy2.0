package com.yihu.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by chenweida on 2017/5/10.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@SpringBootApplication
@EnableDiscoveryClient//服务注册到发现服务
@EnableHystrix //启动断路器
//@EnableZuulProxy //启动zuul代理 路由
@EnableFeignClients //声名式的客户端
@EnableCircuitBreaker
@ComponentScan(basePackages={"com"})
public class WebGateWayAppliaction {

    public static void main(String[] args) {
        SpringApplication.run(WebGateWayAppliaction.class, args);
    }
}
