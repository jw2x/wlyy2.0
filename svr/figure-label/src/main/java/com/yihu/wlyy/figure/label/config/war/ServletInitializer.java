package com.yihu.wlyy.figure.label.config.war;//package com.yihu.wlyy.config.war;

import com.yihu.FigureLabelApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
    public ServletInitializer() {
        super();
        setRegisterErrorPageFilter(false); //报错不跳到错误页
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(FigureLabelApplication.class);
    }
}
