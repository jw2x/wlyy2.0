package com.yihu.wlyy.statistics.config.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by chenweida on 2017/4/6.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "wlyyEntityManagerFactory",
        transactionManagerRef = "wlyyTransactionManager",
        basePackages = {"com.yihu.wlyy.statistics.dao"})   //设置Repository所在位置
public class WlyyJpa {

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "wlyyEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan("com.yihu.wlyy");
        emfb.setPersistenceUnitName("wlyy");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        emfb.setJpaProperties(hibernateProperties.hibProperties());

        return emfb;
    }




    @Bean(name = "wlyyTransactionManager")
    @Primary
    JpaTransactionManager transactionManagerSecondary(
            @Qualifier("wlyyEntityManagerFactory") EntityManagerFactory builder) {
        return new JpaTransactionManager(builder);
    }
}
