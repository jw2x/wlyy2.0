package com.yihu.jw.base.dao.module;

import com.yihu.jw.entity.base.module.SaasModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Entity - 租户模块
 * Created by yeshijie on 2018/10/11.
 */
public interface SaasModuleDao extends PagingAndSortingRepository<SaasModuleDO, String>, JpaSpecificationExecutor<SaasModuleDO> {


}