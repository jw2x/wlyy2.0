package com.yihu.jw.base.dao.servicePackage;

import com.yihu.jw.entity.base.servicePackage.ServicePackageNormcatDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author yeshijie on 2018/8/29.
 */
public interface ServicePackageNormcatDao extends PagingAndSortingRepository<ServicePackageNormcatDO, String>, JpaSpecificationExecutor<ServicePackageNormcatDO> {

}
