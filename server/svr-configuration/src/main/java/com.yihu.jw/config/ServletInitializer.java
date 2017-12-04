package com.yihu.jw.config;

import com.yihu.jw.SvrConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by chenweida on 2017/12/4.
 * 部署在tomcat需要
 */
public class ServletInitializer extends SpringBootServletInitializer {
    public ServletInitializer() {
        super();
        setRegisterErrorPageFilter(false); //报错不跳到错误页
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SvrConfiguration.class);
    }
}

