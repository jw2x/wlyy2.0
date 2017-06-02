package com.yihu.jw.quota.dao.jpa;

import com.yihu.jw.quota.model.jpa.TjQuota;
import com.yihu.jw.quota.model.jpa.TjQuotaLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/2.
 */
public interface TjQuotaLogDao extends PagingAndSortingRepository<TjQuotaLog, Long>, JpaSpecificationExecutor< TjQuotaLog> {
}
