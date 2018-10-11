package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasTypeModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Entity - 租户类型模块
 * Created by yeshijie on 2018/10/11.
 */
public interface SaasTypeModuleDao extends PagingAndSortingRepository<SaasTypeModuleDO, String>, JpaSpecificationExecutor<SaasTypeModuleDO> {


}