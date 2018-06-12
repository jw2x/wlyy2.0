package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.ActivityDao;
import com.yihu.jw.entity.health.bank.ActivityDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-04-27 14:38
 * @desc health activity Service
 **/
@Service
@Transactional
public class ActivityService extends BaseJpaService<ActivityDO,ActivityDao> {

    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert activityDO
     *
     * @param activityDO 活动参数对象
     * @return
     */
    public Envelop<Boolean> insert(ActivityDO activityDO){
        activityDO.setCreateTime(new Date());
        activityDO.setUpdateTime(new Date());
        activityDao.save(activityDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }


    /**
     *  find by condition
     *
     * @param activityDO 活动参数对象
     * @param page 页码
     * @param size 每页大小
     * @return
     * @throws ParseException
     */
    public Envelop<ActivityDO> findByCondition(ActivityDO activityDO,Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(activityDO,page,size,"*");
        List<ActivityDO> activityDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(ActivityDO.class));

        String sqlcount = new ISqlUtils().getSql(activityDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,activityDOS,page,size,count);
    }

    /**
     * update activityDO
     *
     * @param activityDO 活动参数对象
     * @return
     */
    public Envelop<Boolean> update(ActivityDO activityDO){
        activityDao.save(activityDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }

    /**
     * 获取参与的活动
     *
     * @param activityDO 活动对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    public Envelop<ActivityDO> selectByPatient(ActivityDO activityDO,Integer page,Integer size){
        String sql ="SELECT * " +
                " FROM wlyy_health_bank_activity " +
                "WHERE " +
                "id IN ( " +
                "SELECT bt.transaction_id " +
                "FROM wlyy_health_bank_task bt " +
                "WHERE id IN (" +
                " SELECT task_id FROM " +
                "wlyy_health_bank_task_patient_detail" +
                " WHERE " +
                " patient_openid = '"+activityDO.getOpenId()+ "' )" +
                " )" +
                " LIMIT "+(page-1)*size +","+size;
        List<ActivityDO> activityDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(ActivityDO.class));
        String sqlcount = "SELECT count(1) AS total" +
                " FROM wlyy_health_bank_activity " +
                "WHERE " +
                "id IN ( " +
                "SELECT bt.transaction_id " +
                "FROM wlyy_health_bank_task bt " +
                "WHERE id IN (" +
                " SELECT task_id FROM " +
                "wlyy_health_bank_task_patient_detail" +
                " WHERE " +
                " patient_openid = '"+activityDO.getOpenId()+ "' )" +
                " )";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success,activityDOS,page,size,count);
    }
}
