package com.yihu.jw.business.base.dao.base;

import com.yihu.jw.business.base.model.base.Function;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface FunctionDao extends PagingAndSortingRepository<Function, Long>, JpaSpecificationExecutor<Function> {

    @Query("from Function f where f.name=?1 and f.status=1")
    Function findByName(String name);

    @Query("from Function f where f.name=?1 and f.status=1 and f.code != ?2")
    Function findByNameExcludeCode(String name, String code);

    @Query("from Function f where f.code=?1 and f.status=1")
    Function findByCode(String code);

    @Query("from Function f where f.parentCode=?1 and f.status=1")
    List<Function> getChildren(String code);

    @Query("from Function f where f.status=1")
    List<Function> findAll();
}
