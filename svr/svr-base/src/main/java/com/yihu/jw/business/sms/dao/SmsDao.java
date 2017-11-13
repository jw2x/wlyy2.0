package com.yihu.jw.business.sms.dao;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.yihu.jw.base.sms.BaseSms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmsDao extends PagingAndSortingRepository<BaseSms, String>, JpaSpecificationExecutor<BaseSms> {

    @Query("from Function f where f.name=?1 and f.status=1")
    BaseSms findByName(String name);

    @Query("from Function f where f.name=?1 and f.status=1 and f.code != ?2")
    BaseSms findByNameExcludeCode(String name, String code);

    @Query("from Function f where f.code=?1 and f.status=1")
    BaseSms findByCode(String code);


}
