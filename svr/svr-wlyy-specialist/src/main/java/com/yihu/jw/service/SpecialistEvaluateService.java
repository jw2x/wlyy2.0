package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/22.
 */

import com.yihu.jw.dao.SpecialistEvaluateDao;
import com.yihu.jw.dao.SpecialistEvaluateLabelDao;
import com.yihu.jw.dao.SpecialistEvaluateScoreDao;
import com.yihu.jw.entity.specialist.SpecialistEvaluateDO;
import com.yihu.jw.entity.specialist.SpecialistEvaluateLabelDO;
import com.yihu.jw.entity.specialist.SpecialistEvaluateScoreDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.util.ISqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-08-22 9:48
 * @desc 评论service
 **/
@Transactional
@Service
public class SpecialistEvaluateService extends EnvelopRestEndpoint {

    @Autowired
    private SpecialistEvaluateDao specialistEvaluateDao;
    @Autowired
    private SpecialistEvaluateLabelDao specialistEvaluateLabelDao;
    @Autowired
    private SpecialistEvaluateScoreDao specialistEvaluateScoreDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 添加评论
     *
     * @param specialistEvaluateDOList
     * @return
     */
    public MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> createEvaluate(List<SpecialistEvaluateDO> specialistEvaluateDOList){
        MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> envelop = new MixEnvelop<>();
        String doctor = null;
        String relationCode = null;
        String patient = null;
        Double total = 0.0;
        if (specialistEvaluateDOList != null && specialistEvaluateDOList.size() != 0){
            for (SpecialistEvaluateDO specialistEvaluateDO:specialistEvaluateDOList){
                specialistEvaluateDO.setRelationType(1);
                specialistEvaluateDO.setCreateTime(new Date());
                specialistEvaluateDO.setUpdateTime(new Date());
                doctor = specialistEvaluateDO.getDoctor();
                relationCode = specialistEvaluateDO.getRelationCode();
                patient = specialistEvaluateDO.getPatient();
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDao.save(specialistEvaluateDO);
                SpecialistEvaluateLabelDO specialistEvaluateLabelDO = new SpecialistEvaluateLabelDO();
                specialistEvaluateLabelDO.setSaasId("dev");
                specialistEvaluateLabelDO.setContent(specialistEvaluateDO.getContent());
                specialistEvaluateLabelDO.setDoctor(specialistEvaluateDO1.getDoctor());
                specialistEvaluateLabelDO.setPatient(specialistEvaluateDO1.getPatient());
                specialistEvaluateLabelDO.setEvaluateType(1);
                specialistEvaluateLabelDO.setRelationCode(specialistEvaluateDO.getRelationCode());
                specialistEvaluateLabelDO.setCreateTime(new Date());
                specialistEvaluateLabelDO.setUpdateTime(new Date());
                specialistEvaluateLabelDao.save(specialistEvaluateLabelDO);
                total = total + specialistEvaluateDO.getScore();
            }
        }
        double score = total/3;
        NumberFormat nbf= NumberFormat.getInstance();
        nbf.setMinimumFractionDigits(2);
        SpecialistEvaluateScoreDO specialistEvaluateScoreDO = new SpecialistEvaluateScoreDO();
        specialistEvaluateScoreDO.setRelationCode(relationCode);
        specialistEvaluateScoreDO.setEvaluateType(1);
        specialistEvaluateScoreDO.setDoctor(doctor);
        specialistEvaluateScoreDO.setSaasId("dev");
        specialistEvaluateScoreDO.setScore(Integer.parseInt(nbf.format(score)));
        specialistEvaluateScoreDao.save(specialistEvaluateScoreDO);
        return envelop;
    }


    public MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> selectByCondition(SpecialistEvaluateDO specialistEvaluateDO){
        MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> envelop = new MixEnvelop<>();
        String sql = ISqlUtils.getSql(specialistEvaluateDO,1,1,"*");
        List<SpecialistEvaluateDO> specialistEvaluateDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        String sql1 = "select * from wlyy_specialist_evaluate_label where doctor = '"+specialistEvaluateDO.getDoctor()+"' " +
                "and relation_code = '"+specialistEvaluateDO.getRelationCode()+"' and patient = '"+specialistEvaluateDO.getPatient()+"'";
        List<SpecialistEvaluateLabelDO> specialistEvaluateLabelDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(SpecialistEvaluateLabelDO.class));
        return envelop;
    }

}
