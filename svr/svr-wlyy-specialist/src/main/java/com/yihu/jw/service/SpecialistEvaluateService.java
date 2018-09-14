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
import java.util.*;

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
        String patientCode = jsonObject.getString("patient");
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
                specialistEvaluateDO.setPatient(patientCode);
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
                specialistEvaluateLabelDO.setPatient(patientCode);
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
        String sql = ISqlUtils.getSql(specialistEvaluateDO,1,100,"*");
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

    public MixEnvelop<JSONObject,JSONObject> selectByDoctor(String doctor){
        MixEnvelop<JSONObject,JSONObject> envelop = new MixEnvelop<>();
        JSONObject object = new JSONObject();
        String sql = "select AVG(score) AS total from wlyy_specialist_evaluate_score where doctor = '"+doctor+"'";
        String sql1 = "select AVG(score) AS total from wlyy_specialist_evaluate where doctor = '"+doctor+"' AND evaluate_type = 1";
        String sql2 =  "select AVG(score) AS total from wlyy_specialist_evaluate where doctor = '"+doctor+"' AND evaluate_type = 2";
        String sql3 =  "select AVG(score) AS total from wlyy_specialist_evaluate where doctor = '"+doctor+"' AND evaluate_type = 3";
        String sql4 = "select * from wlyy_specialist_evaluate where doctor = '"+doctor+"'";
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        Double totalScore = 0.0;
        if(rstotal!=null&&rstotal.size()>0){
            Object object1 = rstotal.get(0).get("total");
            if (object1!=null){
                totalScore = Double.parseDouble(object1.toString());
            }
        }
        List<Map<String,Object>> rstotal1 = jdbcTemplate.queryForList(sql1);
        Double totalScore1 = 0.0;
        if(rstotal1!=null&&rstotal1.size()>0){
            Object object1 = rstotal1.get(0).get("total");
            if (object1!=null){
                totalScore1 = Double.parseDouble(object1.toString());
            }
        }
        List<Map<String,Object>> rstotal2 = jdbcTemplate.queryForList(sql2);
        Double totalScore2 = 0.0;
        if(rstotal2!=null&&rstotal2.size()>0){
            Object object1 = rstotal2.get(0).get("total");
            if (object1!=null){
                totalScore2 = Double.parseDouble(object1.toString());
            }
        }
        List<Map<String,Object>> rstotal3 = jdbcTemplate.queryForList(sql3);
        Double totalScore3 = 0.0;
        if(rstotal3!=null&&rstotal3.size()>0){
            Object object1 = rstotal3.get(0).get("total");
            if (object1!=null){
                totalScore3 = Double.parseDouble(object1.toString());
            }
        }
        String sql5 = "select * from wlyy_specialist_evaluate WHERE doctor = '"+doctor+"'";
        String sql6 = "select * from wlyy_specialist_evaluate WHERE doctor IN (select doctor from wlyy_specialist_evaluate_score where score > 71 and doctor = '"+doctor+"')";
        String sql7 = "select * from wlyy_specialist_evaluate WHERE doctor IN (select doctor from wlyy_specialist_evaluate_score where score > 41 and score < 71 and doctor = '"+doctor+"')";
        String sql8 = "select * from wlyy_specialist_evaluate WHERE doctor IN (select doctor from wlyy_specialist_evaluate_score where score < 41 and doctor = '"+doctor+"')";
        List<SpecialistEvaluateDO> specialistEvaluateDOS = jdbcTemplate.query(sql5,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS1 = jdbcTemplate.query(sql5,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS3 = jdbcTemplate.query(sql6,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS4 = jdbcTemplate.query(sql6,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS5 = jdbcTemplate.query(sql7,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS6 = jdbcTemplate.query(sql7,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS7 = jdbcTemplate.query(sql8,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        List<SpecialistEvaluateDO> specialistEvaluateDOS8 = jdbcTemplate.query(sql8,new BeanPropertyRowMapper(SpecialistEvaluateDO.class));
        JSONArray array = new JSONArray();
        JSONArray array3 = new JSONArray();
        JSONArray array4 = new JSONArray();
        JSONArray array5 = new JSONArray();
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();
        JSONObject object4 = new JSONObject();
        JSONObject object5 = new JSONObject();
        //全部
        for (int i=0;i<specialistEvaluateDOS.size();i++){
            JSONArray array1 = new JSONArray();
            JSONObject object1 = new JSONObject();
            boolean isTrue = false;
            SpecialistEvaluateDO specialistEvaluateDO = specialistEvaluateDOS.get(i);
            Set<String> set = new HashSet<>();
            Set<Double> scoreSet = new HashSet<>();
            for (int j=0;j<specialistEvaluateDOS1.size();j++){
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDOS1.get(j);
                JSONObject jsonObject = new JSONObject();
                if (specialistEvaluateDO.getRelationCode().equals(specialistEvaluateDO1.getRelationCode())&&specialistEvaluateDO.getPatient().equals(specialistEvaluateDO1.getPatient())&&specialistEvaluateDO.getDoctor().equals(specialistEvaluateDO1.getDoctor())){
                    String scoreSql = "select * from wlyy_specialist_evaluate_score where relation_code = '"+specialistEvaluateDO1.getRelationCode()+"' and patient = '"+specialistEvaluateDO1.getPatient()+"'";
                    List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS =jdbcTemplate.query(scoreSql,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
                    if (specialistEvaluateScoreDOS != null && specialistEvaluateScoreDOS.size()!=0){
                        scoreSet.add(specialistEvaluateScoreDOS.get(0).getScore());
                    }
                    set.add(specialistEvaluateDO1.getPatient());
                    array1.add(specialistEvaluateDO1);
                }
            }
            object1.put("patient",set);
            object1.put("score",scoreSet);
            object1.put("evaluate",array1);
            if (array1.size() !=0&&array1 != null){
                specialistEvaluateDOS1.removeAll(array1);
                array.add(object1);
            }
        }
        //好评
        for (int i=0;i<specialistEvaluateDOS3.size();i++){
            JSONArray array1 = new JSONArray();
            JSONObject object1 = new JSONObject();
            boolean isTrue = false;
            SpecialistEvaluateDO specialistEvaluateDO = specialistEvaluateDOS3.get(i);
            Set<String> set = new HashSet<>();
            Set<Double> scoreSet = new HashSet<>();
            for (int j=0;j<specialistEvaluateDOS4.size();j++){
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDOS4.get(j);
                JSONObject jsonObject = new JSONObject();
                if (specialistEvaluateDO.getRelationCode().equals(specialistEvaluateDO1.getRelationCode())&&specialistEvaluateDO.getPatient().equals(specialistEvaluateDO1.getPatient())&&specialistEvaluateDO.getDoctor().equals(specialistEvaluateDO1.getDoctor())){
                    String scoreSql = "select * from wlyy_specialist_evaluate_score where relation_code = '"+specialistEvaluateDO1.getRelationCode()+"' and patient = '"+specialistEvaluateDO1.getPatient()+"'";
                    List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS =jdbcTemplate.query(scoreSql,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
                    if (specialistEvaluateScoreDOS != null && specialistEvaluateScoreDOS.size()!=0){
                        scoreSet.add(specialistEvaluateScoreDOS.get(0).getScore());
                    }
                    set.add(specialistEvaluateDO1.getPatient());
                    array1.add(specialistEvaluateDO1);
                }
            }
            object1.put("patient",set);
            object1.put("score",scoreSet);
            object1.put("evaluate",array1);
            if (array1.size() !=0&&array1 != null){
                specialistEvaluateDOS4.removeAll(array1);
                array3.add(object1);
            }
        }
        //中评
        for (int i=0;i<specialistEvaluateDOS5.size();i++){
            JSONArray array1 = new JSONArray();
            JSONObject object1 = new JSONObject();
            boolean isTrue = false;
            SpecialistEvaluateDO specialistEvaluateDO = specialistEvaluateDOS5.get(i);
            Set<String> set = new HashSet<>();
            Set<Double> scoreSet = new HashSet<>();
            for (int j=0;j<specialistEvaluateDOS6.size();j++){
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDOS6.get(j);
                JSONObject jsonObject = new JSONObject();
                if (specialistEvaluateDO.getRelationCode().equals(specialistEvaluateDO1.getRelationCode())&&specialistEvaluateDO.getPatient().equals(specialistEvaluateDO1.getPatient())&&specialistEvaluateDO.getDoctor().equals(specialistEvaluateDO1.getDoctor())){
                    String scoreSql = "select * from wlyy_specialist_evaluate_score where relation_code = '"+specialistEvaluateDO1.getRelationCode()+"' and patient = '"+specialistEvaluateDO1.getPatient()+"'";
                    List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS =jdbcTemplate.query(scoreSql,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
                    if (specialistEvaluateScoreDOS != null && specialistEvaluateScoreDOS.size()!=0){
                        scoreSet.add(specialistEvaluateScoreDOS.get(0).getScore());
                    }
                    set.add(specialistEvaluateDO1.getPatient());
                    array1.add(specialistEvaluateDO1);
                }
            }
            object1.put("patient",set);
            object1.put("score",scoreSet);
            object1.put("evaluate",array1);
            if (array1.size() !=0&&array1 != null){
                specialistEvaluateDOS6.removeAll(array1);
                array4.add(object1);
            }
        }
        //差评
        for (int i=0;i<specialistEvaluateDOS7.size();i++){
            JSONArray array1 = new JSONArray();
            JSONObject object1 = new JSONObject();
            boolean isTrue = false;
            SpecialistEvaluateDO specialistEvaluateDO = specialistEvaluateDOS7.get(i);
            Set<String> set = new HashSet<>();
            Set<Double> scoreSet = new HashSet<>();
            for (int j=0;j<specialistEvaluateDOS8.size();j++){
                SpecialistEvaluateDO specialistEvaluateDO1 = specialistEvaluateDOS8.get(j);
                JSONObject jsonObject = new JSONObject();
                if (specialistEvaluateDO.getRelationCode().equals(specialistEvaluateDO1.getRelationCode())&&specialistEvaluateDO.getPatient().equals(specialistEvaluateDO1.getPatient())&&specialistEvaluateDO.getDoctor().equals(specialistEvaluateDO1.getDoctor())){
                    String scoreSql = "select * from wlyy_specialist_evaluate_score where relation_code = '"+specialistEvaluateDO1.getRelationCode()+"' and patient = '"+specialistEvaluateDO1.getPatient()+"'";
                    List<SpecialistEvaluateScoreDO> specialistEvaluateScoreDOS =jdbcTemplate.query(scoreSql,new BeanPropertyRowMapper(SpecialistEvaluateScoreDO.class));
                    if (specialistEvaluateScoreDOS != null && specialistEvaluateScoreDOS.size()!=0){
                        scoreSet.add(specialistEvaluateScoreDOS.get(0).getScore());
                    }
                    set.add(specialistEvaluateDO1.getPatient());
                    array1.add(specialistEvaluateDO1);
                }
            }
            object1.put("patient",set);
            object1.put("score",scoreSet);
            object1.put("evaluate",array1);
            if (array1.size() !=0&&array1 != null){
                specialistEvaluateDOS8.removeAll(array1);
                array5.add(object1);
            }
        }
        object2.put("evaluate",array);
        object2.put("total",array.size());
        object3.put("evaluate",array3);
        object3.put("total",array3.size());
        object4.put("evaluate",array4);
        object4.put("total",array4.size());
        object5.put("evaluate",array5);
        object5.put("total",array5.size());
        object.put("totalScore",totalScore);
        object.put("1",totalScore1);//1、服务效率，
        object.put("2",totalScore2);// 2、服务态度，
        object.put("3",totalScore3);// 3、专业程度
        object.put("4",object2);//全部
        object.put("5",object3);//好评
        object.put("6",object4);//中评
        object.put("7",object5);//差评
        envelop.setObj(object);
        return envelop;
    }


}
