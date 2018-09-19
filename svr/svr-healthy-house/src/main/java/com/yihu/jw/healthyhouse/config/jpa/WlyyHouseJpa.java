//package com.yihu.jw.healthyhouse.config.jpa;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
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
//import java.util.Properties;
//
///**
// * Created by chenweida on 2017/4/6.
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "wlyyHouseEntityManagerFactory",
//        transactionManagerRef = "wlyyHouseTransactionManager",
//        basePackages = {"com.yihu.jw.healthyhouse.dao"})   //设置Repository所在位置
//public class WlyyHouseJpa {
//
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Bean(name = "wlyyHouseEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(DataSource dataSource) {
//
//        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//        emfb.setDataSource(dataSource);
//        emfb.setPackagesToScan("com.yihu.jw.healthyhouse.model");
//        emfb.setPersistenceUnitName("archives");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        emfb.setJpaVendorAdapter(vendorAdapter);
//        Properties properties = hibernateProperties.hibProperties();
//        properties.put("hibernate.ejb.naming_strategy","org.hibernate.cfg.ImprovedNamingStrategy");
//        emfb.setJpaProperties(hibernateProperties.hibProperties());
//
//        return emfb;
//    }
//
//
//    @Bean(name = "wlyyHouseTransactionManager")
//    @Primary
//    JpaTransactionManager transactionManagerSecondary(
//            @Qualifier("wlyyHouseEntityManagerFactory") EntityManagerFactory builder) {
//        return new JpaTransactionManager(builder);
//    }
//}
