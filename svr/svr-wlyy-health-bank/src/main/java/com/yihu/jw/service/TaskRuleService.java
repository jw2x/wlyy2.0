package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.dao.TaskDao;
import com.yihu.jw.dao.TaskPatientDetailDao;
import com.yihu.jw.dao.TaskRuleDao;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.entity.health.bank.TaskRuleDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
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
public class TaskRuleService extends BaseJpaService<TaskRuleDO,TaskRuleDao> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskRuleDao taskRuleDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskPatientDetailDao taskPatientDetailDao;

    /**
     * 获取任务规则列表
     *
     * @param taskRuleDO 任务规则对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<TaskRuleDO, TaskRuleDO> selectByCondition(TaskRuleDO taskRuleDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskRuleDO,page,size,"*");
        List<TaskRuleDO> taskRuleDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskRuleDO.class));
        String sqlcount = new ISqlUtils().getSql(taskRuleDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskRuleDOS, page,size,count);
    }

    /**
     * 添加规则
     * @param taskRuleDO 任务规则对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(TaskRuleDO taskRuleDO){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
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
    public MixEnvelop<Boolean, Boolean> update(TaskRuleDO taskRuleDO){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskRuleDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 批量删除规则
     *
     * @param ids []
     * @return
     */
    public MixEnvelop<Boolean, Boolean> batchDelete(List<String> ids){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        for (int i =0;i<ids.size();i++){
            TaskDO taskDO = taskDao.selectByTaskRuleId(ids.get(i));
            taskDO.setStatus(0);
            taskDO.setCreateTime(new Date());
            taskDO.setUpdateTime(new Date());
            taskDao.save(taskDO);
            List<TaskPatientDetailDO> taskPatientDetailDOS = taskPatientDetailDao.selectByTaskId(taskDO.getId());
            for (TaskPatientDetailDO taskPatientDetailDO:taskPatientDetailDOS){
                taskPatientDetailDO.setStatus(-1);
                taskPatientDetailDO.setCreateTime(new Date());
                taskPatientDetailDO.setUpdateTime(new Date());
                taskPatientDetailDao.save(taskPatientDetailDO);
            }
            TaskRuleDO taskRuleDO = taskRuleDao.findOne(ids.get(i));
            taskRuleDO.setStatus(0);
            taskRuleDO.setCreateTime(new Date());
            taskRuleDO.setUpdateTime(new Date());
            taskRuleDao.save(taskRuleDO);
        }
        return envelop;
    }
}
