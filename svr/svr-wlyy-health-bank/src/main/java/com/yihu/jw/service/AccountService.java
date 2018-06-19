package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.entity.health.bank.CreditsDetailDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.DateUtils;
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

    /**
     * 添加银行账户信息
     *
     * @param accountDO 银行账户对象
     * @return
     */
    public Envelop<Boolean> insert(AccountDO accountDO){
        accountDao.save(accountDO);
        Envelop<Boolean> envelop = new Envelop<>();
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
    public Envelop<AccountDO> findByCondition(AccountDO accountDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(accountDO,page,size,"*");
        List<AccountDO> accountDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(AccountDO.class));
        for (AccountDO accountDO1:accountDOS){
            String sql1 = "select COALESCE(sum(bcd.integrate),0) as total from wlyy_health_bank_credits_detail bcd where bcd.trade_direction = 1" +
                    " AND bcd.create_time > '"+ DateUtils.getDayBegin()+"' AND bcd.create_time < '"+DateUtils.getDayEnd()+"' AND bcd.patient_id = " +
                    " '"+ accountDO1.getPatientId() +"'";
            List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql1);
            Long count = 0L;
            if(rstotal!=null&&rstotal.size()>0){
                Object object = rstotal.get(0).get("total");
                count = Long.parseLong(object.toString());
            }
            accountDO1.setNowTotal(count);
            String activitySql = "SELECT  COUNT(*) AS total FROM ( SELECT * FROM " +
                    "wlyy_health_bank_task_patient_detail " +
                    " WHERE patient_id = '" + accountDO1.getPatientId()+
                    "' GROUP BY activity_id ) btpd1";
            List<Map<String,Object>> rstotal1 = jdbcTemplate.queryForList(activitySql);
            Long activityCount = 0L;
            if(rstotal1!=null&&rstotal1.size()>0){
                Object object = rstotal1.get(0).get("total");
                activityCount = Long.parseLong(object.toString());
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
                taskCount = Long.parseLong(object.toString());
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
                List<AccountDO> accountDOS1 = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(AccountDO.class));
                if (accountDOS1 != null && accountDOS1.size() !=0){
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
                        Count = Long.parseLong(object.toString());
                    }
                    accountDO1.setSum(Count);
                    StringBuffer buffer1 = new StringBuffer();
                    buffer1.append(" (");
                    for (int i=0;i<accountDOS1.size();i++){
                        buffer1.append("'"+accountDOS1.get(i).getId()+"'").append(",");
                    }
                    buffer1.deleteCharAt(buffer1.length()-1);
                    buffer1.append(") ");
                    String sql3 = "SELECT " +
                            " COUNT(1) + 1 AS total " +
                            "FROM " +
                            " ( " +
                            " SELECT " +
                            " ba.id AS id, " +
                            " ba.total + COALESCE (bacd1.sum, 0) AS sum " +
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
                            " ba.id IN " +buffer1+
                            " )ba1 WHERE ba1.sum > "+accountDO1.getSum();
                    List<Map<String,Object>> rstotal4 = jdbcTemplate.queryForList(sql3);
                    Integer Count1 = 0;
                    if(rstotal4!=null&&rstotal4.size()>0){
                        Object object = rstotal4.get(0).get("total");
                        Count1 = Integer.parseInt(object.toString());
                    }
                    accountDO1.setTeamRanking(Count1);

                    String sql4 = "SELECT " +
                            " COUNT(1) + 1 AS total " +
                            "FROM " +
                            " ( " +
                            " SELECT " +
                            " ba.id AS id, " +
                            " ba.total + COALESCE (bacd1.sum, 0) AS sum " +
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
                            " )ba1 WHERE ba1.sum > "+accountDO1.getSum();
                    List<Map<String,Object>> rstotal5 = jdbcTemplate.queryForList(sql4);
                    Integer Count2 = 0;
                    if(rstotal5!=null&&rstotal5.size()>0){
                        Object object = rstotal5.get(0).get("total");
                        Count2 = Integer.parseInt(object.toString());
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
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,accountDOS,page,size,count);
    }

    /**
     * 根据条件活动用户信息
     *
     * @param patientIds 居民id
     * @param bindStatus 绑定状态
     *
     * @param deviceTypes 设备类型
     *
     * @param page 页码
     *
     * @param size 分页大小
     * @return
     */
    public Envelop<AccountDO> findByCondition1(List<String> patientIds ,int bindStatus,List<String> deviceTypes,Integer page,Integer size){
        StringBuffer buffer = new StringBuffer();
        buffer.append(" btpd.patient_id in(");
        if (patientIds == null || patientIds.size() == 0){
            buffer.append("''");
        }else {
            for (int i=0;i<patientIds.size();i++){
                buffer.append("'"+patientIds.get(i)+"'").append(",");
            }
            buffer.deleteCharAt(buffer.length()-1);
        }
        buffer.append(") ");
        StringBuffer buffer1 = new StringBuffer();
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
        }
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
                        " WHERE ba.patient_id IN " + sql1 +
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
                "WHERE ba.patient_id IN " + sql1 +
                " ORDER BY " +
                " ba.create_time, " +
                " (ba.total + COALESCE(cd1.total,0)) DESC ";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlCount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, accountDOS,page,size,count);
    }
}
