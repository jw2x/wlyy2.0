package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.saas.SaasModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by progr1mmer on 2018/8/17.
 */
public interface SaasModuleDao extends PagingAndSortingRepository<SaasModuleDO, Integer>, JpaSpecificationExecutor<SaasModuleDO> {

}
