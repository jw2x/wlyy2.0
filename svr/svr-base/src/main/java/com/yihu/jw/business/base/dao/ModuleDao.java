package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.Module;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<Module, String>, JpaSpecificationExecutor<Module> {
    @Query("from Module f where f.name=?1 and f.status=1")
    Module findByName(String name);

    @Query("from Module f where f.id=?1 and f.status=1")
    Module findById(String id);

    @Query("from Module f where f.name=?1 and f.status=1 and f.id != ?2")
    Module findByNameExcludeId(String name, String code);

    @Query("from Module f where f.parentId=?1 and f.status=1")
    List<Module> getChildren(String code);

    @Query("from Module f where f.status=1")
    List<Module> findAll();
}
