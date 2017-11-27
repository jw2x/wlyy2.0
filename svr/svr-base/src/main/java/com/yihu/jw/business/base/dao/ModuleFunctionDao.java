package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.ModuleFunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface ModuleFunctionDao extends PagingAndSortingRepository<ModuleFunctionDO, String>, JpaSpecificationExecutor<ModuleFunctionDO> {

    @Transactional
    @Modifying
    @Query("delete from ModuleFunctionDO es where es.moduleId = ?1")
    int deleteByModuleId(String moduleId);

    @Query("from ModuleFunctionDO mf where mf.moduleId = ?1")
    List<ModuleFunctionDO> findByModuleId(String id);

    @Transactional
    @Modifying
    @Query("delete from ModuleFunctionDO es where es.functionId = ?1 and es.moduleId = ?2")
    void delete(String delCode, String moduleCode);
}
