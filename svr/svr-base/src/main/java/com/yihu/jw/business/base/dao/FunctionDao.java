package com.yihu.jw.business.base.dao;

import com.yihu.jw.entity.base.function.FunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface FunctionDao extends PagingAndSortingRepository<FunctionDO, String>, JpaSpecificationExecutor<FunctionDO> {

    @Query("from FunctionDO f where f.name=?1 and f.status=1")
    FunctionDO findByName(String name);

    @Query("from FunctionDO f where f.id=?1 and f.status=1")
    FunctionDO findById(String id);

    @Query("from FunctionDO f where f.name=?1 and f.status=1 and f.id!=?2")
    FunctionDO findByNameExcludeId(String name,String id);

    @Query("from FunctionDO f where f.parentCode=?1 and f.status=1")
    List<FunctionDO> getChildren(String parentCode);

    @Query("from FunctionDO f where f.status=1")
    List<FunctionDO> findAll();
}
