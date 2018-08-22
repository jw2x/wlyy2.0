package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.MessageSubscriberDao;
import com.yihu.jw.entity.base.message.MessageSubscriberDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class MessageSubscriberService extends BaseJpaService<MessageSubscriberDO, MessageSubscriberDao> {
}
