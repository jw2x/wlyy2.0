package com.yihu.wlyy.figure.label;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by chenweida on 2018/3/5.
 */
@SpringBootApplication
@EnableJpaRepositories(
        entityManagerFactoryRef = "wlyyEntityManagerFactory",
        transactionManagerRef = "wlyyTransactionManager")
@EnableAutoConfiguration(exclude = {
//        ElasticsearchAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class,
//        ElasticsearchDataAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class
})
@ComponentScan({"com.yihu.wlyy"})
public class FigureLabelApplication {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(FigureLabelApplication.class, args);
    }

}
