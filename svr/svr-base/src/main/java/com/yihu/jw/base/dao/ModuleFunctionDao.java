package com.yihu.jw.base.dao;

import com.yihu.jw.base.model.ModuleFunction;
import com.yihu.jw.base.model.SaasModule;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface ModuleFunctionDao extends PagingAndSortingRepository<ModuleFunction, Long>, JpaSpecificationExecutor<ModuleFunction> {
    @Modifying
    @Query("delete from ModuleFunction es where es.moduleId = ?1")
    int deleteByModuleCode(String moduleCode);
}
