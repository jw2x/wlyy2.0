package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.TaskDao;
import com.yihu.jw.dao.TaskDetailDao;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.entity.health.bank.TaskDetailDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.DateUtils;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-05-10 13:45
 * @desc task service
 **/
@Service
@Transactional
public class TaskService extends BaseJpaService<TaskDO,TaskDao>{

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskDetailDao taskDetailDao;

    public Envelop<Boolean> insert(TaskDO taskDO){
        taskDao.save(taskDO);
        Envelop<Boolean> envelop = new Envelop<>();
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

   public Envelop<TaskDO> selectByCondition(TaskDO taskDO,Integer page,Integer size){
       String sql = new ISqlUtils().getSql(taskDO,page,size,"*");
       List<TaskDO> taskDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
       List<TaskDO> taskDOList = new ArrayList<>();
       for (TaskDO taskDO1 : taskDOS){
           if (taskDO1.getPeriod() == 0){
               String taskSql1 = "SELECT * FROM wlyy_health_bank_task_detail td WHERE" +
                       " td.task_id = '" +taskDO1.getId()+
                       "' AND td.create_time > '" + DateUtils.getDayBegin()+
                       "' AND td.create_time < '"+DateUtils.getDayEnd()+"'";
               List<TaskDetailDO> taskDetailList = jdbcTemplate.query(taskSql1,new BeanPropertyRowMapper(TaskDetailDO.class));
               if (taskDetailList != null && taskDetailList.size() != 0){
                   taskDO1.setStatus(1);
               }
               taskDOList.add(taskDO1);
           }
       }
       String sqlcount = new ISqlUtils().getSql(taskDO,0,0,"count");
       List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
       Long count = 0L;
       if(rstotal!=null&&rstotal.size()>0){
           count = (Long) rstotal.get(0).get("total");
       }
       return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskDOList,page,size,count);
   }
}
