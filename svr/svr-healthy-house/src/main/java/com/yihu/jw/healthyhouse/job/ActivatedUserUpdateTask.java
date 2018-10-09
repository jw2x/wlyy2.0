package com.yihu.jw.healthyhouse.job;


import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.service.user.UserService;
import com.yihu.jw.healthyhouse.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;


/**
 * Task - 定时检查集群状态，提高解析任务容错率
 * Created by progr1mmer on 2017/12/15.
 */
//@Component
public class ActivatedUserUpdateTask {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0/40 * * * * ?")
    private void startTask() {
        List<String> keys = new ArrayList<>();
        String pattern = "healthyHouse:*:activated";
        Set<String> keys1 = redisClient.keys(pattern);
        List<Serializable> ids = redisClient.multiGet(keys1);
        try {
            if (ids!=null && ids.size()>0) {
                userService.updateUserOffLine(ids);
            }
        } catch (ManageException e) {
            e.printStackTrace();
        }

    }


}
