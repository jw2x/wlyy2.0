package com.yihu.jw.base.saas.dao;

import com.yihu.jw.entity.base.saas.SaasDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface SaasDao extends PagingAndSortingRepository<SaasDO, String>, JpaSpecificationExecutor<SaasDO> {
    @Query("from SaasDO f where f.name=?1 and f.status=1")
    SaasDO findByName(String name);

    @Query("from SaasDO f where f.name=?1 and f.status=1 and f.id != ?2")
    SaasDO findByNameExcludeId(String name, String id);

    @Query("from SaasDO f where f.id=?1 and f.status=1")
    SaasDO findById(String id);
}
