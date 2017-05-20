package com.yihu.jw.base.dao;

import com.yihu.jw.base.model.Function;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface FunctionDao extends PagingAndSortingRepository<Function, Long>, JpaSpecificationExecutor<Function> {

    @Query("from Function f where f.name=?1 and f.status=0")
    Function findByName(String name);

    @Query("from Function f where f.name=?1 and f.status=0 and f.code != ?2")
    Function findByNameExcludeCode(String name, String code);

    @Query("from Function f where f.code=?1 and f.status=0")
    Function findByCode(String code);
}
