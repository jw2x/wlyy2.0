package com.yihu.jw.base.dao.servicePackage;

import com.yihu.jw.entity.base.servicePackage.ServicePackageDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
public interface ServicePackageDao extends PagingAndSortingRepository<ServicePackageDO, String>, JpaSpecificationExecutor<ServicePackageDO> {

}
