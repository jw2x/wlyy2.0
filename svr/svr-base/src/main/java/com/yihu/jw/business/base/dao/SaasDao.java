package com.yihu.jw.business.base.dao;

import com.yihu.jw.base.base.Saas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface SaasDao extends PagingAndSortingRepository<Saas, String>, JpaSpecificationExecutor<Saas> {
    @Query("from Saas f where f.name=?1 and f.status=1")
    Saas findByName(String name);

    @Query("from Saas f where f.name=?1 and f.status=1 and f.id != ?2")
    Saas findByNameExcludeId(String name, String id);

    @Query("from Saas f where f.id=?1 and f.status=1")
    Saas findById(String id);
}
