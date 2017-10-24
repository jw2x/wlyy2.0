package com.yihu.jw.business.base.dao;

import com.yihu.jw.business.base.model.Module;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<Module, Long>, JpaSpecificationExecutor<Module> {
    @Query("from Module f where f.name=?1 and f.status=1")
    Module findByName(String name);

    @Query("from Module f where f.name=?1 and f.status=1 and f.code != ?2")
    Module findByNameExcludeCode(String name, String code);

    @Query("from Module f where f.code=?1 and f.status=1")
    Module findByCode(String code);

    @Query("from Module f where f.parentCode=?1 and f.status=1")
    List<Module> getChildren(String code);

    @Query("from Module f where f.status=1")
    List<Module> findAll();
}
