package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.ActivityRuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wang zhinan on 2018/4/27.
 */
public interface ActivityRuleDao extends PagingAndSortingRepository<ActivityRuleDO,String>,JpaSpecificationExecutor<ActivityRuleDO>{
}
