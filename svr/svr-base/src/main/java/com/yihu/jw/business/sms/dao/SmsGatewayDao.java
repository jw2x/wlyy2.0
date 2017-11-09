package com.yihu.jw.business.sms.dao;

import com.yihu.jw.base.sms.BaseSmsGateway;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface SmsGatewayDao extends PagingAndSortingRepository<BaseSmsGateway, Long>, JpaSpecificationExecutor<BaseSmsGateway> {
    @Query("from BaseSmsGateway f where f.name=?1 and f.status=1")
    BaseSmsGateway findByName(String name);

    @Query("from BaseSmsGateway f where f.name=?1 and f.status=1 and f.code != ?2")
    BaseSmsGateway findByNameExcludeCode(String name, String code);

    @Query("from BaseSmsGateway f where f.code=?1 and f.status=1")
    BaseSmsGateway findByCode(String code);
}
