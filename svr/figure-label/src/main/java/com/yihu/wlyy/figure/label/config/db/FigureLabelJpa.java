package com.yihu.wlyy.figure.label.config.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.yihu.base.SolrHelper;
import com.yihu.base.SolrPool;
import com.yihu.wlyy.figure.label.config.db.properties.DataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by chenweida on 2017/4/6.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "EntityManagerFactory",
        transactionManagerRef = "TransactionManager",
        basePackages = {"com.yihu.wlyy.figure.label.dao"})   //设置Repository所在位置
public class FigureLabelJpa {

    @Autowired
    private HibernateProperties hibernateProperties;
    @Autowired
    private DataSourceProperties dataSourceProperties;

//    @Autowired
//    private DruidConfig druidConfig;

    private DataSource buildDataSource(DruidDataSource datasource) throws SQLException{
        //configuration
        datasource.setInitialSize(dataSourceProperties.getInitialSize());
        datasource.setMinIdle(dataSourceProperties.getMinIdle());
        datasource.setMaxActive(dataSourceProperties.getMaxActive());
        datasource.setMaxWait(dataSourceProperties.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceProperties.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceProperties.getTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceProperties.getTestOnBorrow());
        datasource.setTestOnReturn(dataSourceProperties.getTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceProperties.getPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
        datasource.setRemoveAbandoned(dataSourceProperties.getRemoveAbandoned());
        datasource.setRemoveAbandonedTimeout(dataSourceProperties.getRemoveAbandonedTimeout());
        datasource.setLogAbandoned(dataSourceProperties.getLogAbandoned());
        datasource.setFilters(dataSourceProperties.getFilters());
        datasource.setConnectProperties(properties());//;# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
        datasource.setUseGlobalDataSourceStat(true);// 合并多个DruidDataSource的监控数据

        List proxyFilters = new ArrayList<>();
//        proxyFilters.add(druidConfig.statFilter());
//        datasource.setProxyFilters(proxyFilters);
        return datasource;
    }

    @Bean(name = "wlyyDataSource")
    @Primary
    public DataSource wlyyDataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.getWlyy().getUrl());
        datasource.setUsername(dataSourceProperties.getWlyy().getUsername());
        datasource.setPassword(dataSourceProperties.getWlyy().getPassword());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());
        return buildDataSource(datasource);
    }

    @Bean(name = "healtharchiveDataSource")
    public DataSource healtharchiveDataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.getHealtharchive().getUrl());
        datasource.setUsername(dataSourceProperties.getHealtharchive().getUsername());
        datasource.setPassword(dataSourceProperties.getHealtharchive().getPassword());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());
        return buildDataSource(datasource);
    }

    @Bean(name = "wlyy85DataSource")
    public DataSource wlyy85DataSource() throws SQLException {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dataSourceProperties.getWlyy85().getUrl());
        datasource.setUsername(dataSourceProperties.getWlyy85().getUsername());
        datasource.setPassword(dataSourceProperties.getWlyy85().getPassword());
        datasource.setDriverClassName(dataSourceProperties.getDriverClassName());
        return buildDataSource(datasource);
    }

    @Bean(name = "EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(@Qualifier("wlyyDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPackagesToScan("com.yihu.wlyy.figure.label.entity");
        emfb.setPersistenceUnitName("wlyy");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emfb.setJpaVendorAdapter(vendorAdapter);
        emfb.setJpaProperties(hibernateProperties.hibProperties());
        return emfb;
    }


    @Bean(name = "TransactionManager")
    @Primary
    JpaTransactionManager transactionManagerSecondary(@Qualifier("EntityManagerFactory") EntityManagerFactory builder) {
        return new JpaTransactionManager(builder);
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("druid.stat.mergeSql", "true");
        properties.put("slowSqlMillis", "1000");
        return properties;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("wlyyDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "healtharchiveTemplate")
    public JdbcTemplate secondJdbcTemplate(@Qualifier("healtharchiveDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "wlyy85Template")
    public JdbcTemplate thirdJdbcTemplate(@Qualifier("wlyy85DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SolrHelper solrHelper() {
        SolrHelper solrHelper = new SolrHelper();
        return solrHelper;
    }

    @Bean
    public SolrPool solrPool() {
        SolrPool solrPool = new SolrPool();
        return solrPool;
    }
}
