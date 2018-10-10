package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.dao.*;
import com.yihu.jw.entity.health.bank.*;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.DateUtils;
import com.yihu.jw.util.ISqlUtils;
import com.yihu.mysql.query.BaseJpaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangzhinan
 * @create 2018-04-27 16:53
 * @desc credits log info Service
 **/
@Service
@Transactional
public class CreditsDetailService extends BaseJpaService<CreditsDetailDO,CredittsLogDetailDao> {

    private Logger logger = LoggerFactory.getLogger(CreditsDetailService.class);

    private static String STEP = "health:blank:step";
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
    private TaskPatientDetailDao taskPatientDetailDao;
    @Autowired
    private TaskRuleDao taskRuleDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ActiveRecordService activeRecordService;

   /**
     *  find creditsLogInfo
     *
     * @return
     * @throws ParseException
     */
   public MixEnvelop<CreditsDetailDO, CreditsDetailDO> findByCondition(CreditsDetailDO creditsDetailDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(creditsDetailDO,page,size,"*");
        List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        for (CreditsDetailDO creditsDetailDO1 : creditsDetailDOS){
            if (creditsDetailDO1.getTradeType() != null && creditsDetailDO1.getTradeType().equalsIgnoreCase("ACTIVITY_TASK")){
                TaskDO taskDO = taskDao.findOne(creditsDetailDO1.getTransactionId());
                creditsDetailDO1.setTaskDO(taskDO);
            }
        }
        String sqlcount = new ISqlUtils().getSql(creditsDetailDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, creditsDetailDOS,page,size,count);
    }


    /**
     * 获取账户信息
     *
     * @param creditsDetailDO
     * @return
     */
    public MixEnvelop<AccountDO, AccountDO> findByTradeDirection(CreditsDetailDO creditsDetailDO){
        AccountDO accountDO1 = new AccountDO();
        accountDO1.setPatientId(creditsDetailDO.getPatientId());
        String sql1  = ISqlUtils.getAllSql(accountDO1);
        List<AccountDO>  accountDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(AccountDO.class));
        if (accountDOS == null || accountDOS.size() == 0){
            accountDO1.setTotal(0);
            accountDO1.setAccountName(creditsDetailDO.getName());
            if(creditsDetailDO.getIdCard().length()>=4){// 判断是否长度大于等于4
                String cardNumber=creditsDetailDO.getIdCard().substring(creditsDetailDO.getIdCard().length()- 4,creditsDetailDO.getIdCard().length());//截取两个数字之间的部分
                int random = (int)((Math.random()*9+1)*100000);
                accountDO1.setCardNumber(cardNumber+Integer.toString(random));
            }
            accountDO1.setHospital(creditsDetailDO.getHospital());
            accountDO1.setPassword("123456");
            accountDO1.setHospitalName(creditsDetailDO.getHospitalName());
            accountDO1.setStatus(1);
            accountDO1.setSaasId("dev");
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
        return MixEnvelop.getSuccess(HealthBankMapping.api_success,accountDO);
    }




