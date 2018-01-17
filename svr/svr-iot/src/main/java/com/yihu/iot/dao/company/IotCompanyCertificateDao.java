package com.yihu.iot.dao.company;

import com.yihu.jw.iot.company.IotCompanyCertificateDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotCompanyCertificateDao extends PagingAndSortingRepository<IotCompanyCertificateDO, String>,
        JpaSpecificationExecutor<IotCompanyCertificateDO>{


}
