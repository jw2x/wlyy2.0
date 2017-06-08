package com.yihu.jw.base.dao.sms;

import com.yihu.jw.base.model.Function;
import com.yihu.jw.base.model.sms.BaseSms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/5/22.
 */
public interface SmsDao extends PagingAndSortingRepository<BaseSms, Long>, JpaSpecificationExecutor<BaseSms> {

    @Query("from Function f where f.name=?1 and f.status=1")
    BaseSms findByName(String name);

    @Query("from Function f where f.name=?1 and f.status=1 and f.code != ?2")
    BaseSms findByNameExcludeCode(String name, String code);

    @Query("from Function f where f.code=?1 and f.status=1")
    BaseSms findByCode(String code);
}
