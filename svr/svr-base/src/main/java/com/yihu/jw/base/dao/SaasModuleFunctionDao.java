package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.saas.SaasModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - Saas所分配的模块功能
 * Created by progr1mmer on 2018/8/17.
 */
public interface SaasModuleFunctionDao extends PagingAndSortingRepository<SaasModuleFunctionDO, String>, JpaSpecificationExecutor<SaasModuleFunctionDO> {

}
