package com.yihu.iot.dao.company;

import com.yihu.jw.entity.iot.company.IotCompanyCertificateChangeRecordDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/1/16.
 */
public interface IotCompanyCertificateChangeRecordDao extends PagingAndSortingRepository<IotCompanyCertificateChangeRecordDO, String>,
        JpaSpecificationExecutor<IotCompanyCertificateChangeRecordDO> {

}
