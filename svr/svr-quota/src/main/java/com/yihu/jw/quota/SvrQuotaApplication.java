package com.yihu.jw.quota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by chenweida on 2017/5/16.
 */
@SpringBootApplication
@EnableDiscoveryClient//服务注册到发现服务
@EnableJpaRepositories(basePackages="com.yihu.jw.quota.dao.jpa")
public class SvrQuotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvrQuotaApplication.class, args);
    }
}