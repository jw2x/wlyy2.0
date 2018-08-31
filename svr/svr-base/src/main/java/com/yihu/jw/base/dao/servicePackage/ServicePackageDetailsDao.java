package com.yihu.jw.base.dao.servicePackage;

import com.yihu.jw.entity.base.servicePackage.ServicePackageDetailsDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yeshijie on 2018/8/17.
 */
public interface ServicePackageDetailsDao extends PagingAndSortingRepository<ServicePackageDetailsDO, String>, JpaSpecificationExecutor<ServicePackageDetailsDO> {


}
