package com.yihu.jw.base.dao.servicePackage;

import com.yihu.jw.entity.base.servicePackage.ServicePackageSkuDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yeshijie on 2018/8/29.
 */
public interface ServicePackageSkuDao extends PagingAndSortingRepository<ServicePackageSkuDO, String>, JpaSpecificationExecutor<ServicePackageSkuDO> {


}
