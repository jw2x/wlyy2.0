package com.yihu.jw.base.activemq;

import com.yihu.jw.base.service.MqMessageService;
import com.yihu.jw.base.service.MqMessageSubscriberService;
import com.yihu.jw.entity.base.message.MqMessageDO;
import com.yihu.jw.entity.base.message.MqMessageSubscriberDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Component - ActiveMQ消息订阅初始化
 * Created by progr1mmer on 2018/8/30.
 */
@Component
public class MessageManager {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final Pattern PATTERN  = Pattern.compile("^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+(\\\\?{0,1}(([A-Za-z0-9-~]+\\\\={0,1})([A-Za-z0-9-~]*)\\\\&{0,1})*)$");

    private ExecutorService executorService;
    @Autowired
    private MqMessageService mqMessageService;
    @Autowired
    private MqMessageSubscriberService mqMessageSubscriberService;

    public void initConsumer() {
        try {
            Map<String, Set<String>> groups = new HashMap<>();
            List<MqMessageDO> mqMessageDos = mqMessageService.search("");
            for (MqMessageDO mqMessageDo : mqMessageDos) {
                List<MqMessageSubscriberDO> mqMessageSubscriberDOS = mqMessageSubscriberService.search("topic=" + mqMessageDo.getTopic());
                Set<String> subUrl = new HashSet<>();
                mqMessageSubscriberDOS.forEach(item -> {
                    if (item.getUrl() != null && PATTERN.matcher(item.getUrl()).find()) {
                        subUrl.add(item.getUrl());
                    } else {
                        LOGGER.error("[Topic: " + item.getTopic() + "] Illegal url: " + item.getUrl() + " of " + item.getSaasId());
                    }
                });
                groups.put(mqMessageDo.getTopic(), subUrl);
            }
            executorService = Executors.newFixedThreadPool(groups.size());
            for (String key : groups.keySet()) {
                executorService.execute(new ConsumerRunner(key, groups.get(key)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(-1);
        }
    }

}
