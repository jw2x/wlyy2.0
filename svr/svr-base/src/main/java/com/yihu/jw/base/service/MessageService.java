package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.MessageDao;
import com.yihu.jw.entity.base.message.MessageDo;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * Created by progr1mmer on 2018/8/20.
 */
@Service
public class MessageService extends BaseJpaService<MessageDo, MessageDao> {
}
