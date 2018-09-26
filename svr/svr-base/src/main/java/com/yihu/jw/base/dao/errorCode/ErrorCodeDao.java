package com.yihu.jw.base.dao.errorCode;

import com.yihu.jw.entity.base.dict.ErrorCodeDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author yeshijie on 2018/9/26.
 */
public interface ErrorCodeDao extends PagingAndSortingRepository<ErrorCodeDO, String>, JpaSpecificationExecutor<ErrorCodeDO> {

    @Query("select count(*) from ErrorCodeDO a where a.errorCode = ?1 ")
    int isExistsErrorCode(String errorCode);
}
