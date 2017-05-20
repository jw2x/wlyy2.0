package com.yihu.jw.base.dao;

import com.yihu.jw.base.model.Saas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface SaasDao extends PagingAndSortingRepository<Saas, Long>, JpaSpecificationExecutor<Saas> {
    @Query("from Saas f where f.name=?1 and f.status=0")
    Saas findByName(String name);

    @Query("from Saas f where f.name=?1 and f.status=0 and f.code != ?2")
    Saas findByNameExcludeCode(String name, String code);

    @Query("from Saas f where f.code=?1 and f.status=0")
    Saas findByCode(String code);
}
