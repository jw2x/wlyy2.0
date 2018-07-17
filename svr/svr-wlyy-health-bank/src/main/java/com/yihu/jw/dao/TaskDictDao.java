package com.yihu.jw.dao;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.health.bank.TaskDictDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-06-08 15:20
 * @desc 字典DAO
 **/
public interface TaskDictDao extends PagingAndSortingRepository<TaskDictDO,String>,JpaSpecificationExecutor<TaskDictDO> {
}
