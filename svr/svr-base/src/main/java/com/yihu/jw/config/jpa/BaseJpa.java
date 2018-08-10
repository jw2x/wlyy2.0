//package com.yihu.jw.config.jpa;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
///**
// * Created by chenweida on 2017/4/6.
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "baseEntityManagerFactory",
//        transactionManagerRef = "baseTransactionManager",
//        basePackages = {"com.yihu.jw.business"})   //设置Repository所在位置
//public class BaseJpa {
//
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Bean(name = "baseEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(DataSource dataSource) {
//
//        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setDataSource(dataSource);
//        emfb.setPackagesToScan("com.yihu.jw.base");
//        emfb.setPersistenceUnitName("base");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        emfb.setJpaVendorAdapter(vendorAdapter);
//        emfb.setJpaProperties(hibernateProperties.hibProperties());
//
//        return emfb;
//    }
//
//
//    @Bean(name = "baseTransactionManager")
//    @Primary
//    JpaTransactionManager transactionManagerSecondary(
//            @Qualifier("baseEntityManagerFactory") EntityManagerFactory builder) {
//        return new JpaTransactionManager(builder);
//    }
//}
