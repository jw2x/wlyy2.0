package com.yihu.jw.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @author HZY
 * @vsrsion 1.0
 * Created at 2016/8/5.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.yihu.jw")
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
