package com.yihu.jw.service;

import com.yihu.jw.dao.PatientHospitalRecordDao;
import com.yihu.jw.dao.SpecialistPatientRelationDao;
import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.specialist.*;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

/**
 * Created by zhangdan on 2018/7/6.
 */
@Service
@Transactional
public class SpecialistScreenResultService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${basedb.name}")
    private String basedb;



    public Envelop<SurveyScreenResultVo> getScreenList(String doctor,int type,Integer page,Integer size)throws ParseException {
        String sql = "SELECT ssr.id,ssr.`code`,ssr.template_code templateCode,ssr.template_title templateTitle,ssr.disease,ssr.doctor,ssr.parent_code patientCode,ssr.patient_name patientName,ssr.screen_result_code screenResultCode,ssr.screen_result_score screenResultScore,ssr.screen_result screenResult,ssr.is_danger isDanger,ssr.`order`,ssr.following,ssr.is_educate isEducate,ssr.over,ssr.reservation_code reservationCode,ssr.czrq,ssr.is_again isAgain,ssr.parent_code parentCode,ssr.origin_code originCode,ssr.advice_code adviceCode,ssr.other_advice otherAdvice,ssr.source" +
                    " FROM  "+basedb+".wlyy_survey_screen_result ssr LEFT JOIN wlyy_specialist_patient_relation spr ON ssr.patient_code= spr.patient WHERE spr.`status`>=0 AND spr.sign_status>0 AND spr.doctor='"+doctor+"' AND ssr.over=1";
        if (type==1){
            sql +=" AND ssr.following =1";
        }
        if (type==2){
            sql +=" AND ssr.order = 1";
        }
        List<Map<String,Object>> rstotal = jdbcTemplate.queryForList(sql);
        int count = 0;
        if(rstotal!=null&&rstotal.size()>0){
            count = rstotal.size();
        }
        sql += " ORDER BY ssr.czrq DESC LIMIT "+(page-1)*size+","+size;
        List<SurveyScreenResultVo> surveyScreenResultVoList = jdbcTemplate.query(sql,new BeanPropertyRowMapper(SurveyScreenResultVo.class));
        return Envelop.getSuccessListWithPage(SpecialistMapping.api_success,surveyScreenResultVoList,page,size,Long.valueOf(count));
    }

    public Envelop<Map<String,Object>> getResultCount(String doctor){
        String followSql = "SELECT count(*) followNumber" +
                " FROM  "+basedb+".wlyy_survey_screen_result ssr LEFT JOIN wlyy_specialist_patient_relation spr ON ssr.patient_code= spr.patient WHERE spr.`status`>=0 AND spr.sign_status>0 AND spr.doctor='"+doctor+"' AND ssr.over=1 AND ssr.following =1";

        String orderSwl = "SELECT count(*) orderNumber" +
                " FROM  "+basedb+".wlyy_survey_screen_result ssr LEFT JOIN wlyy_specialist_patient_relation spr ON ssr.patient_code= spr.patient WHERE spr.`status`>=0 AND spr.sign_status>0 AND spr.doctor='"+doctor+"' AND ssr.over=1 AND ssr.order =1";

        Map<String,Object> followMap = jdbcTemplate.queryForMap(followSql);
        Map<String,Object> orderMap = jdbcTemplate.queryForMap(orderSwl);
        orderMap.putAll(followMap);
        return Envelop.getSuccess(SpecialistMapping.api_success,orderMap);
    }

    public Envelop<Map<String,Object>> getScreenResultDetail(String code)throws Exception{
        Map<String,Object> map = new HashedMap();
        //登记信息
        String infoSql = "SELECT ssr.id,ssr.`code`,ssr.template_code templateCode,ssr.template_title templateTitle,ssr.disease,ssr.doctor,ssr.parent_code patientCode,ssr.patient_name patientName,ssr.screen_result_code screenResultCode,ssr.screen_result_score screenResultScore,ssr.screen_result screenResult,ssr.is_danger isDanger,ssr.`order`,ssr.following,ssr.is_educate isEducate,ssr.over,ssr.reservation_code reservationCode,ssr.czrq,ssr.is_again isAgain,ssr.parent_code parentCode,ssr.origin_code originCode,ssr.advice_code adviceCode,ssr.other_advice otherAdvice,ssr.source" +
                " FROM "+basedb+".wlyy_survey_screen_result ssr LEFT JOIN " +basedb+
                ".wlyy_patient p ON ssr.patient_code=p.code where ssr.code ='"+code+"'";
        List<SurveyScreenResultVo> surveyScreenResultVoList = jdbcTemplate.query(infoSql,new BeanPropertyRowMapper<>(SurveyScreenResultVo.class));
        if (surveyScreenResultVoList==null || surveyScreenResultVoList.size()==0){
            return Envelop.getError("没有改筛查结果",-1);
        }
        SurveyScreenResultVo surveyScreenResultVo = surveyScreenResultVoList.get(0);
        String templateCode = surveyScreenResultVo.getTemplateCode();
        String patientCode = surveyScreenResultVo.getPatientCode();
        /*String idcard = String.valueOf(infoMap.get("idcard"));
        infoMap.put("sex",IdCardUtil.getSexForIdcard(idcard));
        infoMap.put("age",IdCardUtil.getAgeForIdcard(idcard));*/
        //String doctorCode = String.valueOf(infoMap.get("doctor"));
       /* Doctor doctor = doctorDao.findByCode(doctorCode);
        if (doctor!=null){
            infoMap.put("doctorName",doctor.getName());
        }*/
        map.put("info",surveyScreenResultVo);
        String healthSql ="SELECT value1,value2 FROM device.wlyy_patient_health_index WHERE user='"+patientCode+"' AND type=3 ORDER BY record_date DESC LIMIT 1";
        List<Map<String,Object>> healthMapList = jdbcTemplate.queryForList(healthSql);
        if (healthMapList!=null && healthMapList.size()>0){
            map.put("health",healthMapList.get(0));
        }

        //题目和答案
        String questionSql = "select code,title,question_comment questionComment,question_type questionType,template_code templateCode,sort,del from "+basedb+".wlyy_survey_template_questions where template_code='"+templateCode+"' and del=1";
        List<SurveyTemplateQuestionsVo> questionList = jdbcTemplate.query(questionSql,new BeanPropertyRowMapper<>(SurveyTemplateQuestionsVo.class));
        String sql = "SELECT soa.*,sto.score FROM "+basedb+".wlyy_survey_option_answers soa LEFT JOIN "+basedb+".wlyy_survey_template_options sto ON soa.options_code= sto.code WHERE soa.screen_result_code=? AND soa.patient=? AND soa.survey_code=?";
        List<Map<String,Object>> optionAnswersList = jdbcTemplate.queryForList(sql,new Object[]{code,patientCode,templateCode});
        for (SurveyTemplateQuestionsVo surveyTemplateQuestionsVo : questionList){
            Map<String,Object> Qusmap = new HashMap<>();
            map.put("question",surveyTemplateQuestionsVo);
            String qusCode = surveyTemplateQuestionsVo.getCode();
            for (Map<String,Object> option : optionAnswersList){
                if (option.get("question_code").equals(qusCode)){
                    map.put("option",option);
                }
            }
            map.put(surveyTemplateQuestionsVo.getSort()+"",map);
        }
        //结果
        String reultSql ="SELECT ssr.screen_result_score,ssr.screen_result,str.advice FROM "+basedb+".wlyy_survey_screen_result ssr LEFT JOIN "+basedb+".wlyy_survey_template_result str ON ssr.screen_result_code = str.code WHERE ssr.code='"+code+"'";
        Map<String,Object> resultMap = jdbcTemplate.queryForMap(reultSql);
        /*int following = surveyScreenResultVo.getFollowing();
        int order = surveyScreenResultVo.getOrder();
        if (following==1){
            String adviceCodes = surveyScreenResultVo.getAdviceCode();
            List<SurveyAdvice> surveyAdviceList = new ArrayList<>();
            if (StringUtils.isNotEmpty(adviceCodes)){
                String[] advicesStr = adviceCodes.split(",");
                for (String adviceCode : advicesStr){
                    surveyAdviceList.add(surveyAdviceDao.getByCode(adviceCode));
                }
            }
            resultMap.put("advice",surveyAdviceList);
        }*/
        map.put("result",resultMap);
        return Envelop.getSuccess(SpecialistMapping.api_success,map);
    }

}
