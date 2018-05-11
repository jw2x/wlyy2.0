package com.yihu.jw.service;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.jw.dao.ActivityDao;
import com.yihu.jw.dao.ActivityRuleDao;
import com.yihu.jw.dao.CredittsLogDetailDao;
import com.yihu.jw.entity.health.bank.ActivityRuleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;

/**
 * @author wangzhinan
 * @create 2018-04-27 14:40
 * @desc health activity info Service
 **/
@Service
@Transactional
public class ActivityRuleService extends BaseJpaService<ActivityRuleDO,ActivityRuleDao> {
    @Autowired
    private ActivityRuleDao activityRuleDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private CredittsLogDetailDao credittsLogDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * insert activityRuleDO
     *
     * @param activityRuleDO
     * @return
    public Envelop<Boolean> insert(ActivityRuleDO activityRuleDO){
        activityRuleDao.save(activityRuleDO);
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }


    *//**
     *  find by condition
     *
     * @param activityRuleDO
     * @param page
     * @param size
     * @return
     * @throws ParseException
     *//*
    public Envelop<ActivityRuleDO> findByCondition(ActivityRuleDO activityRuleDO, Integer page, Integer size) throws ParseException {
        String sql = new ISqlUtils().getSql(activityRuleDO,page,size,"*");
        List<ActivityRuleDO> activityRuleDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(ActivityRuleDO.class));
        List<ActivityRuleDO> activityRuleDOList = new ArrayList<>();
        for (ActivityRuleDO activityRuleDO1 : activityRuleDOS){
            ActivityDO activityDO = activityDao.findOne(activityRuleDO1.getActivityId());
            activityRuleDO1.setActivityDO(activityDO);
            activityRuleDOList.add(activityRuleDO1);
        }
        String sqlcount = new ISqlUtils().getSql(activityRuleDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return Envelop.getSuccessListWithPage(HealthBankMapping.api_success, activityRuleDOList,page,size,count);
    }

    *//**
     * update activityRuleDO
     *
     * @param activityRuleDO
     * @return
     *//*
    public Envelop<Boolean> update(ActivityRuleDO activityRuleDO){
        ActivityDO activityDO = activityDao.findOne(activityRuleDO.getActivityId());
        ActivityRuleDO activityRuleDO1 = new ActivityRuleDO();
        activityRuleDO1 = activityRuleDao.findOne(activityRuleDO.getId());
        if (activityRuleDO.getStatus().equalsIgnoreCase("finished")){
            activityRuleDO.setIntegrate(activityDO.getIntegrate());
        }
        String sql = "select cl.status as status,cl.total as total from health_bank_credits_log cl order by cl.update_time desc";
        List<CreditsDetailDO> creditsLogDetailDOList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CreditsDetailDO.class));
        activityRuleDao.save(activityRuleDO);
        if (activityRuleDO.getStatus().equalsIgnoreCase("finished")&&!activityRuleDO1.getStatus().equalsIgnoreCase("unfinished")){
            CreditsDetailDO creditsLogDetailDO = new CreditsDetailDO();
            if (creditsLogDetailDOList == null || creditsLogDetailDOList.size() ==0){
                creditsLogDetailDO.setTotal(Long.parseLong(activityDO.getIntegrate()));
            }else {
                creditsLogDetailDO.setTotal(Long.parseLong(activityDO.getIntegrate().replace("+",""))+ creditsLogDetailDOList.get(0).getTotal());
            }
            creditsLogDetailDO.setPatientId(activityRuleDO.getPatientId());
            creditsLogDetailDO.setIntegrate("+"+ activityRuleDO.getIntegrate());
            creditsLogDetailDO.setIntegrateType("HEALTH_ACTIVITY");
            creditsLogDetailDO.setIntegrateId(activityRuleDO.getActivityId());
            creditsLogDetailDO.setStatus("unused");
            creditsLogDetailDO.setCommunity(activityDO.getCommunity());
            credittsLogDetailDao.save(creditsLogDetailDO);
        }
        Envelop<Boolean> envelop = new Envelop<>();
        envelop.setObj(true);
        return envelop;
    }*/
}
