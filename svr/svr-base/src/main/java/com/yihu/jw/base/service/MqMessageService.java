package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.MqMessageDao;
import com.yihu.jw.entity.base.message.MqMessageDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Service - 基于MQ的消息推送
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class MqMessageService extends BaseJpaService<MqMessageDO, MqMessageDao> {
}
