package com.yihu.iot.dao.company;

import com.yihu.jw.iot.company.IotCompanyDO;
import com.yihu.jw.iot.device.IotOrderPurchaseDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 企业表
 * @author yeshijie on 2018/1/15.
 */
public interface IotCompanyDao extends PagingAndSortingRepository<IotCompanyDO, String>,
        JpaSpecificationExecutor<IotCompanyDO> {

    @Query("from IotCompanyDO w where w.id =?1 and w.del=1 ")
    IotCompanyDO findById(String id);

    @Query("from IotCompanyDO w where w.businessLicense =?1 and w.del=1")
    IotCompanyDO findByBusinessLicense(String businessLicense);
}
