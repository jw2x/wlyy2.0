package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.dao.TaskRuleDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.entity.health.bank.TaskRuleDO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-05-10 11:26
 * @desc account service
 **/
@Service
@Transactional
public class AccountService extends BaseJpaService<AccountDO,AccountDao> {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskRuleDao taskRuleDao;

    /**
     * 添加银行账户信息
     *
     * @param accountDO 银行账户对象
     * @return
     */
    public MixEnvelop<Boolean, Boolean> insert(AccountDO accountDO){
        accountDao.save(accountDO);
        MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
        envelop.setObj(true);
        return envelop;
    }


    /**
     * 获取银行账户信息
     *
     * @param accountDO 银行账户对象
     * @param page 页码
     * @param size 每页大小
     * @return
     * @throws ParseException
     */
    public MixEnvelop<AccountDO, AccountDO> findByCondition(AccountDO accountDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(accountDO,page,size,"*");
        List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
        for (AccountDO accountDO1:accountDOS){
            String sql1 = "select COALESCE(sum(bcd.integrate),0) as total from wlyy_health_bank_credits_detail bcd where bcd.trade_direction = 1" +
                    " AND bcd.create_time > '"+ DateUtils.getDayBegin()+"' AND bcd.create_time < '"+DateUtils.getDayEnd()+"' AND bcd.patient_id = '" + accountDO1.getPatientId() +"'"
                    +"AND transaction_id = '"+accountDO.getTaskId()+"'";
            List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql1);
            Long count = 0L;
            if(rstotal!=null&&rstotal.size()>0){
                Object object = rstotal.get(0).get("total");
                if (object != null){
                    count = Long.parseLong(object.toString());
                }
            }
            accountDO1.setNowTotal(count);
            String activitySql1 =" SELECT " +
                    " SUM(ptpd.total) AS total FROM wlyy_health_bank_task_patient_detail ptpd " +
                    " WHERE " +" task_id = '" + accountDO.getTaskId() + "' " +
                    "and patient_id = '"+accountDO1.getPatientId()+"'";
            List<Map<String,Object>> rstotal6 = jdbcTemplate.queryForList(activitySql1);
            Long activityIntegrate = 0L;
            if(rstotal6!=null&&rstotal6.size()>0){
                Object object = rstotal6.get(0).get("total");
                if (object!=null){
                    activityIntegrate = Long.parseLong(object.toString());
                }
            }
            accountDO1.setActivityIntegrate(activityIntegrate);
            String activitySql = "SELECT  COUNT(*) AS total FROM ( SELECT * FROM " +
                    "wlyy_health_bank_task_patient_detail " +
                    " WHERE patient_id = '" + accountDO1.getPatientId()+
                    "'AND activity_id != '' GROUP BY activity_id ) btpd1";
            List<Map<String,Object>> rstotal1 = jdbcTemplate.queryForList(activitySql);
            Long activityCount = 0L;
            if(rstotal1!=null&&rstotal1.size()>0){
                Object object = rstotal1.get(0).get("total");
                if (object != null){
                    activityCount = Long.parseLong(object.toString());
                }
            }
            accountDO1.setActivityTotal(activityCount);
            String taskSql = "SELECT  COUNT(*) AS total FROM ( SELECT * FROM " +
                    "wlyy_health_bank_task_patient_detail " +
                    " WHERE patient_id = '" + accountDO1.getPatientId()+
                    "' GROUP BY task_id ) btpd1";
            List<Map<String,Object>> rstotal2 = jdbcTemplate.queryForList(taskSql);
            Long taskCount = 0L;
            if(rstotal2!=null&&rstotal2.size()>0){
                Object object = rstotal2.get(0).get("total");
                if (object != null ){
                    taskCount = Long.parseLong(object.toString());
                }
            }
            accountDO1.setTaskTotal(taskCount);
            if (accountDO.getPatientIds() != null && accountDO.getPatientIds().size() != 0){
                StringBuffer buffer = new StringBuffer();
                buffer.append(" (");
                for (int i=0;i<accountDO.getPatientIds().size();i++){
                    buffer.append("'"+accountDO.getPatientIds().get(i)+"'").append(",");
                }
                buffer.deleteCharAt(buffer.length()-1);
                buffer.append(") ");
                String accountSql = "select * from wlyy_health_bank_account where patient_id in "+buffer;
                List<AccountDO> accountDOSList = jdbcTemplate.query(accountSql,new BeanPropertyRowMapper<>(AccountDO.class));
                if (accountDOSList != null && accountDOSList.size() !=0){
                    String sql2 = " SELECT " +
                            " ba.total + COALESCE (bacd1.sum, 0) AS total " +
                            " FROM " +
                            " wlyy_health_bank_account ba " +
                            " LEFT JOIN ( " +
                            " SELECT " +
                            " COALESCE (SUM(bacd.integrate), 0) AS sum, " +
                            " bacd.account_id " +
                            "  FROM " +
                            " wlyy_health_bank_credits_detail bacd " +
                            " WHERE " +
                            " bacd.trade_direction = - 1 " +
                            " GROUP BY " +
                            " bacd.account_id ) bacd1 ON ba.id = bacd1.account_id " +
                            " WHERE " +
                            " ba.id = '"+accountDO1.getId()+"'";
                    List<Map<String,Object>> rstotal3 = jdbcTemplate.queryForList(sql2);
                    Long Count = 0L;
                    if(rstotal3!=null&&rstotal3.size()>0){
                        Object object = rstotal3.get(0).get("total");
                        if (object!=null){
                            Count = Long.parseLong(object.toString());
                        }
                    }
                    accountDO1.setSum(Count);
                    StringBuffer buffer1 = new StringBuffer();
                    buffer1.append(" (");
                    for (int i=0;i<accountDOSList.size();i++){
                        buffer1.append("'"+accountDOSList.get(i).getId()+"'").append(",");
                    }
                    buffer1.deleteCharAt(buffer1.length()-1);
                    buffer1.append(") ");
                    String sql3 = "SELECT " +
                            " COUNT(1) + 1 AS total " +
                            "FROM " +
                            " ( " +
                            " SELECT " +
                            " ba.id AS id, " +
                            " ba.total /*+ COALESCE (bacd1.sum, 0)*/ AS sum " +
                            " FROM " +
                            " wlyy_health_bank_account ba " +
                            /*" LEFT JOIN ( " +
                            " SELECT " +
                            " COALESCE (SUM(bacd.integrate), 0) AS sum, " +
                            " bacd.account_id " +
                            "  FROM " +
                            " wlyy_health_bank_credits_detail bacd " +
                            " WHERE " +
                            " bacd.trade_direction = - 1 " +
                            " GROUP BY " +
                            " bacd.account_id ) bacd1 ON ba.id = bacd1.account_id "+*/
                            " WHERE " +
                            " ba.id IN " +buffer1+
                            " )ba1 WHERE ba1.sum > "+accountDO1.getSum();
                    List<Map<String,Object>> rstotal4 = jdbcTemplate.queryForList(sql3);
                    Integer Count1 = 0;
                    if(rstotal4!=null&&rstotal4.size()>0){
                        Object object = rstotal4.get(0).get("total");
                        if (object != null){
                            Count1 = Integer.parseInt(object.toString());
                        }

                    }
                    accountDO1.setTeamRanking(Count1);

                    String sql4 = "SELECT " +
                            " COUNT(1) + 1 AS total " +
                            "FROM " +
                            " ( " +
                            " SELECT " +
                            " ba.id AS id, " +
                            " ba.total /*+ COALESCE (bacd1.sum, 0)*/ AS sum " +
                            " FROM " +
                            " wlyy_health_bank_account ba " +
                            /*" LEFT JOIN ( " +
                            " SELECT " +
                            " COALESCE (SUM(bacd.integrate), 0) AS sum, " +
                            " bacd.account_id " +
                            "  FROM " +
                            " wlyy_health_bank_credits_detail bacd " +
                            " WHERE " +
                            " bacd.trade_direction = - 1 " +
                            " GROUP BY " +
                            " bacd.account_id ) bacd1 ON ba.id = bacd1.account_id " +*/
                            " )ba1 WHERE ba1.sum > "+accountDO1.getSum();
                    List<Map<String,Object>> rstotal5 = jdbcTemplate.queryForList(sql4);
                    Integer Count2 = 0;
                    if(rstotal5!=null&&rstotal5.size()>0){
                        Object object = rstotal5.get(0).get("total");
                        if (object != null){
                            Count2 = Integer.parseInt(object.toString());
                        }
                    }
                    accountDO1.setCityRanking(Count2);
                }

            }
        }
        String sqlcount = new ISqlUtils().getSql(accountDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success,accountDOS,page,size,count);
    }

    /**
     * 根据条件活动用户信息
     *
     * @param page 页码
     *
     * @param size 分页大小
     * @return
     */
    public MixEnvelop<AccountDO, AccountDO> findByCondition1(JSONArray patients, String ruleId, Integer page, Integer size){
        for (int i=0;i<patients.size();i++){
            String patientId = patients.getJSONObject(i).getString("code");
            String sql = "select * from wlyy_health_bank_account where patient_id = '"+patientId+"'";
            List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
            if (accountDOS == null || accountDOS.size() == 0) {
                AccountDO accountDO1 = new AccountDO();
                accountDO1.setPatientId(patientId);
                accountDO1.setTotal(0);
                accountDO1.setAccountName(patients.getJSONObject(i).getString("name"));
                accountDO1.setCardNumber(patients.getJSONObject(i).getString("idcard"));
                accountDO1.setHospital("350205");
                accountDO1.setPassword("321321312321");
                accountDO1.setHospitalName("海沧区");
                accountDO1.setCreateTime(new Date());
                accountDO1.setUpdateTime(new Date());
                accountDao.save(accountDO1);
            }
        }
        TaskRuleDO taskRuleDO = taskRuleDao.findOne(ruleId);
        String taskSql = "select * from wlyy_health_bank_task where transaction_id = '"+taskRuleDO.getId()+"'";
        List<TaskDO> taskDOList = jdbcTemplate.query(taskSql,new BeanPropertyRowMapper(TaskDO.class));
        List<String> ids = new ArrayList<>();
        if(taskDOList != null && taskDOList.size() != 0){
            if (taskRuleDO.getPeriod() == 1){
                for (int i = 0;i<patients.size();i++){
                    String patientId = patients.getJSONObject(i).getString("code");
                    String sql = "select * from wlyy_health_bank_task_patient_detail where patient_id = '"+patientId+"' AND task_id = '"+taskDOList.get(0).getId()+"'";
                    List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                    if (taskPatientDetailDOS ==null || taskPatientDetailDOS.size() == 0){
                        ids.add(patientId);
                    }
                }
            }else if (taskRuleDO.getPeriod() == 0){
                for (int i = 0;i<patients.size();i++){
                    String patientId = patients.getJSONObject(i).getString("code");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();
                    String date1 = dateFormat.format(date);
                    String begin = DateUtils.getMinMonthDate(date1);
                    String end = DateUtils.getMaxMonthDate(date1);
                    String sql = "select * from wlyy_health_bank_task_patient_detail where patient_id = '"+patientId+"' AND task_id = '"+taskDOList.get(0).getId()+"' AND create_time > '"+begin+"' AND create_time < '"+end+"'";
                    List<TaskPatientDetailDO> taskPatientDetailDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(TaskPatientDetailDO.class));
                    if (taskPatientDetailDOS ==null || taskPatientDetailDOS.size() == 0){
                        ids.add(patientId);
                    }
                }
            }
        }else {
            for (int i = 0;i<patients.size();i++){
                String patientId = patients.getJSONObject(i).getString("code");
                ids.add(patientId);
            }
        }


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

       /* StringBuffer buffer1 = new StringBuffer();//设备类型
        buffer1.append("(");
        if (deviceTypes == null || deviceTypes.size() == 0){
            buffer1.append("''");
        }else {
            for (int i=0;i<deviceTypes.size();i++){
                buffer1.append("'"+deviceTypes.get(i)+"'").append(",");
            }
            buffer1.deleteCharAt(buffer1.length()-1);
        }
        buffer1.append(")");
        String sql1 = null ;
        if (bindStatus == -1){
            sql1 = "( SELECT btpd.patient_id AS patient_id FROM " +
                    "  wlyy_health_bank_task_patient_detail btpd " +
                    " LEFT JOIN wlyy_health_bank_task bt ON bt.id = btpd.task_id WHERE " +
                    " bt.task_code NOT IN "+buffer1+" and "+buffer+")";
        }else if (bindStatus == 1){
            sql1 = "( SELECT btpd.patient_id AS patient_id FROM " +
                    "  wlyy_health_bank_task_patient_detail btpd " +
                    " LEFT JOIN wlyy_health_bank_task bt ON bt.id = btpd.task_id WHERE " +
                    " bt.task_code IN "+buffer1 +" and "+buffer+")";
        }*/
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
                        "(ba.total +COALESCE((cd1.total),0)) AS sum" +
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
                        " WHERE ba.patient_id IN " + buffer +
                        " ORDER BY" +
                        " ba.create_time DESC " +
                        "LIMIT "+(page-1)*size+","+size +")ba1" +
                        " ORDER BY " +
                        " ba1.sum DESC";
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
                "WHERE ba.patient_id IN " + buffer +
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
}
