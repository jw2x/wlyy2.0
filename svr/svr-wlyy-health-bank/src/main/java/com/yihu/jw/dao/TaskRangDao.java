package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.health.bank.TaskRangDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-06-08 15:29
 * @desc 任务范围
 **/
public interface TaskRangDao extends PagingAndSortingRepository<TaskRangDO,String>,JpaSpecificationExecutor<TaskRangDO> {
}
