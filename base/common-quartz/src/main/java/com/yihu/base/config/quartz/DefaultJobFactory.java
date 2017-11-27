package com.yihu.base.config.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016.10.12.
 * 為了讓quartz種可以使用Spring的注入
 */
@Component("jobFactory")
public class DefaultJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
       // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
