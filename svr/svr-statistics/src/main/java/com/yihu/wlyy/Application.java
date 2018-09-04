package com.yihu.wlyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Administrator on 2016.10.12.
 */
@SpringBootApplication
@EnableJpaRepositories(
        entityManagerFactoryRef = "wlyyEntityManagerFactory",
        transactionManagerRef = "wlyyTransactionManager")
@EnableAutoConfiguration(exclude = {
        ElasticsearchAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class,
        ElasticsearchDataAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class
})
public class Application {
    public static ApplicationContext ctx = null;
    public static void main(String[] args) {
        ctx = SpringApplication.run(Application.class, args);
    }

    /**
     * start 解决如下问题
     * Cannot forward to error page for
     * request [/strategy/list/] as the response has already been committed. As a
     * result, the response may have the wrong status code. If your application is
     * running on WebSphere Application Server you may be able to resolve this
     * problem by setting com.ibm.ws.webcontainer.invokeFlushAfterService to false
     */
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
    //end
}
