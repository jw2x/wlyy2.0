package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by wang zhinan on 2018/4/27.
 */
public interface TaskPatientDetailDao extends PagingAndSortingRepository<TaskPatientDetailDO,String>,JpaSpecificationExecutor<TaskPatientDetailDO> {
}
