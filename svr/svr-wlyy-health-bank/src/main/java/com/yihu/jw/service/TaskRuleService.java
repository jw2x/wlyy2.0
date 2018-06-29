package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.TaskRuleDao;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.entity.health.bank.TaskRuleDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-06-08 15:42
 * @desc 任务规则
 **/
@Service
@Transactional
public class TaskRuleService extends BaseJpaService<TaskRuleDO,TaskRuleDao>{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskRuleDao taskRuleDao;

    /**
     * 获取任务规则列表
     *
     * @param taskRuleDO 任务规则对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public Envelop<TaskRuleDO> selectByCondition(TaskRuleDO taskRuleDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskRuleDO,page,size,"*");
        List<TaskRuleDO> taskRuleDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskRuleDO.class));
        String sqlcount = new ISqlUtils().getSql(taskRuleDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskRuleDOS,page,size,count);
    }

    /**
     * 添加规则
     * @param taskRuleDO 任务规则对象
     * @return
     */
    public Envelop<Boolean> insert(TaskRuleDO taskRuleDO){
        Envelop<Boolean> envelop = new Envelop<>();
        taskRuleDO.setCreateTime(new Date());
        taskRuleDO.setUpdateTime(new Date());
        taskRuleDao.save(taskRuleDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 更新规则
     *
     * @param taskRuleDO 规则对象
     * @return
     */
    public Envelop<Boolean> update(TaskRuleDO taskRuleDO){
        Envelop<Boolean> envelop = new Envelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskRuleDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }


    /**
     *
     * @param ids
     * @return
     */
    public Envelop<Boolean> batchDelete(List<String> ids){
        Envelop<Boolean> envelop = new Envelop<>();
        for (int i =0;i<ids.size();i++){
            TaskRuleDO taskRuleDO = taskRuleDao.findOne(ids.get(i));
            taskRuleDO.setStatus(0);
            taskRuleDao.save(taskRuleDO);
        }
        return envelop;
    }
}
