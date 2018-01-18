package com.yihu.iot.dao.device;

import com.yihu.jw.iot.device.IotDeviceOrderDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2017/12/1.
 */
public interface IotDeviceOrderDao extends PagingAndSortingRepository<IotDeviceOrderDO,String>,JpaSpecificationExecutor<IotDeviceOrderDO> {

    @Query("from IotDeviceOrderDO w where w.id =?1 and w.del=1")
    IotDeviceOrderDO findById(String id);

    @Query("from IotDeviceOrderDO w where w.ymd =?1")
    List<IotDeviceOrderDO> findByYmd(String ymd);
}
