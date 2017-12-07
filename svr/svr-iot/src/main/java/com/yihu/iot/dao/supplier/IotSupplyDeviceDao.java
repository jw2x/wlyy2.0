package com.yihu.iot.dao.supplier;

import com.yihu.jw.iot.supplier.IotSupplyDeviceDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/5.
 */
public interface IotSupplyDeviceDao extends PagingAndSortingRepository<IotSupplyDeviceDO, Long>, JpaSpecificationExecutor<IotSupplyDeviceDO> {


}
