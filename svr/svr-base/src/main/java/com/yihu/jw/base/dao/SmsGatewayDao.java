package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.sms.SmsGatewayDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 短信网关
 * Created by progr1mmer on 2018/8/23.
 */
public interface SmsGatewayDao extends PagingAndSortingRepository<SmsGatewayDO, String>, JpaSpecificationExecutor<SmsGatewayDO> {
}
