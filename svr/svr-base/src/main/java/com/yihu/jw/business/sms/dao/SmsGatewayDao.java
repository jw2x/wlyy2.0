//package com.yihu.jw.business.sms.dao;
//
//import com.yihu.jw.base.sms.BaseSmsGatewayDO;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//
///**
// * Created by chenweida on 2017/5/22.
// */
//public interface SmsGatewayDao extends PagingAndSortingRepository<BaseSmsGatewayDO, String>, JpaSpecificationExecutor<BaseSmsGatewayDO> {
//    @Query("from BaseSmsGatewayDO f where f.name=?1 and f.status=1")
//    BaseSmsGatewayDO findByName(String name);
//
//    @Query("from BaseSmsGatewayDO f where f.name=?1 and f.status=1 and f.id != ?2")
//    BaseSmsGatewayDO findByNameExcludeCode(String name, String code);
//
//    @Query("from BaseSmsGatewayDO f where f.id=?1 and f.status=1")
//    BaseSmsGatewayDO findById(String id);
//}
