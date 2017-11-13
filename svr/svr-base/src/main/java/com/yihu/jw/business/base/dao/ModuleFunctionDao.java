package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.ModuleFunction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface ModuleFunctionDao extends PagingAndSortingRepository<ModuleFunction, String>, JpaSpecificationExecutor<ModuleFunction> {

    @Transactional
    @Modifying
    @Query("delete from ModuleFunction es where es.moduleId = ?1")
    int deleteByModuleId(String moduleId);

    @Query("from ModuleFunction mf where mf.moduleId = ?1")
    List<ModuleFunction> findByModuleId(String id);

    @Transactional
    @Modifying
    @Query("delete from ModuleFunction es where es.functionId = ?1 and es.moduleId = ?2")
    void delete(String delCode, String moduleCode);
}
