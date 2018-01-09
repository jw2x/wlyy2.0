package com.yihu.iot.dao.supplier;

import com.yihu.jw.iot.supplier.IotDeviceSupplierDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2017/12/5.
 */
public interface IotDeviceSupplierDao extends PagingAndSortingRepository<IotDeviceSupplierDO, String>, JpaSpecificationExecutor<IotDeviceSupplierDO> {

    @Query("from IotDeviceSupplierDO w where w.id =?1")
    IotDeviceSupplierDO findById(String id);
}
