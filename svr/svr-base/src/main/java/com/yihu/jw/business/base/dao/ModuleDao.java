package com.yihu.jw.business.base.dao;

import com.yihu.jw.entity.base.module.ModuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<ModuleDO, String>, JpaSpecificationExecutor<ModuleDO> {
    @Query("from ModuleDO f where f.name=?1 and f.status=1")
    ModuleDO findByName(String name);

    @Query("from ModuleDO f where f.id=?1 and f.status=1")
    ModuleDO findById(String id);

    @Query("from ModuleDO f where f.name=?1 and f.status=1 and f.id != ?2")
    ModuleDO findByNameExcludeId(String name, String code);

    @Query("from ModuleDO f where f.parentId=?1 and f.status=1")
    List<ModuleDO> getChildren(String code);

    @Query("from ModuleDO f where f.status=1")
    List<ModuleDO> findAll();
}
