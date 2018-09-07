package com.yihu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by chenweida on 2017/5/10.
 * localhost:10020/refresh  刷新当个微服务的配置 可以在需要刷新的bean上面@RefreshScope
 */
@SpringBootApplication
@EnableJpaAuditing
public class SvrBaseApplication extends SpringBootServletInitializer {

    public static void main(String[] args)  {
        SpringApplication.run(SvrBaseApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SvrBaseApplication.class);
    }

}
