package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.dao.CredittsLogDetailDao;
import com.yihu.jw.dao.TaskPatientDetailDao;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.mysql.query.BaseJpaService;
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
public class TaskDetailService extends BaseJpaService<TaskPatientDetailDO,TaskPatientDetailDao> {
    @Autowired
    private TaskPatientDetailDao taskPatientDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CredittsLogDetailDao credittsLogDetailDao;


    /**
     * 参与任务
     *
     * @param taskPatientDetailDO
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(TaskPatientDetailDO taskPatientDetailDO){
        taskPatientDetailDao.save(taskPatientDetailDO);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }
   /* *//**
     * insert health task
     *
     * @param taskDetailDO
     * @return
     *//*
    public Envelop<Boolean> insert(TaskPatientDetailDO taskDetailDO){
        taskPatientDetailDao.save(taskDetailDO);
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
    public Envelop<TaskPatientDetailDO> findTaskByPatient(String patientId, String doctorId, Integer page, Integer size) throws ParseException {
        TaskPatientDetailDO taskDetailDO = new TaskPatientDetailDO();
        taskDetailDO.setDoctorId(doctorId);
        taskDetailDO.setPatientId(patientId);
        String sql = new ISqlUtils().getSql(taskDetailDO,page,size,"*");
        List<TaskPatientDetailDO> taskDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));

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
    public Envelop<Boolean> update(TaskPatientDetailDO taskDetailDO){
        TaskPatientDetailDO taskDetailDO1 = taskPatientDetailDao.findOne(taskDetailDO.getId());
        String sql = "select cl.status as status,cl.total as total from health_bank_credits_log cl order by cl.update_time desc";
        List<CreditsDetailDO> creditsLogDetailDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        taskPatientDetailDao.save(taskDetailDO);
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
