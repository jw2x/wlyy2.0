package com.yihu.jw.config;

import com.yihu.jw.SvrBaseApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class SvrBaseWarConfig extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SvrBaseApplication.class);
    }

}
