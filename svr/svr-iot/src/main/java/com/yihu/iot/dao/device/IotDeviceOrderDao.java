package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.device.IotDeviceOrderDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceOrderDao extends PagingAndSortingRepository<IotDeviceOrderDO,Long>,JpaSpecificationExecutor<IotDeviceOrderDO> {

    @Query("from IotDeviceOrderDO w where w.id =?1")
    IotDeviceOrderDO findById(String id);
}
