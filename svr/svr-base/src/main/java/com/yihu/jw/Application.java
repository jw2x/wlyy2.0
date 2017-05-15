package com.yihu.jw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenweida on 2017/5/10.
 * localhost:10020/refresh  刷新当个微服务的配置 可以在需要刷新的bean上面@RefreshScope
 */
@SpringBootApplication
@EnableDiscoveryClient//服务注册到发现服务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
