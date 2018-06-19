/**
 * Created by nature of king on 2018/5/10.
 */
package com.yihu.jw.dao;

import com.yihu.jw.entity.health.bank.TaskRuleDO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author wangzhinan
 * @create 2018-05-10 11:17
 * @desc health bank task dao
 **/
public interface TaskRuleDao extends PagingAndSortingRepository<TaskRuleDO,String>,JpaSpecificationExecutor<TaskRuleDO> {
}
