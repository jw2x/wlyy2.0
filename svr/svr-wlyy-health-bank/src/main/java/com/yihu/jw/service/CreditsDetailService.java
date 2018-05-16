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
import java.util.Date;
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
        AccountDO accountDO1 = new AccountDO();
        accountDO1.setPatientId(creditsDetailDO.getPatientId());
        String sql1  = ISqlUtils.getAllSql(accountDO1);
        List<AccountDO>  accountDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(AccountDO.class));
        if (accountDOS == null || accountDOS.size() == 0){
            accountDO1.setTotal(0);
            accountDO1.setAccountName(creditsDetailDO.getName());
            accountDO1.setCardNumber("jw");
            accountDO1.setHospital("海沧区");
            accountDO1.setPassword("321321312321");
            accountDO1.setHospitalName("haichan");
            accountDO1.setCreateTime(new Date());
            accountDO1.setUpdateTime(new Date());
            accountDao.save(accountDO1);
        }
        List<AccountDO>  accountDOS1 = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(AccountDO.class));
        AccountDO accountDO = accountDOS1.get(0);
        String sql = "SELECT SUM(cd.integrate) as total FROM wlyy_health_bank_credits_detail cd where cd.trade_direction = "+creditsDetailDO.getTradeDirection() +" AND cd.patient_id = '" +creditsDetailDO.getPatientId()+"'";
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
        buffer.append(" ba.patient_id in(");
        if (patientIds == null || patientIds.size() == 0){
            buffer.append("''");
        }else {
            for (int i=0;i<patientIds.size();i++){
                buffer.append("'"+patientIds.get(i)+"'").append(",");
            }
            buffer.deleteCharAt(buffer.length()-1);
        }
        buffer.append(") ");
        String sql = "SELECT" +
                " ba.patient_id AS patient_id, " +
                " ba.account_name AS account_name," +
                " ba.hospital AS hospital, " +
                " ba.total AS total, " +
                " ba.create_time AS create_time, " +
                " (ba.total +(cd1.total)) AS sum " +
                " FROM " +
                " wlyy_health_bank_account ba LEFT JOIN " +
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
                " ) cd1  ON cd1.patient_id = ba.patient_id " +
                "WHERE " + buffer +
                " ORDER BY " +
                " ba.create_time, " +
                " (ba.total + cd1.total) DESC " +
                "LIMIT "+(page-1)*size+","+size;
        List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
        String sqlCount = "SELECT count(1) AS total"+
                " FROM " +
                " wlyy_health_bank_account ba LEFT JOIN " +
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
                " ) cd1 ON cd1.patient_id = ba.patient_id " +
                "WHERE " + buffer +
                " ORDER BY " +
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
        try {
            synchronized (creditsDetailDO.getPatientId()){
                String sqlAccount = "select * from wlyy_health_bank_account ba where ba.patient_id = '"+creditsDetailDO.getPatientId() +"'";
                List<AccountDO> accountDOList = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
                if (accountDOList != null && accountDOList.size() != 0){
                    creditsDetailDO.setAccountId(accountDOList.get(0).getId());
                }else {
                    AccountDO accountDO1 = new AccountDO();
                    accountDO1.setPatientId(creditsDetailDO.getPatientId());
                    accountDO1.setTotal(0);
                    accountDO1.setAccountName(creditsDetailDO.getName());
                    accountDO1.setCardNumber("jw");
                    accountDO1.setHospital("海沧区");
                    accountDO1.setPassword("321321312321");
                    accountDO1.setHospitalName("haichan");
                    accountDO1.setCreateTime(new Date());
                    accountDO1.setUpdateTime(new Date());
                    accountDao.save(accountDO1);
                    List<AccountDO> accountDOS = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
                    creditsDetailDO.setAccountId(accountDOS.get(0).getId());
                }
                TaskDO taskDO = new TaskDO();
                taskDO.setTaskCode(creditsDetailDO.getFlag());
                taskDO.setPatientId(creditsDetailDO.getPatientId());
                String sql = ISqlUtils.getSql(taskDO,1,1,"*");
                List<TaskDO> taskDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
                if (taskDOList != null && taskDOList.size() != 0){
                    creditsDetailDO.setTransactionId(taskDOList.get(0).getId());
                }else {
                    List<TaskDO> taskDOS = getTasks(taskDO.getPatientId());
                    for (TaskDO taskDO1:taskDOS){
                        taskDao.save(taskDO1);
                    }
                    List<TaskDO> taskDOList1 = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
                    creditsDetailDO.setTransactionId(taskDOList1.get(0).getId());
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
                        taskDetailDO.setCreateTime(new Date());
                        taskDetailDO.setUpdateTime(new Date());
                        taskDetailDao.save(taskDetailDO);
                    }
                }
                creditsDetailDO.setCreateTime(new Date());
                creditsDetailDO.setUpdateTime(new Date());
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
                AccountDO accountDO1 = accountDao.save(accountDO);
                List<CreditsDetailDO> creditsDetailDOS = new ArrayList<>();
                for (CreditsDetailDO creditsDetailDO2:creditsDetailDOList){
                    creditsDetailDO2.setTotal(accountDO1.getTotal());
                    creditsDetailDOS.add(creditsDetailDO2);
                }
                Envelop<CreditsDetailDO> envelop = new Envelop<>();
                envelop.setDetailModelList(creditsDetailDOS);
                return envelop;
            }
        }catch (Exception e){
            e.printStackTrace();
            Envelop<CreditsDetailDO> envelop = new Envelop<>();
            return envelop;
        }
    }


    /**
     * 固定数据
     *
     * @param patientId
     * @return
     */
    public List<TaskDO> getTasks(String patientId){
        List<TaskDO> taskDOList = new ArrayList<>();
        TaskDO taskDO = new TaskDO();
        taskDO.setPatientId(patientId);
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
        taskDO1.setPatientId(patientId);
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
    }

}
