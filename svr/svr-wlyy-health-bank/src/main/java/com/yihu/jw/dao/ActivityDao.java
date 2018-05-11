package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.ActivityDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wang zhinan on 2018/4/27.
 */
public interface ActivityDao extends PagingAndSortingRepository<ActivityDO,String>,JpaSpecificationExecutor<ActivityDO> {
}
