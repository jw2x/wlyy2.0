package com.yihu.jw.config;

import com.yihu.jw.SvrWlyyHealthBankApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class WarConfig extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SvrWlyyHealthBankApplication.class);
    }

}
