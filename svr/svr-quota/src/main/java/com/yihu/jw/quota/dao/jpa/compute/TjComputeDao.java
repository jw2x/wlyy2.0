package com.yihu.jw.quota.dao.jpa.compute;

import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.model.jpa.rule.TjCleanRule;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface TjComputeDao extends PagingAndSortingRepository<TjCompute, Long>, JpaSpecificationExecutor<TjCompute> {
}
