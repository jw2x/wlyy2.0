package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.CredittsLogDetailDao;
import com.yihu.jw.dao.TaskDetailDao;
import com.yihu.jw.entity.health.bank.TaskDetailDO;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;

/**
 * @author wangzhinan
 * @create 2018-04-27 9:35
 * @desc health blank Service
 **/
@Service
@Transactional
public class TaskDetailService extends BaseJpaService<TaskDetailDO,TaskDetailDao> {
    @Autowired
    private TaskDetailDao taskDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CredittsLogDetailDao credittsLogDetailDao;


    /**
     * 参与任务
     *
     * @param taskDetailDO
     * @return
     */
    public Envelop<Boolean> insert(TaskDetailDO taskDetailDO){
        taskDetailDao.save(taskDetailDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }
   /* *//**
     * insert health task
     *
     * @param taskDetailDO
     * @return
     *//*
    public Envelop<Boolean> insert(TaskDetailDO taskDetailDO){
        taskDetailDao.save(taskDetailDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }

    *//**
     *  patient or doctor find health task
     *
     * @param patientId
     * @param page
     * @param size
     * @return
     * @throws ParseException
     *//*
    public Envelop<TaskDetailDO> findTaskByPatient(String patientId, String doctorId, Integer page, Integer size) throws ParseException {
        TaskDetailDO taskDetailDO = new TaskDetailDO();
        taskDetailDO.setDoctorId(doctorId);
        taskDetailDO.setPatientId(patientId);
        String sql = new ISqlUtils().getSql(taskDetailDO,page,size,"*");
        List<TaskDetailDO> taskDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDetailDO.class));

        String sqlcount = new ISqlUtils().getSql(taskDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, taskDetailDOS,page,size,count);
    }

    *//**
     * 修改健康任务
     *
     * @param taskDetailDO
     * @return
     *//*
    public Envelop<Boolean> update(TaskDetailDO taskDetailDO){
        TaskDetailDO taskDetailDO1 = taskDetailDao.findOne(taskDetailDO.getId());
        String sql = "select cl.status as status,cl.total as total from health_bank_credits_log cl order by cl.update_time desc";
        List<CreditsDetailDO> creditsLogDetailDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        taskDetailDao.save(taskDetailDO);
        if (taskDetailDO.getStatus().equalsIgnoreCase("finished")&&!taskDetailDO1.getStatus().equalsIgnoreCase("unfinished")){
            CreditsDetailDO creditsLogDetailDO = new CreditsDetailDO();
            if (creditsLogDetailDOList == null || creditsLogDetailDOList.size() ==0){
                creditsLogDetailDO.setTotal(Long.parseLong(taskDetailDO.getIntegrate()));
            }else {
                creditsLogDetailDO.setTotal(Long.parseLong(taskDetailDO.getIntegrate().replace("+",""))+ creditsLogDetailDOList.get(0).getTotal());
            }
            creditsLogDetailDO.setPatientId(taskDetailDO.getPatientId());
            creditsLogDetailDO.setIntegrate("+"+ taskDetailDO.getIntegrate());
            creditsLogDetailDO.setIntegrateType("HEALTH_TASK");
            creditsLogDetailDO.setIntegrateId(taskDetailDO.getId());
            creditsLogDetailDO.setStatus("unused");
            credittsLogDetailDao.save(creditsLogDetailDO);
        }
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }*/
}
