package com.yihu.jw.service;/**
 * Created by nature of king on 2018/5/10.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.AccountDao;
import com.yihu.jw.entity.health.bank.AccountDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
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

        String sqlcount = new ISqlUtils().getSql(accountDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,accountDOS,page,size,count);
    }


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
