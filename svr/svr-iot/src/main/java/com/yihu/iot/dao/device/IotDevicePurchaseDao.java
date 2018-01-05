package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDevicePurchaseDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDevicePurchaseDao extends PagingAndSortingRepository<IotDevicePurchaseDO,Long>,JpaSpecificationExecutor<IotDevicePurchaseDO> {

    @Query("from IotDevicePurchaseDO w where w.id =?1")
    IotDevicePurchaseDO findById(String id);
}
