package com.yihu.iot.dao.device;

import com.yihu.jw.entity.iot.device.IotOrderPurchaseDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotOrderPurchaseDao extends PagingAndSortingRepository<IotOrderPurchaseDO,String>,JpaSpecificationExecutor<IotOrderPurchaseDO> {

    @Query("from IotOrderPurchaseDO w where w.id =?1")
    IotOrderPurchaseDO findById(String id);

    @Query("select count(*) from IotOrderPurchaseDO a where  a.del = 1 and a.productId = ?1 ")
    long countByProductId(String productId);

    @Query(value = "SELECT IFNULL(SUM(purchase_num),0)  from iot_order_purchase WHERE order_id =?1 and del = 1 ",nativeQuery = true)
    int sumByOrderId(String orderId);

}
