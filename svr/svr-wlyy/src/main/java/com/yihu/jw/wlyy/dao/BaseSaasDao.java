package com.yihu.jw.wlyy.dao;

import com.yihu.jw.base.base.SaasDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface BaseSaasDao extends PagingAndSortingRepository<SaasDO, String>, JpaSpecificationExecutor<SaasDO> {
    @Query("from SaasDO f where f.name=?1 and f.status=1")
    SaasDO findByName(String name);

    @Query("from SaasDO f where f.code=?1 and f.status=1")
    SaasDO findByCode(String code);
}
