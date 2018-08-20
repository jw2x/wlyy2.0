package com.yihu.iot.dao.company;

import com.yihu.jw.entity.iot.company.IotCompanyCertificateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotCompanyCertificateDao extends PagingAndSortingRepository<IotCompanyCertificateDO, String>,
        JpaSpecificationExecutor<IotCompanyCertificateDO>{

    @Query("from IotCompanyCertificateDO w where w.id =?1 and w.del=1")
    IotCompanyCertificateDO findById(String id);

    @Query("from IotCompanyCertificateDO w where w.companyId =?1 and w.del=1")
    List<IotCompanyCertificateDO> findByCompanyId(String companyId);
}
