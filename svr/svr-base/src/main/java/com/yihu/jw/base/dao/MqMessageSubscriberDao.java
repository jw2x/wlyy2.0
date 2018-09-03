package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.message.MqMessageSubscriberDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 基于MQ的消息推送订阅者
 * Created by progr1mmer on 2018/8/20.
 */
public interface MqMessageSubscriberDao extends PagingAndSortingRepository<MqMessageSubscriberDO, String>, JpaSpecificationExecutor<MqMessageSubscriberDO> {
}
