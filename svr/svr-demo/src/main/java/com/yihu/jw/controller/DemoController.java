package com.yihu.jw.controller;

import com.yihu.base.config.quartz.QuartzHelper;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/11/3.
 */
@RestController
public class DemoController {
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private QuartzHelper quartzHelper;

    @GetMapping("demo")
    public String demo() throws Exception {
        System.out.println(schedulerFactoryBean.getScheduler().getSchedulerName());
        return "123";
    }

}
