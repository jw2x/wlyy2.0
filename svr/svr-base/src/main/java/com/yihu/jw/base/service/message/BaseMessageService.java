package com.yihu.jw.base.service.message;

import com.yihu.jw.base.dao.message.BaseMessageDao;
import com.yihu.jw.entity.base.message.BaseMessageDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 消息表服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年09月14日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class BaseMessageService extends BaseJpaService<BaseMessageDO, BaseMessageDao> {

}
