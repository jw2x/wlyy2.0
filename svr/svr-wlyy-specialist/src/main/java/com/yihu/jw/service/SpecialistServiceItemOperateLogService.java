package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/17.
 */

import com.yihu.jw.dao.SpecialistServiceItemOperateLogDao;
import com.yihu.jw.entity.specialist.SpecialistServiceItemOperateLogDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wangzhinan
 * @create 2018-08-17 8:48
 * @desc 服务项目操作记录
 **/
@Service
@Transactional
public class SpecialistServiceItemOperateLogService {

    @Autowired
    private SpecialistServiceItemOperateLogDao specialistServiceItemOperateLogDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param specialistServiceItemOperateLogDO
     * @return
     */
    public MixEnvelop<Boolean,Boolean> insert(SpecialistServiceItemOperateLogDO specialistServiceItemOperateLogDO){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        specialistServiceItemOperateLogDao.save(specialistServiceItemOperateLogDO);
        envelop.setObj(true);
        return envelop;
    }

    /**
     *  查询操作记录
     *
     * @param specialistServiceItemOperateLogDO
     * @param page
     * @param pageSize
     * @return
     */
    public MixEnvelop<SpecialistServiceItemOperateLogDO,SpecialistServiceItemOperateLogDO> select(SpecialistServiceItemOperateLogDO specialistServiceItemOperateLogDO,Integer page,Integer pageSize){
        MixEnvelop<SpecialistServiceItemOperateLogDO,SpecialistServiceItemOperateLogDO> envelop = new MixEnvelop<>();
        String sql = ISqlUtils.getSql(specialistServiceItemOperateLogDO,page,pageSize,"*");
        List<SpecialistServiceItemOperateLogDO> specialistServiceItemOperateLogDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistServiceItemOperateLogDO.class));
        String sqlcount = new ISqlUtils().getSql(specialistServiceItemOperateLogDO,0,0,"count");
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sqlcount);
        Long count = 0L;
        if(rstotal!=null&&rstotal.size()>0){
            count = (Long) rstotal.get(0).get("total");
        }
        return MixEnvelop.getSuccessListWithPage(HealthBankMapping.api_success,specialistServiceItemOperateLogDOS,page,pageSize,count);
    }
}
