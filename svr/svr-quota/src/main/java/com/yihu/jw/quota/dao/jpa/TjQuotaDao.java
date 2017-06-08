package com.yihu.jw.quota.dao.jpa;

import com.yihu.jw.quota.model.jpa.TjQuota;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface TjQuotaDao extends PagingAndSortingRepository<TjQuota, Long>, JpaSpecificationExecutor<TjQuota> {
    @Query("from TjQuota j where j.code=?1 and j.status=1 ")
    TjQuota findByCode(String id);
}
