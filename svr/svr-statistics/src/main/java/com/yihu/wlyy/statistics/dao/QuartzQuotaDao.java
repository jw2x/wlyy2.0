package com.yihu.wlyy.statistics.dao;

import com.yihu.jw.entity.base.statistics.QuotaDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface QuartzQuotaDao extends PagingAndSortingRepository<QuotaDO, String>, JpaSpecificationExecutor<QuotaDO> {
}
