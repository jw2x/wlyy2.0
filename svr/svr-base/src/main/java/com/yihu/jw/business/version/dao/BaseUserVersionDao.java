package com.yihu.jw.business.version.dao;

import com.yihu.jw.base.version.BaseUserVersionDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/11/13.
 */
public interface BaseUserVersionDao extends PagingAndSortingRepository<BaseUserVersionDO, String>, JpaSpecificationExecutor<BaseUserVersionDO> {
    @Query("from BaseUserVersionDO where userId=?1")
    BaseUserVersionDO getUserVersionByUserId(String userId);
}