    /*
    public Envelop<Boolean> exchangeGoods(GoodsDO goodsDO){
        CreditsDetailDO creditsLogDetailDO = new CreditsDetailDO();
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }



*/
    public MixEnvelop<AccountDO, AccountDO> selectByRanking(List<String> patientIds, Integer page, Integer size){
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
        String sql =
                "SELECT ba1.patient_id AS patient_id," +
                        "ba1.account_name AS account_name," +
                        "ba1.hospital AS hospital," +
                        "ba1.total AS total," +
                        "ba1.create_time AS create_time," +
                        "ba1.sum AS sum" +
                        " FROM" +
                        "( SELECT " +
                        "ba.patient_id AS patient_id," +
                        "ba.account_name AS account_name," +
                        "ba.hospital AS hospital," +
                        "ba.total AS total," +
                        "ba.create_time AS create_time," +
                        " if(ba.total=0,ba.total,(ba.total +COALESCE((cd1.total),0))) AS sum" +
                        " FROM" +
                        " wlyy_health_bank_account ba" +
                        " LEFT JOIN ( " +
                        "SELECT" +
                        " SUM(cd.integrate) AS total," +
                        " cd.patient_id AS patient_id" +
                        " FROM" +
                        " wlyy_health_bank_credits_detail cd" +
                        " WHERE " +
                        "cd.trade_direction = - 1" +
                        " GROUP BY " +
                        " cd.patient_id ) cd1 ON cd1.patient_id = ba.patient_id " +
                        " WHERE " + buffer +
                        " ORDER BY" +
                        " ba.create_time DESC )ba1" +
                        " ORDER BY " +
                        " ba1.total DESC"+
                        " LIMIT "+(page-1)*size+","+size ;
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
                " (ba.total + COALESCE(cd1.total,0)) DESC ";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlCount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, accountDOS,page,size,count);
    }

    /**
     * 添加积分
     *
     * @param creditsDetailDO
     * @return
     */

    public MixEnvelop<CreditsDetailDO, CreditsDetailDO> insert(CreditsDetailDO creditsDetailDO){
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
                    if(creditsDetailDO.getIdCard().length()>=4){// 判断是否长度大于等于4
                        String cardNumber=creditsDetailDO.getIdCard().substring(creditsDetailDO.getIdCard().length()- 4,creditsDetailDO.getIdCard().length());//截取两个数字之间的部分
                        int random = (int)((Math.random()*9+1)*100000);
                        accountDO1.setCardNumber(cardNumber+Integer.toString(random));
                    }
                    accountDO1.setHospital(creditsDetailDO.getHospital());
                    accountDO1.setPassword("123456");
                    accountDO1.setHospitalName(creditsDetailDO.getHospitalName());
                    accountDO1.setStatus(1);
                    accountDO1.setCreateTime(new Date());
                    accountDO1.setUpdateTime(new Date());
                    accountDao.save(accountDO1);
                    List<AccountDO> accountDOS = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
                    creditsDetailDO.setAccountId(accountDOS.get(0).getId());
                }
                TaskDO taskDO = new TaskDO();
                taskDO.setTaskCode(creditsDetailDO.getFlag());
                taskDO.setId(creditsDetailDO.getTransactionId());
                /*taskDO.setPatientId(creditsDetailDO.getPatientId());*/
                String sql = ISqlUtils.getSql(taskDO,1,1,"*");
                List<TaskDO> taskDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskDO.class));
                creditsDetailDO.setTransactionId(taskDOList.get(0).getId());
                String ruleSql = "SELECT * FROM wlyy_health_bank_task_rule WHERE id= '"+taskDOList.get(0).getRuleCode()+"'";
                List<TaskRuleDO> taskRuleDOS = jdbcTemplate.query(ruleSql,new BeanPropertyRowMapper(TaskRuleDO.class));
                TaskRuleDO taskRuleDO = taskRuleDOS.get(0);
                creditsDetailDO.setIntegrate(taskRuleDO.getIntegrate());
                creditsDetailDO.setTradeDirection(taskRuleDO.getTradeDirection());
                String taskSql = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDOList.get(0).getId()+"' and patient_id = '" + creditsDetailDO.getPatientId() +"'";
                List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                if (taskPatientDetailDOS == null || taskPatientDetailDOS.size() ==0){
                    TaskPatientDetailDO taskPatientDetailDO = new TaskPatientDetailDO();
                    taskPatientDetailDO.setTaskId(creditsDetailDO.getTransactionId());
                    taskPatientDetailDO.setSaasId(creditsDetailDO.getSaasId());
                    taskPatientDetailDO.setPatientId(creditsDetailDO.getPatientId());
                    taskPatientDetailDO.setPatientIdcard(creditsDetailDO.getIdCard());
                    taskPatientDetailDO.setPatientOpenid(creditsDetailDO.getOpenId());
                    taskPatientDetailDO.setUnionId(creditsDetailDO.getUnionId());
                    taskPatientDetailDO.setStatus(Integer.parseInt("0"));
                    taskPatientDetailDO.setCreateTime(new Date());
                    taskPatientDetailDO.setUpdateTime(new Date());
                    taskPatientDetailDO.setActivityId(taskDOList.get(0).getTransactionId());
                    taskPatientDetailDO.setTotal(Long.parseLong("0"));
                    taskPatientDetailDao.save(taskPatientDetailDO);
                }else if (taskPatientDetailDOS != null && taskDOList.get(0).getPeriod() == 0){
                    String taskSql1 = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDOList.get(0).getId()+
                            "' and patient_id = '"+creditsDetailDO.getPatientId()+"' and create_time > '" + DateUtils.getDayBegin() +"' and create_time < '"+ DateUtils.getDayEnd() +"'";
                    List<TaskPatientDetailDO> taskPatientDetailDOS1 = jdbcTemplate.query(taskSql1,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                    if (taskPatientDetailDOS1 == null || taskPatientDetailDOS1.size() == 0){
                        TaskPatientDetailDO taskPatientDetailDO = new TaskPatientDetailDO();
                        taskPatientDetailDO.setTaskId(creditsDetailDO.getTransactionId());
                        taskPatientDetailDO.setSaasId(creditsDetailDO.getSaasId());
                        taskPatientDetailDO.setPatientId(creditsDetailDO.getPatientId());
                        taskPatientDetailDO.setPatientIdcard(creditsDetailDO.getIdCard());
                        taskPatientDetailDO.setPatientOpenid(creditsDetailDO.getOpenId());
                        taskPatientDetailDO.setActivityId(taskDOList.get(0).getTransactionId());
                        taskPatientDetailDO.setStatus(Integer.parseInt("0"));
                        taskPatientDetailDO.setCreateTime(new Date());
                        taskPatientDetailDO.setUpdateTime(new Date());
                        taskPatientDetailDO.setTotal(Long.parseLong("0"));
                        taskPatientDetailDao.save(taskPatientDetailDO);
                    }
                }
                creditsDetailDO.setCreateTime(new Date());
                creditsDetailDO.setUpdateTime(new Date());
                CreditsDetailDO creditsDetailDO1 =credittsLogDetailDao.save(creditsDetailDO);
                creditsDetailDO1.setFlag(creditsDetailDO.getFlag());
                List<CreditsDetailDO> creditsDetailDOList = new ArrayList<>();
                creditsDetailDOList.add(creditsDetailDO1);
                TaskPatientDetailDO taskPatientDetailDO = new TaskPatientDetailDO();
                taskPatientDetailDO.setPatientId(creditsDetailDO1.getPatientId());
                taskPatientDetailDO.setTaskId(creditsDetailDO1.getTransactionId());
                String taskSql1 = ISqlUtils.getAllSql(taskPatientDetailDO);
                List<TaskPatientDetailDO> taskPatientDetailDOS1 = jdbcTemplate.query(taskSql1,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                TaskPatientDetailDO taskPatientDetailDO1 = taskPatientDetailDOS1.get(0);
                if (creditsDetailDO1.getTradeDirection() == 1){
                    taskPatientDetailDO1.setTotal(taskPatientDetailDO1.getTotal()+creditsDetailDO1.getIntegrate());
                }else if (creditsDetailDO.getTradeDirection() == -1){
                    taskPatientDetailDO1.setTotal(taskPatientDetailDO1.getTotal()-creditsDetailDO1.getIntegrate());
                }
                taskPatientDetailDao.save(taskPatientDetailDO1);
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
                MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
                envelop.setDetailModelList(creditsDetailDOS);
                return envelop;
            }
        }catch (Exception e){
            e.printStackTrace();
            MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
            return envelop;
        }
    }

    /**
     * 活动排名
     *
     * @param activityId 活动id
     * @param ids 微信编码
     *
     * @param page 页码
     *
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<TaskPatientDetailDO, TaskPatientDetailDO> selectByActivityRanking(String activityId, List<String> ids, Integer page, Integer size){
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        if (ids == null || ids.size() == 0){
            buffer.append("''");
        }else {
            for (int i=0;i<ids.size();i++){
                buffer.append("'"+ids.get(i)+"'").append(",");
            }
            buffer.deleteCharAt(buffer.length()-1);
        }
        buffer.append(") ");
        String sql = "SELECT " +
                " * " +
                "FROM " +
                " ( " +
                " SELECT " +
                " SUM(ptpd.total) AS total, " +
                " ptpd.patient_openid AS patient_openid, " +
                " ptpd.task_id AS task_id, " +
                " ptpd.activity_id AS activity_id, " +
                " ptpd.create_time as create_time, " +
                " ptpd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_task_patient_detail ptpd " +
                " WHERE " +
                " activity_id = '" + activityId +
                "' GROUP BY " +
                " patient_id " +
                " ORDER BY ptpd.create_time DESC " +
                " )btpd1 " +
                " WHERE  patient_id IN "+buffer+
                " ORDER BY btpd1.total DESC "+" LIMIT " + (page-1)*size+","+size;
        List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
        for (TaskPatientDetailDO taskPatientDetailDO : taskPatientDetailDOS){
            String accountSql = "select * from wlyy_health_bank_account where patient_id = '"+taskPatientDetailDO.getPatientId()+"'";
            List<AccountDO> accountDOS = jdbcTemplate.query(accountSql,new BeanPropertyRowMapper(AccountDO.class));
            taskPatientDetailDO.setAccountDO(accountDOS.get(0));
        }
        String sqlCount =  "SELECT " +
                " count(1) AS total " +
                "FROM " +
                " ( " +
                " SELECT " +
                " SUM(ptpd.total) AS total, " +
                " ptpd.patient_openid AS patient_openid, " +
                " ptpd.task_id AS task_id, " +
                " ptpd.activity_id AS activity_id, " +
                " ptpd.create_time as create_time, " +
                " ptpd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_task_patient_detail ptpd " +
                " WHERE " +
                " activity_id = '" + activityId+
                "' GROUP BY " +
                " patient_openid " +
                " ORDER BY ptpd.create_time DESC " +
                " )btpd1 " +
                "WHERE  patient_openid IN "+buffer+
                " ORDER BY btpd1.total DESC ";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlCount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskPatientDetailDOS,page,size,count);

    }



    /**
     * 根据活动查询积分
     *
     * @param activityId 活动id
     *
     * @param patientId 居民id
     *
     * @param page 页码
     *
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<CreditsDetailDO, CreditsDetailDO> selectByActivity(String activityId, String patientId, Integer page, Integer size){
        String sql="SELECT * " +
                "FROM " +
                " wlyy_health_bank_credits_detail " +
                "WHERE" +
                " transaction_id IN ( " +
                " SELECT " +
                " bt.id " +
                " FROM " +
                " wlyy_health_bank_task bt " +
                " WHERE " +
                " transaction_id = '"+activityId +"' " +
                " ) " +
                " and patient_id = '" +patientId+
                "' LIMIT "+(page-1)*size +","+size;
        List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        for (CreditsDetailDO creditsDetailDO : creditsDetailDOS){
            TaskDO taskDO = taskDao.findOne(creditsDetailDO.getTransactionId());
            creditsDetailDO.setTaskDO(taskDO);
        }
        String sqlcount = "SELECT count(1) AS" +
                " total FROM " +
                " wlyy_health_bank_credits_detail " +
                "WHERE" +
                " transaction_id IN ( " +
                " SELECT " +
                " bt.id " +
                " FROM " +
                " wlyy_health_bank_task bt " +
                " WHERE " +
                " transaction_id = '"+activityId +"' " +
                " ) " +
                " and patient_id = '" +patientId+
                "'";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success,creditsDetailDOS,page,size,count);
    }


    /**
     * 根据活动查找全部排行
     *
     * @param activityId 活动id
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<TaskPatientDetailDO, TaskPatientDetailDO> selectByActivityRanking1(String activityId, String patientId, Integer page, Integer size){
        String sql = "SELECT " +
                " * " +
                "FROM " +
                " ( " +
                " SELECT " +
                " SUM(ptpd.total) AS total, " +
                " ptpd.patient_openid AS patient_openid, " +
                " ptpd.task_id AS task_id, " +
                " ptpd.activity_id AS activity_id, " +
                " ptpd.create_time as create_time, " +
                " ptpd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_task_patient_detail ptpd " +
                " WHERE " +
                " activity_id = '" + activityId +
                "' GROUP BY " +
                " patient_openid " +
                " ORDER BY ptpd.create_time DESC " +
                " )btpd1 " +
                " ORDER BY btpd1.total DESC "+" LIMIT " + (page-1)*size+","+size;
        List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
        logger.info(taskPatientDetailDOS.toString()+""+taskPatientDetailDOS.size());
        for (int i = 0;taskPatientDetailDOS != null&&taskPatientDetailDOS.size()!=0 && i<taskPatientDetailDOS.size();i++){
            TaskPatientDetailDO taskPatientDetailDO = taskPatientDetailDOS.get(i);
            String accountSql = "select * from wlyy_health_bank_account where patient_id = '"+taskPatientDetailDO.getPatientId()+"'";
            List<AccountDO> accountDOS = jdbcTemplate.query(accountSql,new BeanPropertyRowMapper(AccountDO.class));
            if (taskPatientDetailDOS.get(i).getPatientId().equalsIgnoreCase(patientId)){
                /*String taskSql = "select count(1)+1 as total from (" +
                        "select *  from (SELECT " +
                        " SUM(ptpd.total) AS total, " +
                        " ptpd.patient_openid AS patient_openid, " +
                        " ptpd.task_id AS task_id, " +
                        " ptpd.activity_id AS activity_id, " +
                        " ptpd.create_time as create_time, " +
                        " ptpd.patient_id AS patient_id " +
                        " FROM " +
                        " wlyy_health_bank_task_patient_detail ptpd " +
                        " WHERE " +
                        " activity_id = '" + activityId + "')ptpd1 where" +
                        " ptpd1.patient_id = '"+patientId+"' AND ptpd1.total > "+taskPatientDetailDOS.get(i).getTotal()+") ptpd2";
                List<Map<String,Object>> rstotal1 = jdbcTemplate.queryForList(taskSql);
                Long count = 0L;
                if(rstotal1!=null&&rstotal1.size()>0){
                    count = (Long) rstotal1.get(0).get("total");
                }
                accountDOS.get(0).setActivityRanking(count);*/
                taskPatientDetailDO.setIsFlag(1);
            }else {
                taskPatientDetailDO.setIsFlag(0);
            }
            taskPatientDetailDO.setAccountDO(accountDOS.get(0));
        }
        String sqlCount =  "SELECT " +
                " count(1) AS total " +
                "FROM " +
                " ( " +
                " SELECT " +
                " SUM(ptpd.total) AS total, " +
                " ptpd.patient_openid AS patient_openid, " +
                " ptpd.task_id AS task_id, " +
                " ptpd.activity_id AS activity_id, " +
                " ptpd.create_time as create_time, " +
                " ptpd.patient_id AS patient_id " +
                " FROM " +
                " wlyy_health_bank_task_patient_detail ptpd " +
                " WHERE " +
                " activity_id = '" + activityId+
                "' GROUP BY " +
                " patient_openid " +
                " ORDER BY ptpd.create_time DESC " +
                " )btpd1 " +
                " ORDER BY btpd1.total DESC ";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlCount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success, taskPatientDetailDOS,page,size,count);

    }


    /**
     * 医生主动加分
     *
     * @param array 居民信息集合
     *
     * @param ruleId 规则id
     * @return
     */
    public MixEnvelop<Boolean, Boolean> doctorAddIntegrate(JSONArray array, String ruleId, String description) throws Exception {
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        for (int i=0;i<array.size();i++){
            TaskRuleDO taskRuleDO = taskRuleDao.findOne(ruleId);
            JSONObject object = array.getJSONObject(i);
            String patientId = object.getString("code");
            String idCard = object.getString("idcard");
            String unionId = object.getString("unionid");
            String openId = object.getString("openid");
            String hospital = object.getString("hospital");
            String hospitalName = object.getString("hospitalName");
            String taskSql = "select * from wlyy_health_bank_task bt where type = 'RULE_TASK' AND transaction_id = '"+ruleId +"'";
            List<TaskDO> taskDOList = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskDO.class));
            TaskDO taskDO1 = new TaskDO();
            if (taskDOList.isEmpty() && taskDOList.size() == 0){
                TaskDO taskDO = new TaskDO();
                taskDO.setTransactionId(taskRuleDO.getId());
                taskDO.setTaskCode("RULE");
                taskDO.setStatus(1);
                taskDO.setRuleCode(taskRuleDO.getId());
                taskDO.setType("RULE_TASK");
                taskDO.setCreateTime(new Date());
                taskDO.setUpdateTime(new Date());
                taskDO.setPeriod(taskRuleDO.getPeriod());
                taskDO.setSaasId("dev");
                taskDO.setTitle(taskRuleDO.getDescription());
                taskDO.setContent(taskRuleDO.getDescription());
                taskDO1 = taskDao.save(taskDO);
            }else {
                taskDO1 = taskDOList.get(0);
            }
            if (taskRuleDO.getPeriod() == 1){
                    String sql = "select * from wlyy_health_bank_task_patient_detail where patient_id = '"+patientId+"'AND task_id ='"+taskDO1.getId()+"'";
                    List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                    if (taskPatientDetailDOS != null && taskPatientDetailDOS.size() != 0){
                        throw new Exception("已奖励过");
                    }
            }else if (taskRuleDO.getPeriod() == 0){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String date1 = dateFormat.format(date);
                    String begin = DateUtils.getMinMonthDate(date1);
                    String end = DateUtils.getMaxMonthDate(date1);
                    String sql = "select * from wlyy_health_bank_task_patient_detail where patient_id = '"+patientId+"'AND task_id ='"+taskDO1.getId()+"' AND create_time > '"+begin+"' AND create_time < '"+end+"'";
                    List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                    if (taskPatientDetailDOS != null && taskPatientDetailDOS.size() != 0){
                        throw new Exception("已奖励过");
                    }
            }
            String sql1 = "select * from wlyy_health_bank_task_patient_detail where task_id = '"+taskDO1.getId()+"' " +
                    "AND patient_idcard = '"+idCard+"' AND patient_openid = '"+openId+"' AND union_id = '"+unionId+"'";
            List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
            TaskPatientDetailDO taskPatientDetailDO1 = new TaskPatientDetailDO();
            if (taskPatientDetailDOS.isEmpty() && taskPatientDetailDOS.size() == 0){
                TaskPatientDetailDO taskPatientDetailDO = new TaskPatientDetailDO();
                taskPatientDetailDO.setStatus(1);
                taskPatientDetailDO.setTotal(Long.parseLong("0"));
                taskPatientDetailDO.setPatientId(patientId);
                taskPatientDetailDO.setPatientOpenid(openId);
                taskPatientDetailDO.setPatientIdcard(idCard);
                taskPatientDetailDO.setUnionId(unionId);
                taskPatientDetailDO.setSaasId("dev");
                taskPatientDetailDO.setCreateTime(new Date());
                taskPatientDetailDO.setUpdateTime(new Date());
                taskPatientDetailDO.setTaskId(taskDO1.getId());
                taskPatientDetailDO1 = taskPatientDetailDao.save(taskPatientDetailDO);
            }else {
                taskPatientDetailDO1 = taskPatientDetailDOS.get(0);
            }
            String sql = "select * from wlyy_health_bank_account where patient_id = '"+patientId+"'";
            List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
            if (taskRuleDO.getTradeDirection() == -1 && taskRuleDO.getIntegrate() == 0){
                String integrateSql = "select * from wlyy_health_bank_credits_detail where patient_id = '"+patientId+"' " +
                        "AND transaction_id IN (SELECT id FROM wlyy_health_bank_task WHERE task_code IN ('BP_BIND','GLU_BIND','BP_MEASURE','GLU_MEASURE','RULE'))";
                List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(integrateSql,new BeanPropertyRowMapper(CreditsDetailDO.class));
                for (CreditsDetailDO creditsDetailDO:creditsDetailDOS){
                    if (creditsDetailDO.getTradeDirection()==1&&creditsDetailDO.getStatus()!=0){
                        AccountDO accountDO = accountDOS.get(0);
                        int total = accountDO.getTotal() - creditsDetailDO.getIntegrate();
                        creditsDetailDO.setStatus(0);
                        creditsDetailDO.setDescription(description);
                        credittsLogDetailDao.save(creditsDetailDO);
                        accountDO.setTotal(total);
                        accountDao.save(accountDO);
                    }
                }
                CreditsDetailDO creditsDetailDO1 = new CreditsDetailDO();
                creditsDetailDO1.setStatus(1);
                creditsDetailDO1.setSaasId("dev");
                creditsDetailDO1.setAccountId(accountDOS.get(0).getId());
                creditsDetailDO1.setHospital(hospital);
                creditsDetailDO1.setHospitalName(hospitalName);
                creditsDetailDO1.setPatientId(patientId);
                creditsDetailDO1.setIntegrate(taskRuleDO.getIntegrate());
                creditsDetailDO1.setTradeDirection(taskRuleDO.getTradeDirection());
                creditsDetailDO1.setDescription(description);
                creditsDetailDO1.setCreateTime(new Date());
                creditsDetailDO1.setUpdateTime(new Date());
                creditsDetailDO1.setTransactionId(taskDO1.getId());
                creditsDetailDO1.setTradeType("ACTIVITY_TASK");
                credittsLogDetailDao.save(creditsDetailDO1);
            }else{
                CreditsDetailDO creditsDetailDO = new CreditsDetailDO();
                creditsDetailDO.setStatus(1);
                creditsDetailDO.setSaasId("dev");
                creditsDetailDO.setAccountId(accountDOS.get(0).getId());
                creditsDetailDO.setHospital(hospital);
                creditsDetailDO.setHospitalName(hospitalName);
                creditsDetailDO.setPatientId(patientId);
                creditsDetailDO.setIntegrate(taskRuleDO.getIntegrate());
                creditsDetailDO.setTradeDirection(taskRuleDO.getTradeDirection());
                creditsDetailDO.setDescription(description);
                creditsDetailDO.setCreateTime(new Date());
                creditsDetailDO.setUpdateTime(new Date());
                creditsDetailDO.setTransactionId(taskDO1.getId());
                creditsDetailDO.setTradeType("ACTIVITY_TASK");
                credittsLogDetailDao.save(creditsDetailDO);
                if (taskRuleDO.getTradeDirection() == -1){
                    String integrateSql = "select * from wlyy_health_bank_credits_detail where patient_id = '"+patientId+"' " +
                            "AND transaction_id IN (SELECT id FROM wlyy_health_bank_task WHERE task_code IN ('BP_BIND','GLU_BIND','BP_MEASURE','GLU_MEASURE','RULE'))";
                    List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(integrateSql,new BeanPropertyRowMapper(CreditsDetailDO.class));
                    for (CreditsDetailDO creditsDetailDO1:creditsDetailDOS){
                        if (creditsDetailDO1.getTradeDirection()==1&&creditsDetailDO1.getStatus()!=0){
                            AccountDO accountDO = accountDOS.get(0);
                            int total = accountDO.getTotal() - creditsDetailDO1.getIntegrate();
                            creditsDetailDO1.setStatus(0);
                            creditsDetailDO1.setDescription(description);
                            credittsLogDetailDao.save(creditsDetailDO1);
                            accountDO.setTotal(total);
                            accountDao.save(accountDO);
                        }
                    }
                }else if (taskRuleDO.getTradeDirection() == 1){
                    AccountDO accountDO = accountDOS.get(0);
                    accountDO.setTotal(accountDO.getTotal() + taskRuleDO.getIntegrate());
                    accountDao.save(accountDO);
                }
            }
        }
        envelop.setObj(true);
        return envelop;
    }
    /**
     * 固定数据
     *
     * @param
     * @return
     *//*
    public List<TaskDO> getTasks(String patientId){
        List<TaskDO> taskDOList = new ArrayList<>();
        TaskDO taskDO = new TaskDO();
        *//*taskDO.setPatientId(patientId);*//*
        taskDO.setTaskCode("BIND");
        taskDO.setPeriod(1);
        taskDO.setTaskTitle("首次绑定");
        taskDO.setTaskContent("（绑定社区登记发放的设备，可获得各类型设备的首绑奖励。）");
        taskDO.setTradeType("activity");
        taskDO.setTransactionId("402885e96324a409016324c0a45a0006");
        taskDO.setCreateTime(new Date());
        taskDO.setUpdateTime(new Date());
        taskDOList.add(taskDO);
        TaskDO taskDO1 = new TaskDO();
        *//*taskDO1.setPatientId(patientId);*//*
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
*/

    public MixEnvelop<CreditsDetailDO, CreditsDetailDO> stepAddIntegrate(CreditsDetailDO creditsDetailDO){
        try {
            synchronized (creditsDetailDO.getPatientId()){
                String sqlAccount = "select * from wlyy_health_bank_account ba where ba.patient_id = '"+creditsDetailDO.getPatientId() +"'";
                List<AccountDO> accountDOList = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
                if (accountDOList != null && accountDOList.size() != 0){
                    creditsDetailDO.setAccountId(accountDOList.get(0).getId());
                }else {
                    AccountDO accountDO1 = new AccountDO();
                    accountDO1.setPatientId(creditsDetailDO.getPatientId());
                    accountDO1.setSaasId("dev");
                    accountDO1.setTotal(0);
                    accountDO1.setAccountName(creditsDetailDO.getName());
                    if(creditsDetailDO.getIdCard().length()>=4){// 判断是否长度大于等于4
                        String cardNumber=creditsDetailDO.getIdCard().substring(creditsDetailDO.getIdCard().length()- 4,creditsDetailDO.getIdCard().length());//截取两个数字之间的部分
                        int random = (int)((Math.random()*9+1)*100000);
                        accountDO1.setCardNumber(cardNumber+Integer.toString(random));
                    }
                    accountDO1.setHospital(creditsDetailDO.getHospital());
                    accountDO1.setPassword("123456");
                    accountDO1.setHospitalName(creditsDetailDO.getHospitalName());
                    accountDO1.setStatus(1);
                    accountDO1.setCreateTime(new Date());
                    accountDO1.setUpdateTime(new Date());
                    accountDao.save(accountDO1);
                    List<AccountDO> accountDOS = jdbcTemplate.query(sqlAccount,new BeanPropertyRowMapper(AccountDO.class));
                    creditsDetailDO.setAccountId(accountDOS.get(0).getId());
                }
                TaskDO taskDO = taskDao.findOne(creditsDetailDO.getTransactionId());
                String sql1 = "select *  from wlyy_health_bank_task_patient_detail where "+
                        " patient_idcard = '"+creditsDetailDO.getIdCard()+"' AND union_id = '"+creditsDetailDO.getUnionId()+"' AND task_id = '"+creditsDetailDO.getTransactionId()+"'";
                List<TaskPatientDetailDO> taskPatientDetailDOList = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                if(taskPatientDetailDOList == null || taskPatientDetailDOList.size()==0){
                    throw new Exception("该居民参与活动查不到!");
                }
                TaskPatientDetailDO taskPatientDetailDO = taskPatientDetailDOList.get(0);
                String sql = "select * from wlyy_health_bank_credits_detail where patient_id = '"+creditsDetailDO.getPatientId()+"' AND " +
                        "transaction_id = '"+creditsDetailDO.getTransactionId()+"' AND create_time > '"+DateUtils.getDayBegin() +"' AND" +
                        " create_time < '"+DateUtils.getDayEnd()+"'AND ISNULL(description)";
                List<CreditsDetailDO> creditsDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(CreditsDetailDO.class));
                String step = redisTemplate.opsForValue().get(STEP);
                if (StringUtils.isEmpty(step)){
                    throw new Exception("获取步数失败！");
                }
                JSONObject object = JSONObject.parseObject(step);
                int step1 = object.getInteger("step1");
                int step2 = object.getInteger("step2");
                if (creditsDetailDOS != null && creditsDetailDOS.size() != 0){
                    CreditsDetailDO creditsDetailDO1 = creditsDetailDOS.get(0);
                    TaskRuleDO taskRuleDO = taskRuleDao.findOne(taskDO.getRuleCode());
                    if (creditsDetailDO.getStepNumber() < step1 && creditsDetailDO.getStepNumber() >0){
                        creditsDetailDO1.setIntegrate(0);
                        creditsDetailDO1.setTradeDirection(1);
                        CreditsDetailDO creditsDetailDO2 = credittsLogDetailDao.save(creditsDetailDO1);
                        AccountDO accountDO = accountDao.findOne(creditsDetailDO2.getAccountId());
                        accountDO.setTotal(accountDO.getTotal()+creditsDetailDO2.getIntegrate());
                        AccountDO accountDO1 = accountDao.save(accountDO);
                        creditsDetailDO2.setTotal(accountDO1.getTotal());
                        taskPatientDetailDO.setTotal(taskPatientDetailDO.getTotal()+creditsDetailDO2.getIntegrate());
                        taskPatientDetailDao.save(taskPatientDetailDO);
                        creditsDetailDOS.clear();
                        creditsDetailDOS.add(creditsDetailDO2);
                    }else if (creditsDetailDO.getStepNumber() >= step1 && creditsDetailDO.getStepNumber() < step2){
                        creditsDetailDO1.setIntegrate(creditsDetailDO1.getIntegrate()+1);
                        creditsDetailDO1.setTradeDirection(1);
                        CreditsDetailDO creditsDetailDO2 = credittsLogDetailDao.save(creditsDetailDO1);
                        AccountDO accountDO = accountDao.findOne(creditsDetailDO2.getAccountId());
                        accountDO.setTotal(accountDO.getTotal()+(creditsDetailDO2.getIntegrate()));
                        AccountDO accountDO1 = accountDao.save(accountDO);
                        creditsDetailDO2.setTotal(accountDO1.getTotal());
                        taskPatientDetailDO.setTotal(taskPatientDetailDO.getTotal()+(creditsDetailDO2.getIntegrate()));
                        taskPatientDetailDao.save(taskPatientDetailDO);
                        creditsDetailDOS.clear();
                        creditsDetailDOS.add(creditsDetailDO2);
                    }else if (creditsDetailDO.getStepNumber() >= step2){
                        if (creditsDetailDO1.getIntegrate() == 1){
                            creditsDetailDO1.setIntegrate(2);
                            creditsDetailDO1.setTradeDirection(1);
                            CreditsDetailDO creditsDetailDO2 = credittsLogDetailDao.save(creditsDetailDO1);
                            AccountDO accountDO = accountDao.findOne(creditsDetailDO2.getAccountId());
                            accountDO.setTotal(accountDO.getTotal()+(creditsDetailDO2.getIntegrate()-1));
                            AccountDO accountDO1 = accountDao.save(accountDO);
                            creditsDetailDO2.setTotal(accountDO1.getTotal());
                            taskPatientDetailDO.setTotal(taskPatientDetailDO.getTotal()+(creditsDetailDO2.getIntegrate()-1));
                            taskPatientDetailDao.save(taskPatientDetailDO);
                            creditsDetailDOS.clear();
                            creditsDetailDOS.add(creditsDetailDO2);
                        }/*else if(creditsDetailDO1.getIntegrate() == 3){
                            creditsDetailDO1.setIntegrate(creditsDetailDO1.getIntegrate()+5);
                            creditsDetailDO1.setTradeDirection(1);
                            CreditsDetailDO creditsDetailDO2 = credittsLogDetailDao.save(creditsDetailDO1);
                            AccountDO accountDO = accountDao.findOne(creditsDetailDO2.getAccountId());
                            accountDO.setTotal(accountDO.getTotal()+(creditsDetailDO2.getIntegrate()-3));
                            AccountDO accountDO1 = accountDao.save(accountDO);
                            creditsDetailDO2.setTotal(accountDO1.getTotal());
                            taskPatientDetailDO.setTotal(taskPatientDetailDO.getTotal()+(creditsDetailDO2.getIntegrate()-3));
                            taskPatientDetailDao.save(taskPatientDetailDO);
                            creditsDetailDOS.clear();
                            creditsDetailDOS.add(creditsDetailDO2);
                        }*/
                    }
                }else{
                    CreditsDetailDO creditsDetailDO1 = new CreditsDetailDO();
                    if (creditsDetailDO.getStepNumber() < step1 && creditsDetailDO.getStepNumber() >0){
                        creditsDetailDO1.setIntegrate(0);
                        creditsDetailDO1.setTradeDirection(1);
                    }else if (creditsDetailDO.getStepNumber() >= step1 && creditsDetailDO.getStepNumber() < step2){
                        creditsDetailDO1.setIntegrate(1);
                        creditsDetailDO1.setTradeDirection(1);
                    }else if (creditsDetailDO.getStepNumber() >=step2){
                        creditsDetailDO1.setIntegrate(2);
                        creditsDetailDO1.setTradeDirection(1);
                    }
                    creditsDetailDO1.setSaasId("dev");
                    creditsDetailDO1.setTradeType("ACTIVITY_TASK");
                    creditsDetailDO1.setPatientId(creditsDetailDO.getPatientId());
                    creditsDetailDO1.setHospital(creditsDetailDO.getHospital());
                    creditsDetailDO1.setHospitalName(creditsDetailDO.getHospitalName());
                    creditsDetailDO1.setAccountId(creditsDetailDO.getAccountId());
                    creditsDetailDO1.setStatus(1);
                    creditsDetailDO1.setCreateTime(new Date());
                    creditsDetailDO1.setUpdateTime(new Date());
                    creditsDetailDO1.setTransactionId(taskPatientDetailDO.getTaskId());
                    CreditsDetailDO creditsDetailDO2 = credittsLogDetailDao.save(creditsDetailDO1);
                    AccountDO accountDO = accountDao.findOne(creditsDetailDO2.getAccountId());
                    accountDO.setTotal(accountDO.getTotal()+creditsDetailDO2.getIntegrate());
                    taskPatientDetailDO.setTotal(taskPatientDetailDO.getTotal()+creditsDetailDO2.getIntegrate());
                    taskPatientDetailDao.save(taskPatientDetailDO);
                    creditsDetailDO2.setTotal(accountDO.getTotal());
                    creditsDetailDOS.add(creditsDetailDO2);
                }
                try{
                    activeRecordService.insert("dev",creditsDetailDOS.get(0).getTransactionId(),null,null,null,creditsDetailDOS.get(0).getPatientId());
                }catch (Exception e){
                    logger.error("插入活跃出错:"+e.getMessage());
                }
                MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
                envelop.setDetailModelList(creditsDetailDOS);
                return envelop;
            }
        }catch (Exception e){
            e.printStackTrace();
            MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
            return envelop;
        }
    }

    /*public JSONObject getStepNumber(){
        String step = redisTemplate.·().get(STEP);
        logger.info("redis数据:"+step);
        String sql = ""
        String step1 = systemDictDao.
        logger.info("数据库的数据:"+step1);
        if (step != null && step.equalsIgnoreCase(step1)){
            JSONObject jsonObject = JSONObject.parseObject(step);
            logger.info("数据不一致:"+jsonObject.toString());
            return jsonObject;
        }else {
            JSONObject jsonObject = JSONObject.parseObject(step);
            redisTemplate.opsForValue().set(STEP,step1);
            logger.info("数据一致:"+jsonObject.toString());
            return jsonObject;
        }
    }*/

    public MixEnvelop<CreditsDetailDO, CreditsDetailDO> weekReward(CreditsDetailDO creditsDetailDO) {
        try {
            synchronized (creditsDetailDO.getPatientId()) {
                String sqlAccount = "select * from wlyy_health_bank_account ba where ba.patient_id = '" + creditsDetailDO.getPatientId() + "'";
                List<AccountDO> accountDOList = jdbcTemplate.query(sqlAccount, new BeanPropertyRowMapper(AccountDO.class));
                if (accountDOList != null && accountDOList.size() != 0) {
                    creditsDetailDO.setAccountId(accountDOList.get(0).getId());
                }
                String creditsSql = "select * from wlyy_health_bank_credits_detail where patient_id = '" + creditsDetailDO.getPatientId() + "' and " +
                        "DATE_FORMAT(create_time,'%Y-%m-%d') IN " + creditsDetailDO.getWeekTimes()+" and description = '周奖励'";
                List<CreditsDetailDO> creditsDetailDOS1 = jdbcTemplate.query(creditsSql, new BeanPropertyRowMapper(CreditsDetailDO.class));
                List<CreditsDetailDO> creditsDetailDOList = new ArrayList<>();
                MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
                if (getWeekOfDate(new Date()).equalsIgnoreCase("星期五")||getWeekOfDate(new Date()).equalsIgnoreCase("星期六")||getWeekOfDate(new Date()).equalsIgnoreCase("星期日")){
                    String sql = "select sum(integrate) as total from wlyy_health_bank_credits_detail where patient_id = '" + creditsDetailDO.getPatientId() + "' and description = '周奖励'";
                    List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
                    Long count = 0L;
                    if(rstotal!=null&&rstotal.size()>0){
                        Object object =  rstotal.get(0).get("total");
                        if (object != null){
                            count = Long.parseLong(object.toString());
                        }
                    }
                    if (count > 12){
                        envelop.setMessage("奖励积分已达到12分。不能再奖励了！");
                        return envelop;
                    }else{
                        if (creditsDetailDOS1 == null || creditsDetailDOS1.size() == 0) {
                            creditsDetailDO.setTradeType("ACTIVITY_TASK");
                            creditsDetailDO.setSaasId("dev");
                            creditsDetailDO.setStatus(1);
                            creditsDetailDO.setTransactionId(creditsDetailDO.getTransactionId());
                            creditsDetailDO.setCreateTime(new Date());
                            creditsDetailDO.setUpdateTime(new Date());
                            creditsDetailDO.setTradeDirection(1);
                            CreditsDetailDO creditsDetailDO1 = credittsLogDetailDao.save(creditsDetailDO);
                            creditsDetailDOList.add(creditsDetailDO1);
                            TaskPatientDetailDO taskPatientDetailDO = new TaskPatientDetailDO();
                            taskPatientDetailDO.setPatientId(creditsDetailDO1.getPatientId());
                            taskPatientDetailDO.setTaskId(creditsDetailDO1.getTransactionId());
                            String taskSql1 = ISqlUtils.getAllSql(taskPatientDetailDO);
                            List<TaskPatientDetailDO> taskPatientDetailDOS1 = jdbcTemplate.query(taskSql1, new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                            TaskPatientDetailDO taskPatientDetailDO1 = taskPatientDetailDOS1.get(0);
                            if (creditsDetailDO1.getTradeDirection() == 1) {
                                taskPatientDetailDO1.setTotal(taskPatientDetailDO1.getTotal() + creditsDetailDO1.getIntegrate());
                            } else if (creditsDetailDO.getTradeDirection() == -1) {
                                taskPatientDetailDO1.setTotal(taskPatientDetailDO1.getTotal() - creditsDetailDO1.getIntegrate());
                            }
                            taskPatientDetailDao.save(taskPatientDetailDO1);
                            AccountDO accountDO = accountDao.findOne(creditsDetailDO1.getAccountId());
                            if (creditsDetailDO1.getTradeDirection() == 1) {
                                accountDO.setTotal(accountDO.getTotal() + creditsDetailDO1.getIntegrate());
                            } else if (creditsDetailDO.getTradeDirection() == -1) {
                                accountDO.setTotal(accountDO.getTotal() - creditsDetailDO1.getIntegrate());
                            }
                            AccountDO accountDO1 = accountDao.save(accountDO);
                            List<CreditsDetailDO> creditsDetailDOS = new ArrayList<>();
                            for (CreditsDetailDO creditsDetailDO2 : creditsDetailDOList) {
                                creditsDetailDO2.setTotal(accountDO1.getTotal());
                                creditsDetailDO2.setFlag("1");
                                creditsDetailDOS.add(creditsDetailDO2);
                            }
                            envelop.setDetailModelList(creditsDetailDOS);
                        }else {
                            for (CreditsDetailDO creditsDetailDO1 : creditsDetailDOS1){
                                creditsDetailDO1.setFlag("2");
                            }
                            envelop.setDetailModelList(creditsDetailDOS1);
                        }
                        return envelop;
                    }
                }else{
                    CreditsDetailDO creditsDetailDO1 = new CreditsDetailDO();
                    creditsDetailDO1.setFlag("0");
                    creditsDetailDOList.add(creditsDetailDO1);
                    envelop.setDetailModelList(creditsDetailDOList);
                    return envelop;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MixEnvelop<CreditsDetailDO, CreditsDetailDO> envelop = new MixEnvelop<>();
            return envelop;
        }
    }


    public  String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


}
