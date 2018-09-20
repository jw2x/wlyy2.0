package com.yihu.jw.base.service.message;

import com.yihu.jw.base.dao.message.BaseMessageTypeDao;
import com.yihu.jw.entity.base.message.BaseMessageTypeDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

/**
 * 
 * 消息类型字典服务service
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
public class BaseMessageTypeService extends BaseJpaService<BaseMessageTypeDO, BaseMessageTypeDao> {
}
