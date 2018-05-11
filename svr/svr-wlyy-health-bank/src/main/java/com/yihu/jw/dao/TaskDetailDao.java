package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.TaskDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wang zhinan on 2018/4/27.
 */
public interface TaskDetailDao extends PagingAndSortingRepository<TaskDetailDO,String>,JpaSpecificationExecutor<TaskDetailDO> {
}
