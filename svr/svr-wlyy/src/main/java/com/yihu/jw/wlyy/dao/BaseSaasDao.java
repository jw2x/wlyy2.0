package com.yihu.jw.wlyy.dao;

import com.yihu.jw.wlyy.entity.BaseSaas;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/19.
 */
public interface BaseSaasDao extends PagingAndSortingRepository<BaseSaas, Long>, JpaSpecificationExecutor<BaseSaas> {
    @Query("from BaseSaas f where f.name=?1 and f.status=1")
    BaseSaas findByName(String name);

    @Query("from BaseSaas f where f.code=?1 and f.status=1")
    BaseSaas findByCode(String code);
}
