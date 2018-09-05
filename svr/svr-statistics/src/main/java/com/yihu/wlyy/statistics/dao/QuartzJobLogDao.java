package com.yihu.wlyy.statistics.dao;

import com.yihu.jw.entity.base.statistics.JobLogDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface QuartzJobLogDao extends PagingAndSortingRepository<JobLogDO, String>, JpaSpecificationExecutor<JobLogDO> {
}
