package com.yihu.jw.service;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.ActivityDao;
import com.yihu.jw.dao.TaskDao;
import com.yihu.jw.dao.TaskPatientDetailDao;
import com.yihu.jw.entity.health.bank.ActivityDO;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.entity.health.bank.TaskRangDO;
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
 * @create 2018-06-08 15:43
 * @desc 任务参与 Service
 **/
@Service
@Transactional
public class TaskPatientDtailService extends BaseJpaService<TaskPatientDetailDO,TaskPatientDetailDao> {
    @Autowired
    private TaskPatientDetailDao taskPatientDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ActivityDao activityDao;


    /**
     * 查看任务参与情况
     *
     * @param taskPatientDetailDO 任务参与对象
     *
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public Envelop<TaskRangDO> selectByCondition(TaskPatientDetailDO taskPatientDetailDO, Integer page, Integer size){
        String sql = new ISqlUtils().getSql(taskPatientDetailDO,page,size,"*");
        List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
        String sqlcount = new ISqlUtils().getSql(taskPatientDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskPatientDetailDOS,page,size,count);
    }

    /**
     * 添加任务参与
     *
     * @param taskPatientDetailDO 任务参与对象
     * @return
     */
    public Envelop<Boolean> insert(TaskPatientDetailDO taskPatientDetailDO) throws Exception{
        Envelop<Boolean> envelop = new Envelop<>();
        taskPatientDetailDO.setCreateTime(new Date());
        taskPatientDetailDO.setUpdateTime(new Date());
        taskPatientDetailDO.setStatus(Integer.parseInt("0"));
        String sql = "select * from wlyy_health_bank_task_patient_detail where patient_openid = '"+taskPatientDetailDO.getPatientOpenid()+"'";
        List<TaskPatientDetailDO> taskPatientDetailDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
        if (taskPatientDetailDOList != null && taskPatientDetailDOList.size() !=0){
            throw new Exception("该微信账号已报名过！");
        }
        taskPatientDetailDao.save(taskPatientDetailDO);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 更新任务参与
     *
     * @param taskPatientDetailDO 任务参与对象
     * @return
     */
    public Envelop<Boolean> update(TaskPatientDetailDO taskPatientDetailDO){
        Envelop<Boolean> envelop = new Envelop<>();
        String sql =  ISqlUtils.getUpdateSql(taskPatientDetailDO);
        jdbcTemplate.update(sql);
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 我参与的活动
     *
     * @param taskPatientDetailDO 参与信息对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public Envelop<TaskPatientDetailDO> selectByPatient(TaskPatientDetailDO taskPatientDetailDO,Integer page,Integer size){
        String sql = new ISqlUtils().getSql(taskPatientDetailDO,page,size,"*");
        List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
        for (TaskPatientDetailDO taskPatientDetailDO1:taskPatientDetailDOS){
            TaskDO taskDO = taskDao.findOne(taskPatientDetailDO1.getTaskId());
            ActivityDO activityDO = activityDao.findOne(taskDO.getTransactionId());
            taskDO.setActivityDO(activityDO);
            taskPatientDetailDO1.setTaskDO(taskDO);
        }
        String sqlcount = new ISqlUtils().getSql(taskPatientDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskPatientDetailDOS,page,size,count);
    }
}
