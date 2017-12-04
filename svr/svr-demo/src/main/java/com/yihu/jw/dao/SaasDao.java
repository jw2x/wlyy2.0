package com.yihu.jw.dao;

import com.yihu.jw.model.SaasDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/12/1.
 */
public interface SaasDao extends PagingAndSortingRepository<SaasDO, String>, JpaSpecificationExecutor<SaasDO> {
    @Query("from SaasDO where appId=?1")
    SaasDO findByAppId(String clientId);
}
