package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.dao.ActivityDao;
import com.yihu.jw.dao.TaskDao;
import com.yihu.jw.dao.TaskPatientDetailDao;
import com.yihu.jw.dao.TaskRuleDao;
import com.yihu.jw.entity.health.bank.*;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.DateUtils;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-05-10 13:45
 * @desc task service
 **/
@Service
@Transactional
public class TaskService extends BaseJpaService<TaskDO,TaskDao> {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskPatientDetailDao taskPatientDetailDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private TaskRuleDao taskRuleDao;


    /**
     * 添加任务
     *
     * @param taskDO 任务对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(TaskDO taskDO){
        taskDO.setCreateTime(new Date());
        taskDO.setUpdateTime(new Date());
        taskDao.save(taskDO);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }

    /**
     *  更新任务
     *
     * @param taskDO
     * @return
     */
    public MixEnvelop<Boolean, Boolean> update(TaskDO taskDO){
        String sql = ISqlUtils.getUpdateSql(taskDO);
        jdbcTemplate.update(sql);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }


   /* public Envelop<CreditsDetailDO> findByCondition(TaskDO taskDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(taskDO,page,size,"*");
        List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        String sqlcount = new ISqlUtils().getSql(creditsDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, creditsDetailDOS,page,size,count);
    }*/

