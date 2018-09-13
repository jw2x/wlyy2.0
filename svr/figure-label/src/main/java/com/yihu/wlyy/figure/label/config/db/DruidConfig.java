package com.yihu.wlyy.figure.label.config.db;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenweida on 2018/3/5.
 */
//@Configuration
public class DruidConfig {
   /* //------------------------------------druid 监控----------------------------------------------
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建servlet注册实体
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //设置ip黑名单，如果allow与deny共同存在时,deny优先于allow
        // servletRegistrationBean.addInitParameter("deny","192.168.0.19");
        //设置控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "jkzl");
        servletRegistrationBean.addInitParameter("loginPassword", "jkzlehr");
        //是否可以重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(WebStatFilter webStatFilter) {
        //创建过滤器
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webStatFilter);
        //设置过滤器过滤路径
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的形式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;

    }

    @Bean
    public StatFilter statFilter() {
        return new StatFilter();
    }

    @Bean
    public WebStatFilter webStatFilter() {
        return new WebStatFilter();
    }*/
    //------------------------------------druid 监控----------------------------------------------
}
