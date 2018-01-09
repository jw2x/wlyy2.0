package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceDao extends PagingAndSortingRepository<IotDeviceDO, String>, JpaSpecificationExecutor<IotDeviceDO> {

    @Query("from IotDeviceDO w where w.id =?1")
    IotDeviceDO findById(String id);

    @Query("select count(*) from IotDeviceDO w where w.deviceSn =?1")
    int countByDeviceSn(String deviceSn);

    @Query("from IotDeviceDO w where w.deviceSn =?1")
    IotDeviceDO findByDeviceSn(String deviceSn);
}
