package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.message.MqMessageDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 基于MQ的消息推送
 * Created by progr1mmer on 2018/8/20.
 */
public interface MqMessageDao extends PagingAndSortingRepository<MqMessageDO, String>, JpaSpecificationExecutor<MqMessageDO> {
}