    /**
     * 查找数据
     *
     * @param taskDO 任务对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
   public MixEnvelop<TaskDO, TaskDO> selectByCondition(TaskDO taskDO, Integer page, Integer size){
       TaskDO taskDO2 = new TaskDO();
       String sql = new ISqlUtils().getSql(taskDO,page,size,"*");
       List<TaskDO> taskDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
       for (TaskDO taskDO1:taskDOS){
           if (taskDO1.getType()!= null && taskDO1.getType().equalsIgnoreCase("ACTIVITY_TASK")){
               ActivityDO activityDO = activityDao.findOne(taskDO1.getTransactionId());
               taskDO1.setActivityDO(activityDO);
           }
           TaskRuleDO taskRuleDO = taskRuleDao.findOne(taskDO1.getRuleCode());
           taskDO1.setRuleName(taskRuleDO.getName());
           //参与活动的详情
           String taskSql = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDO1.getId()+
                   "' and patient_openid = '"+taskDO.getOpenId()+"'";
           List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
           taskDO1.setTaskPatientDetailDOS(taskPatientDetailDOS);
           //参与人数
           String taskSql1 = "select count(1) as total from wlyy_health_bank_task_patient_detail where task_id = '"+taskDO1.getId()+"'";
           List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(taskSql1);
           Long count = 0L;
           if(rstotal!=null&&rstotal.size()>0){
               count = (Long) rstotal.get(0).get("total");
           }
           taskDO1.setTotal(count);
           taskDO1.setPatientId(taskDO.getPatientId());

       }
       String sqlcount = new ISqlUtils().getSql(taskDO,0,0,"count");
       List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
       Long count = 0L;
       if(rstotal!=null&&rstotal.size()>0){
           count = (Long) rstotal.get(0).get("total");
       }
       return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskDOS,page,size,count);
   }

    /**
     * 查看当前的任务
     * @param array taskCode集合
     * @param patientId 居民id
     * @param page 页码
     * @param size 分页大小
     * @return
     */
   public MixEnvelop<TaskDO, TaskDO> selectByTask(JSONArray array, String patientId, Integer page, Integer size){
       StringBuffer buffer = new StringBuffer();
       List<String> taskCodes = new ArrayList<>();
       for (int i=0;i<array.size();i++){
           taskCodes.add(array.getString(i));
       }
       buffer.append(" bt.task_code in(");
       if (taskCodes == null || taskCodes.size() == 0){
           buffer.append("''");
       }else {
           for (int i=0;i<taskCodes.size();i++){
               buffer.append("'"+taskCodes.get(i)+"'").append(",");
           }
           buffer.deleteCharAt(buffer.length()-1);
       }
       buffer.append(") ");
       String sql = "select * from wlyy_health_bank_task bt where "+buffer +" ORDER BY update_time DESC LIMIT "+(page-1)*size+","+size;
       List<TaskDO> taskDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
       for (TaskDO taskDO:taskDOList){
           if (taskDO.getType().equalsIgnoreCase("ACTIVITY_TASK")){
               ActivityDO activityDO = activityDao.findOne(taskDO.getTransactionId());
               taskDO.setActivityDO(activityDO);
           }
           if (taskDO.getPeriod() == 1){
               String taskSql = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDO.getId()+
                       "' and patient_id = '"+patientId+"'";
               List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
               taskDO.setTaskPatientDetailDOS(taskPatientDetailDOS);
           }else if (taskDO.getPeriod() == 0){
               String taskSql = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDO.getId()+
                       "' and patient_id = '"+patientId+"' and create_time > '" + DateUtils.getDayBegin() +"' and create_time < '"+ DateUtils.getDayEnd() +"'";
               List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
               taskDO.setTaskPatientDetailDOS(taskPatientDetailDOS);
           }
           taskDO.setPatientId(patientId);
       }
       String sqlcount = "select COUNT(1) AS  total from wlyy_health_bank_task bt where "+buffer ;
       List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
       Long count = 0L;
       if(rstotal!=null&&rstotal.size()>0){
           count = (Long) rstotal.get(0).get("total");
       }
       return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskDOList,page,size,count);
   }
   /* public List<TaskDO> getTasks(String patientId){
       List<TaskDO> taskDOList = new ArrayList<>();
       TaskDO taskDO = new TaskDO();
       *//*taskDO.setPatientId(patientId);*//*
       taskDO.setTaskCode("BIND");
       taskDO.setPeriod(1);
       taskDO.setTaskTitle("首次绑定");
       taskDO.setTaskContent("（使用社区发放的已关联您身份信息的设备,登录厦门i健康绑定设备）");
       taskDO.setTradeType("activity");
       taskDO.setTransactionId("402885e96324a409016324c0a45a0006");
       taskDO.setCreateTime(new Date());
       taskDO.setUpdateTime(new Date());
       taskDOList.add(taskDO);
       TaskDO taskDO1 = new TaskDO();
   *//*    taskDO1.setPatientId(patientId);*//*
       taskDO1.setTaskCode("MEASURE");
       taskDO1.setPeriod(0);
       taskDO1.setTaskTitle("每日测量");
       taskDO1.setTaskContent("（使用社区发放的已关联您身份信息的设备，绑定后每天完成测量）");
       taskDO1.setTradeType("activity");
       taskDO1.setTransactionId("402885e96324a409016324c0a45a0006");
       taskDO1.setCreateTime(new Date());
       taskDO1.setUpdateTime(new Date());
       taskDOList.add(taskDO1);
       return taskDOList;
    }*/

    /**
     * 删除任务
     *
     * @param ids taskId集合[]
     * @return
     */
    public MixEnvelop<Boolean, Boolean> batchDelete(List<String> ids){
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        for (int i =0;i<ids.size();i++){
            List<TaskPatientDetailDO> taskPatientDetailDOS = taskPatientDetailDao.selectByTaskId(ids.get(i));
            for(TaskPatientDetailDO taskPatientDetailDO:taskPatientDetailDOS){
                taskPatientDetailDO.setStatus(-1);
                taskPatientDetailDO.setCreateTime(new Date());
                taskPatientDetailDO.setUpdateTime(new Date());
                taskPatientDetailDao.save(taskPatientDetailDO);
            }
            TaskDO taskDO = taskDao.findOne(ids.get(i));
            taskDO.setStatus(0);
            taskDO.setCreateTime(new Date());
            taskDO.setUpdateTime(new Date());
            taskDao.save(taskDO);
        }
        return envelop;
    }


    /**
     * 查询某个时间任务的积分情况
     *
     * @param object{"taskCode":,"startTime":,"endTime":,"patientId":""}
     * @return
     */
    public MixEnvelop<TaskDO, TaskDO> selectByDate(JSONObject object){
        MixEnvelop<TaskDO, TaskDO> envelop = new MixEnvelop<>();
        String taskSql = "select * from wlyy_health_bank_task where task_code = '"+object.getString("taskCode")+ "'";
        List<TaskDO> taskDOList = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskDO.class));
        List<TaskDO> taskDOS = new ArrayList<>();
        if (taskDOList != null && taskDOList.size() != 0){
            TaskDO taskDO = taskDOList.get(0);
            String creditSql = "select * from wlyy_health_bank_credits_detail where transaction_id ='"+taskDO.getId()+
                    "' and DATE_FORMAT(create_time,'%Y-%m-%d') >= '"+object.getString("startTime")+"' and DATE_FORMAT(create_time,'%Y-%m-%d') <= '" +object.getString("endTime")+"'"
                    +" and patient_id = '"+object.getString("patientId")+"'";
            List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(creditSql,new BeanPropertyRowMapper(CreditsDetailDO.class));
            taskDO.setCreditsDetailDOS(creditsDetailDOS);
            taskDOS.add(taskDO);
        }
        envelop.setDetailModelList(taskDOS);
        return envelop;

    }
}
