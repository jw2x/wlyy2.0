package com.yihu.jw.base.dao.function;

import com.yihu.jw.entity.base.function.FunctionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao - 功能
 * Created by chenweida on 2017/5/19.
 */
public interface FunctionDao extends PagingAndSortingRepository<FunctionDO, String>, JpaSpecificationExecutor<FunctionDO> {

    @Query("select f from FunctionDO  f where f.moduleId in (:moduleId)")
    List<FunctionDO> findFunctionDOSByModuleIdExists(@Param("moduleId") String[] moduleId);


}
