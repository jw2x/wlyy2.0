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
     * @param activityDO
     * @return
     */
    public Envelop<Boolean> insert(ActivityDO activityDO){
        activityDao.save(activityDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }


    /**
     *  find by condition
     *
     * @param activityDO
     * @param page
     * @param size
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
     * @param activityDO
     * @return
     */
    public Envelop<Boolean> update(ActivityDO activityDO){
        activityDao.save(activityDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }
}
