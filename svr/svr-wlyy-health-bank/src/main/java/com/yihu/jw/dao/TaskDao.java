package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.jw.entity.health.bank.TaskDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-05-10 11:17
 * @desc health bank task dao
 **/
public interface TaskDao extends PagingAndSortingRepository<TaskDO,String>,JpaSpecificationExecutor<TaskDO> {
}
