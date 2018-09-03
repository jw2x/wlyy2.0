package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.MqMessageSubscriberDao;
import com.yihu.jw.entity.base.message.MqMessageSubscriberDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 基于MQ的消息推送订阅者
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class MqMessageSubscriberService extends BaseJpaService<MqMessageSubscriberDO, MqMessageSubscriberDao> {
}
