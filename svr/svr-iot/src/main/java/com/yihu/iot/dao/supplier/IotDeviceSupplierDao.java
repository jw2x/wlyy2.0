package com.yihu.iot.dao.supplier;

import com.yihu.jw.iot.supplier.IotDeviceSupplierDO;
import com.yihu.jw.wlyy.agreement.WlyyAgreementKpiLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2017/12/5.
 */
public interface IotDeviceSupplierDao extends PagingAndSortingRepository<IotDeviceSupplierDO, Long>, JpaSpecificationExecutor<IotDeviceSupplierDO> {

    @Query("from IotDeviceSupplierDO w where w.id =?1")
    IotDeviceSupplierDO findById(String code);
}
