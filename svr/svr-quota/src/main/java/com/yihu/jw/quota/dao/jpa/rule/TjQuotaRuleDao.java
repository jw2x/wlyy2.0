package com.yihu.jw.quota.dao.jpa.rule;

import com.yihu.jw.quota.model.jpa.compute.TjCompute;
import com.yihu.jw.quota.model.jpa.rule.TjQuotaRule;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2017/6/1.
 */
public interface TjQuotaRuleDao extends PagingAndSortingRepository<TjQuotaRule, Long>, JpaSpecificationExecutor<TjQuotaRule> {
}
