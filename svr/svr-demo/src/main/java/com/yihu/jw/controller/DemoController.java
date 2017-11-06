package com.yihu.jw.controller;

import com.yihu.jw.config.quartz.QuartzHelper;
import com.yihu.jw.restmodel.common.Envelop;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
