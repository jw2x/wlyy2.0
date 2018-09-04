package com.yihu.wlyy.statistics.job.business;

import com.yihu.wlyy.statistics.etl.cache.Cache;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016.10.19.
 */

@Component
@Scope("prototype")
public class CacheCleanJob implements Job {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public static String jobKey="CLEAN_CACHE_JOB";
    public static String cron="0 55 23 * * ?";
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("clean cache start");
        Cache.cleanCache();
        logger.info("clean cache end");
    }

}
