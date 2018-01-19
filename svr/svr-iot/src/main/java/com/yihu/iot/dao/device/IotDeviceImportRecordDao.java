package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceImportRecordDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/18.
 */
public interface IotDeviceImportRecordDao extends PagingAndSortingRepository<IotDeviceImportRecordDO, String>,
        JpaSpecificationExecutor<IotDeviceImportRecordDO> {

    @Query("from IotDeviceImportRecordDO w where w.purchaseId =?1 and w.status='1' and w.del=1")
    IotDeviceImportRecordDO findByPurchaseId(String purchaseId);
}
