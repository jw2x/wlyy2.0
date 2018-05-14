package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.*;
import com.yihu.jw.entity.health.bank.*;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-04-27 16:53
 * @desc credits log info Service
 **/
@Service
@Transactional
public class CreditsDetailService extends BaseJpaService<CreditsDetailDO,CredittsLogDetailDao> {


    @Autowired
    private CredittsLogDetailDao credittsLogDetailDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TaskDetailDao taskDetailDao;
   /**
     *  find creditsLogInfo
     *
     * @return
     * @throws ParseException
     */
    public Envelop<CreditsDetailDO> findByCondition(CreditsDetailDO creditsDetailDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(creditsDetailDO,page,size,"*");
        List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        for (CreditsDetailDO creditsDetailDO1 : creditsDetailDOS){
            if (creditsDetailDO1.getTradeType().equalsIgnoreCase("HEALTH_TASK")){
                TaskDO taskDO = taskDao.findOne(creditsDetailDO1.getTransactionId());
                creditsDetailDO1.setTaskDO(taskDO);
            }else if(creditsDetailDO1.getTradeType().equalsIgnoreCase("HEALTH_ACTIVITY")){
                ActivityDO activityDO = activityDao.findOne(creditsDetailDO1.getTransactionId());
                creditsDetailDO1.setActivityDO(activityDO);
            }
        }
        String sqlcount = new ISqlUtils().getSql(creditsDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, creditsDetailDOS,page,size,count);
    }


    /**
     * 获取账户信息
     *
     * @param creditsDetailDO
     * @return
     */
    public Envelop<AccountDO> findByTradeDirection(CreditsDetailDO creditsDetailDO){
        AccountDO accountDO = accountDao.findOne(creditsDetailDO.getAccountId());
        String sql = "SELECT SUM(cd.integrate) as total FROM wlyy_health_bank_credits_detail cd where cd.trade_direction = "+creditsDetailDO.getTradeDirection();
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        if (rstotal!= null && rstotal.size()>0){
            if (rstotal.get(0).get("total") == null){
                accountDO.setUsedTotal(0);
            }else {
                accountDO.setUsedTotal(Integer.parseInt(rstotal.get(0).get("total").toString()));
            }
        }
        return Envelop.getSuccess(HealthBankMapping.api_success,accountDO);
    }





    /*
    public Envelop<Boolean> exchangeGoods(GoodsDO goodsDO){
        CreditsDetailDO creditsLogDetailDO = new CreditsDetailDO();
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }



*/
    public Envelop<AccountDO> selectByRanking(List<String> patientIds, Integer page, Integer size){
        StringBuffer buffer = new StringBuffer();
        if (patientIds !=null && patientIds.size()!=0){
            buffer.append(" ba.patient_id in(");
            for (int i=0;i<patientIds.size();i++){
                buffer.append("'"+patientIds.get(i)+"'").append(",");
            }
            buffer.append(")");
            buffer.deleteCharAt(buffer.length()-2);
        }
        String sql = "SELECT" +
                " ba.patient_id AS patient_id, " +
                " ba.account_name AS account_name," +
                " ba.hospital AS hospital, " +
                " ba.total AS total, " +
                " ba.create_time AS create_time, " +
                " (ba.total +(cd1.total)) AS sum " +
                " FROM " +
                " wlyy_health_bank_account ba, " +
                " ( " +
                " SELECT " +
                " SUM(cd.integrate) AS total, " +
                " cd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_credits_detail cd " +
                " WHERE " +
                " cd.trade_direction = - 1 " +
                " GROUP BY " +
                " cd.patient_id " +
                " ) cd1 " +
                "WHERE " + buffer +
                "AND cd1.patient_id = ba.patient_id " +
                "ORDER BY " +
                " ba.create_time, " +
                " (ba.total + cd1.total) DESC " +
                "LIMIT "+(page-1)*size+","+size;
        List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
        String sqlCount = "SELECT count(1) AS total"+
                " FROM " +
                " wlyy_health_bank_account ba, " +
                " ( " +
                " SELECT " +
                " SUM(cd.integrate) AS total, " +
                " cd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_credits_detail cd " +
                " WHERE " +
                " cd.trade_direction = - 1 " +
                " GROUP BY " +
                " cd.patient_id " +
                " ) cd1 " +
                "WHERE " + buffer +
                "AND cd1.patient_id = ba.patient_id " +
                "ORDER BY " +
                " ba.create_time, " +
                " (ba.total + cd1.total) DESC ";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlCount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, accountDOS,page,size,count);
    }

    /**
     * 添加积分
     *
     * @param creditsDetailDO
     * @return
     */
    public Envelop<CreditsDetailDO> insert(CreditsDetailDO creditsDetailDO){
        TaskDO taskDO = new TaskDO();
        taskDO.setTaskCode(creditsDetailDO.getFlag());
        taskDO.setPatientId(creditsDetailDO.getPatientId());
        String sql = ISqlUtils.getSql(taskDO,1,1,"*");
        List<TaskDO> taskDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
        if (taskDOList != null && taskDOList.size() != 0){
            creditsDetailDO.setTransactionId(taskDOList.get(0).getId());
        }
        String sqlAccount = "select * from wlyy_health_bank_account ba where ba.patient_id = '"+creditsDetailDO.getPatientId() +"'";
        List<AccountDO> accountDOList = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
        if (accountDOList != null && accountDOList.size() != 0){
            creditsDetailDO.setAccountId(accountDOList.get(0).getId());
        }
        if (creditsDetailDO.getTradeDirection() == 1){
            if (creditsDetailDO.getTradeType().equals("HEALTH_TASK")){
                TaskDetailDO taskDetailDO = new TaskDetailDO();
                taskDetailDO.setIntegrate(creditsDetailDO.getIntegrate());
                taskDetailDO.setTaskId(creditsDetailDO.getTransactionId());
                taskDetailDO.setSaasId(creditsDetailDO.getSaasId());
                taskDetailDO.setPatientId(creditsDetailDO.getPatientId());
                taskDetailDO.setTradeDirection(creditsDetailDO.getTradeDirection());
                taskDetailDO.setStatus("1");
                taskDetailDao.save(taskDetailDO);
            }
        }
        CreditsDetailDO creditsDetailDO1 =credittsLogDetailDao.save(creditsDetailDO);
        creditsDetailDO1.setFlag(creditsDetailDO.getFlag());
        List<CreditsDetailDO> creditsDetailDOList = new ArrayList<>();
        creditsDetailDOList.add(creditsDetailDO1);
        AccountDO accountDO = accountDao.findOne(creditsDetailDO1.getAccountId());
        if (creditsDetailDO1.getTradeDirection() == 1){
            accountDO.setTotal(accountDO.getTotal()+creditsDetailDO1.getIntegrate());
        }else if (creditsDetailDO.getTradeDirection() == -1){
            accountDO.setTotal(accountDO.getTotal()-creditsDetailDO1.getIntegrate());
        }
        accountDao.save(accountDO);
        Envelop<CreditsDetailDO> envelop = new Envelop<>();
        envelop.setDetailModelList(creditsDetailDOList);
        return envelop;
    }

}
