package com.yihu.iot.dao.supplier;

import com.yihu.jw.iot.supplier.IotSupplyDeviceDataTypeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/5.
 */
public interface IotSupplyDeviceDataTypeDao extends PagingAndSortingRepository<IotSupplyDeviceDataTypeDO, Long>, JpaSpecificationExecutor<IotSupplyDeviceDataTypeDO> {


}
