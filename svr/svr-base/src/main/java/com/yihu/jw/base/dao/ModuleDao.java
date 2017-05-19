package com.yihu.jw.base.dao;

import com.yihu.jw.base.model.Module;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface ModuleDao extends PagingAndSortingRepository<Module, Long>, JpaSpecificationExecutor<Module> {
    @Query("from Module f where f.name=?1 and f.status=0")
    Module findByName(String name);

    @Query("from Module f where f.name=?1 and f.status=0 and f.code != ?2")
    Module findByNameExcludeCode(String name, String code);

    @Query("from Module f where f.code=?1 and f.status=0")
    Module findByCode(String code);
}
