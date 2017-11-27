package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.Function;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface FunctionDao extends PagingAndSortingRepository<Function, String>, JpaSpecificationExecutor<Function> {

    @Query("from Function f where f.name=?1 and f.status=1")
    Function findByName(String name);

    @Query("from Function f where f.id=?1 and f.status=1")
    Function findById(String id);

    @Query("from Function f where f.name=?1 and f.status=1 and f.id!=?2")
    Function findByNameExcludeId(String name,String id);

    @Query("from Function f where f.parentCode=?1 and f.status=1")
    List<Function> getChildren(String parentCode);

    @Query("from Function f where f.status=1")
    List<Function> findAll();
}
