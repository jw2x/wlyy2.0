package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by wang zhinan on 2018/4/27.
 */
public interface TaskPatientDetailDao extends PagingAndSortingRepository<TaskPatientDetailDO,String>,JpaSpecificationExecutor<TaskPatientDetailDO> {
    @Query("select t from TaskPatientDetailDO t where t.activityId = ?1")
    List<TaskPatientDetailDO> selectByActivityId(String activityId);

    @Query("select t from TaskPatientDetailDO t where taskId = ?1")
    List<TaskPatientDetailDO> selectByTaskId(String taskId);
}
