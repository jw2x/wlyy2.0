package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceDao extends PagingAndSortingRepository<IotDeviceDO, String>, JpaSpecificationExecutor<IotDeviceDO> {

    @Query("from IotDeviceDO w where w.id =?1 and w.del=1")
    IotDeviceDO findById(String id);

    @Query("select count(*) from IotDeviceDO w where w.deviceSn =?1 and w.del=1")
    int countByDeviceSn(String deviceSn);

    @Query("select count(*) from IotDeviceDO w where w.purchaseId =?1 and w.del=1")
    int countByPurchaseId(String purchaseId);

    @Query("select count(*) from IotDeviceDO w where w.orderId =?1 and w.del=1")
    int countByOrderId(String orderId);

    @Query("from IotDeviceDO w where w.deviceSn =?1 and w.del=1")
    IotDeviceDO findByDeviceSn(String deviceSn);

    @Query("from IotDeviceDO w where w.simNo =?1 and w.del=1")
    IotDeviceDO findBySimNo(String simNo);

    @Query("from IotDeviceDO w where w.purchaseId =?1 and w.del=1")
    List<IotDeviceDO> findListByPurchaseId(String purchaseId);

    @Query("from IotDeviceDO w where w.orderId =?1 and w.del=1")
    List<IotDeviceDO> findListByOrderId(String orderId);
}
