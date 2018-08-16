//package com.yihu.jw.config.jpa;
//
//import org.hibernate.EmptyInterceptor;
//import org.hibernate.Interceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Properties;
//
///**
// * Created by chenweida on 2017/4/6.
// */
//@Component
//public class HibernateProperties {
//    @Value("${hibernate.dialect}")
//    private String dialect;
//    @Value("${hibernate.show_sql}")
//    private String show_sql;
////    @Value("${hibernate.ejb.naming_strategy}")
////    private String naming_strategy;
//
//    @Value("${hibernate.physical_naming_strategy}")
//    private String physical_naming_strategy;
//    @Value("${hibernate.implicit_naming_strategy}")
//    private String implicit_naming_strategy;
////    @Autowired
////    private List<Interceptor> interceptors;
//
//    public Properties hibProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", dialect);
//        properties.put("hibernate.show_sql", show_sql);
//        properties.put("hibernate.physical_naming_strategy", physical_naming_strategy);
//        properties.put("hibernate.implicit_naming_strategy", implicit_naming_strategy);
//        //properties.put("hibernate.ejb.interceptor", interceptors.get(0));
//        return properties;
//    }
//}
