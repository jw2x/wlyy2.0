package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceDO;
import com.yihu.jw.iot.dict.IotDeviceDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceDao extends PagingAndSortingRepository<IotDeviceDO, Long>, JpaSpecificationExecutor<IotDeviceDO> {

    @Query("from IotDeviceDO w where w.id =?1")
    IotDeviceDO findById(String id);

    @Query("count(*) from IotDeviceDO w where w.deviceSn =?1")
    int countByDeviceSn(String deviceSn);

    @Query("from IotDeviceDO w where w.deviceSn =?1")
    IotDeviceDO findByDeviceSn(String deviceSn);
}
