package com.yihu.jw.service;/**
 * Created by nature of king on 2018/8/22.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
     * @param jsonObject
     * @return
     */
    public MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> createEvaluate(JSONObject jsonObject) throws IOException {
        MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> envelop = new MixEnvelop<>();
        JSONArray evaluate = jsonObject.getJSONArray("evaluate");
        JSONArray evaluateLabel = jsonObject.getJSONArray("evaluateLabel");
        List<SpecialistEvaluateDO> specialistEvaluateDOList = new ArrayList<>();
        List<SpecialistEvaluateLabelDO> specialistEvaluateLabelDOS = new ArrayList<>();
        for (int i = 0;i<evaluate.size();i++){
            SpecialistEvaluateDO specialistEvaluateDO = toEntity(evaluate.getJSONObject(i).toJSONString(),SpecialistEvaluateDO.class);
            specialistEvaluateDOList.add(specialistEvaluateDO);
        }
        for (int i =0;i<evaluateLabel.size();i++){
            SpecialistEvaluateLabelDO specialistEvaluateLabelDO = toEntity(evaluateLabel.getJSONObject(i).toJSONString(),SpecialistEvaluateLabelDO.class);
            specialistEvaluateLabelDOS.add(specialistEvaluateLabelDO);
        }
        String doctor = null;
        String relationCode = null;
        String patient = null;
        Double total = 0d;
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
                specialistEvaluateLabelDO.setFlag(specialistEvaluateDO.getFlag());
                specialistEvaluateLabelDO.setCreateTime(new Date());
                specialistEvaluateLabelDO.setUpdateTime(new Date());
                specialistEvaluateLabelDao.save(specialistEvaluateLabelDO);
                total = total + specialistEvaluateDO.getScore();
            }
        }
        if (specialistEvaluateLabelDOS.size()!=0&&specialistEvaluateLabelDOS != null){
            for (SpecialistEvaluateLabelDO specialistEvaluateLabelDO:specialistEvaluateLabelDOS){
                specialistEvaluateLabelDO.setSaasId("dev");
                specialistEvaluateLabelDO.setEvaluateType(1);
                specialistEvaluateLabelDao.save(specialistEvaluateLabelDO);
            }
        }
        double score = total/3;
        BigDecimal   b   =   new   BigDecimal(score);
        double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        SpecialistEvaluateScoreDO specialistEvaluateScoreDO = new SpecialistEvaluateScoreDO();
        specialistEvaluateScoreDO.setRelationCode(relationCode);
        specialistEvaluateScoreDO.setEvaluateType(1);
        specialistEvaluateScoreDO.setDoctor(doctor);
        specialistEvaluateScoreDO.setSaasId("dev");
        specialistEvaluateScoreDO.setScore(f1);
        specialistEvaluateScoreDO.setCreateTime(new Date());
        specialistEvaluateScoreDO.setUpdateTime(new Date());
        specialistEvaluateScoreDao.save(specialistEvaluateScoreDO);
        return envelop;
    }


    /**
     * 查询评论
     *
     * @param specialistEvaluateDO
     * @return
     */
    public MixEnvelop<JSONObject,JSONObject> selectByCondition(SpecialistEvaluateDO specialistEvaluateDO){
        MixEnvelop<JSONObject,JSONObject> envelop = new MixEnvelop<>();
        String sql = ISqlUtils.getSql(specialistEvaluateDO,1,1,"*");
        List<SpecialistEvaluateDO> specialistEvaluateDOS = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        String sql1 = "select * from wlyy_specialist_evaluate_label where doctor = '"+specialistEvaluateDO.getDoctor()+"' " +
                "and relation_code = '"+specialistEvaluateDO.getRelationCode()+"' and patient = '"+specialistEvaluateDO.getPatient()+"'";
        List<SpecialistEvaluateLabelDO> specialistEvaluateLabelDOS = jdbcTemplate.query(sql1,new BeanPropertyRowMapper(SpecialistEvaluateLabelDO.class));
        String sql2 = "select * from wlyy_specialist_evaluate_score where doctor = '"+specialistEvaluateDO.getDoctor()+"' " +
                "and relation_code = '"+specialistEvaluateDO.getRelationCode()+"'";
        List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS = jdbcTemplate.query(sql2,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
        JSONObject object = new JSONObject();
        object.put("score",specialistEvaluateScoreDOS);
        object.put("evaluate",specialistEvaluateDOS);
        object.put("evaluateLabel",specialistEvaluateLabelDOS);
        List<JSONObject> array = new ArrayList<>();
        array.add(object);
        envelop.setDetailModelList(array);
        return envelop;
    }


    /**
     * 更新评价
     *
     * @param specialistEvaluateDOList
     * @return
     */
    public MixEnvelop<Boolean,Boolean> update(List<SpecialistEvaluateDO> specialistEvaluateDOList){
        MixEnvelop<Boolean,Boolean> envelop = new MixEnvelop<>();
        String doctor = null;
        String relationCode = null;
        String patient = null;
        Double total = 0d;
        if (specialistEvaluateDOList != null && specialistEvaluateDOList.size() != 0){
            for (SpecialistEvaluateDO specialistEvaluateDO:specialistEvaluateDOList){
                specialistEvaluateDO.setSaasId("dev");
                specialistEvaluateDO.setRelationType(1);
                specialistEvaluateDO.setCreateTime(new Date());
                specialistEvaluateDO.setUpdateTime(new Date());
                doctor = specialistEvaluateDO.getDoctor();
                relationCode = specialistEvaluateDO.getRelationCode();
                patient = specialistEvaluateDO.getPatient();
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDao.save(specialistEvaluateDO);
                SpecialistEvaluateLabelDO specialistEvaluateLabelDO = specialistEvaluateLabelDao.findOne(specialistEvaluateDO.getLabelId());
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
        BigDecimal   b   =   new   BigDecimal(score);
        double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        String sql2 = "select * from wlyy_specialist_evaluate_score where doctor = '"+doctor+"' " +
                "and relation_code = '"+relationCode+"'";
        List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS = jdbcTemplate.query(sql2,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
        SpecialistEvaluateScoreDO specialistEvaluateScoreDO = specialistEvaluateScoreDOS.get(0);
        specialistEvaluateScoreDO.setRelationCode(relationCode);
        specialistEvaluateScoreDO.setEvaluateType(1);
        specialistEvaluateScoreDO.setDoctor(doctor);
        specialistEvaluateScoreDO.setSaasId("dev");
        specialistEvaluateScoreDO.setScore(f1);
        specialistEvaluateScoreDO.setCreateTime(new Date());
        specialistEvaluateScoreDO.setUpdateTime(new Date());
        specialistEvaluateScoreDao.save(specialistEvaluateScoreDO);
        return envelop;
    }


}
