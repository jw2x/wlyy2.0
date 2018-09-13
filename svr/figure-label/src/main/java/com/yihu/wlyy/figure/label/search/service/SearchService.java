package com.yihu.wlyy.figure.label.search.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.base.es.config.ElastricSearchHelper;
import com.yihu.base.hbase.HBaseHelper;
import com.yihu.wlyy.figure.label.constant.BusinessConstant;
import com.yihu.wlyy.figure.label.constant.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author litaohong on 2018/5/17
 * @project patient-co-management
 * 标签搜索服务
 */
@Service
public class SearchService {
    private Logger logger = LoggerFactory.getLogger(Service.class);

    @Autowired
    private ElastricSearchHelper elastricSearchHelper;

    @Autowired
    private HBaseHelper hBaseHelper;

    @Autowired
    private HbaseTemplate hbaseTemplate;
    /**
     * 用户基本信息
     * @param idcard
     * @return
     */
    public JSONObject getBaseInfo(String idcard){
        List<Map<String, Object>> result = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + " where idcard = '" + idcard + "'" ;
        result = elastricSearchHelper.excuceSQL(sql);
        result.forEach(
                map -> {
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.name_dict)){
                        jsonObject.put("name",String.valueOf(map.get("labelValue")));
                    }
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.sex_dict)){
                        jsonObject.put("sex",String.valueOf(map.get("labelName")));
                    }
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.age_dict)){
                        jsonObject.put("age",String.valueOf(map.get("labelName")));
                    }
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.birth_dict)){
                        jsonObject.put("birth",String.valueOf(map.get("labelValue")));
                    }
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.height_dict)){
                        String height = String.valueOf(map.get("labelName")) + String.valueOf(map.get("labelValue"));
                        jsonObject.put("height",height);
                    }
                    if(StringUtils.endsWithIgnoreCase(String.valueOf(map.get("dictCode")),ConstantUtil.weight_dict)){
                        String weight = String.valueOf(map.get("labelName")) + String.valueOf(map.get("labelValue"));
                        jsonObject.put("weight",weight);
                    }
                }
        );
        if(null == jsonObject.get("name")){
            jsonObject.put("name","");
        }
        if(null == jsonObject.get("sex")){
            jsonObject.put("sex","");
        }
        if(null == jsonObject.get("age")){
            jsonObject.put("age","");
        }
        if(null == jsonObject.get("birth")){
            jsonObject.put("birth","");
        }
        if(null == jsonObject.get("height")){
            jsonObject.put("height","");
        }
        if(null == jsonObject.get("weight")){
            jsonObject.put("weight","");
        }

        return jsonObject;
    }

    /**
     * 用户病种信息
     * @param idcard
     * @return
     */
    public JSONObject getDiseaseInfo(String idcard) {
        JSONObject jsonObject = new JSONObject();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + " where idcard = '" + idcard + "' and dictCode in ('" + ConstantUtil.ICD10_dict + "','" + ConstantUtil.health_problem_dict + "')";
        List<Map<String, Object>> result = elastricSearchHelper.excuceSQL(sql);
        Set<String> set = new HashSet<>();
        result.forEach(
                map -> {
                    if (!StringUtils.isEmpty(map.get("labelValue"))) {
                        set.add(map.get("labelValue").toString());
                    }
                });
        jsonObject.put("label",set);
        return jsonObject;
    }

    /**
     * 用户就诊记录
     * @param idcard
     * @return
     */
    public JSONObject getAppointmentInfo(String idcard) {
        JSONObject result = new JSONObject();
        result.put("idcard",idcard);
        StringBuilder sqlBuilder = new StringBuilder();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + " where idcard = '" + idcard + "' and dictCode = '" + ConstantUtil.online_appoinment_dict + "',";
        List<Map<String, Object>> list = elastricSearchHelper.excuceSQL(sql);
        JSONArray jsonArray = new JSONArray();
        list.forEach(
                map -> {
                    //如果有医生姓名，表示是医生代预约
                    if(null == map.get("doctor")){
                            return;
                    }
                    JSONObject jsonObject = new JSONObject();
                    String labelName = String.valueOf(map.get("labelName"));
                    jsonObject.put("hospitalName", labelName.split("-")[0]);
                    jsonObject.put("deptName", labelName.split("-")[1]);
                    jsonObject.put("apponinementTime", map.get("sourceTime"));
                    jsonObject.put("doctor",map.get("doctor"));
                    jsonArray.add(jsonObject);
                }
        );
        JSONObject appoint = new JSONObject();
        appoint.put("appointdata",jsonArray);
        result.putAll(appoint);
        return result;
    }


    /**
     * 获取历史健康问题,默认获取所有
     * @param idcard
     * @param date
     * @return
     */
    public String getHistoryHealthProblem(String idcard,String date){
        JSONObject result = new JSONObject();
        StringBuilder sqlBuilder = new StringBuilder();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + " where idcard = '" + idcard + "' and dictCode = '" + ConstantUtil.health_problem_dict;
        sqlBuilder.append(sql);
        if(!StringUtils.isEmpty(date)){
            sqlBuilder.append(" and sourceTime <= '").append(date).append("'");
        }
        List<Map<String, Object>> list = elastricSearchHelper.excuceSQL(sqlBuilder.toString());
        Set<String> set = new HashSet<>();
        list.forEach(
                map -> {
                    String name = String.valueOf(map.get("labelName"));
                    if(name.contains(";")){
                        for(String str : name.split(";")){
                            set.add(str);
                        }
                    }else{
                        set.add(name);
                    }
                }
        );
        result.put("historyHealthProblem",set);
        return result.toJSONString();
    }

    /**
     * 医疗云档案查询接口（包括基本信息，疾病标签如慢病新生儿等，就诊记录信息）
     * @param idcard
     * @return
     */
    public String getEHRRecordInfo(String idcard) {
        JSONObject result = new JSONObject();

        // 基础信息标签
        JSONObject baseinfo = getBaseInfo(idcard);
        baseinfo.put("idcard",idcard);
        result.putAll(baseinfo);

        //疾病标签
        JSONObject diesase = getDiseaseInfo(idcard);
        result.putAll(diesase);

        //就诊记录标签
        JSONObject appointmentinfo = getAppointmentInfo(idcard);
        if(appointmentinfo.getJSONArray("appointdata").size() > 0){
            result.putAll(appointmentinfo);
        }

        return result.toJSONString();
    }

    /**
     * 获取健康教育文章记录
     * @return
     */
    public String getHealthArticle(String idcard){
        JSONArray jsonArray = new JSONArray();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + "where dictCode = '" + ConstantUtil.health_edu_artical_receive_dict + "' and idcard ='" + idcard + "'";
        List<Map<String, Object>> list = elastricSearchHelper.excuceSQL(sql.toString());
        list.forEach(
                map -> {
                    JSONObject result = new JSONObject();
                    // 文章标题
                    String title = String.valueOf(map.get("labelName"));
                    result.put("title",title);
                    // 文章分类标题
                    String name = String.valueOf(map.get("labelValue"));
                    if (!StringUtils.isEmpty(name) && name.contains(BusinessConstant.separator)) {
                        String[] nameArr = name.split(BusinessConstant.separator);
                        result.put("firstCategoryName", nameArr[0]);
                        result.put("secondCategoryName", nameArr[1]);
                    }
                    // 文章推送时间
                    Object sendTime = map.get("sourceTime");
                    result.put("sendTime",sendTime);
                    // 文章推送人
                    Object sender = map.get("sendName");
                    result.put("sender",sender);
                    jsonArray.add(result);
                }

        );
        return jsonArray.toJSONString();
    }

    /**
     *
     * 获取随访记录
     * @return
     */
    public String getFollowupRecord(String idcard){
        JSONArray jsonArray = new JSONArray();
        String sql = "select * from " + ConstantUtil.figure_label_es_index + "/" + ConstantUtil.figure_label_es_type + "where dictCode = '" + ConstantUtil.followup_dict + "' and idcard ='" + idcard + "'";
        List<Map<String, Object>> list = elastricSearchHelper.excuceSQL(sql.toString());
        list.forEach(
                map -> {
                    JSONObject result = new JSONObject();

                    // 随访疾病名称
                    String followupName = String.valueOf(map.get("labelName"));
                    result.put("followupName", followupName);
                    // 随访方式
                    String followupStyle = String.valueOf(map.get("labelValue"));
                    result.put("followupStyle", followupStyle);
                    // 随访时间
                    Object followupTime = map.get("sourceTime");
                    result.put("followupTime", followupTime);
                    // 随访医生
                    Object followupDoctor = map.get("sendName");
                    result.put("followupDoctor", followupDoctor);

                    // 随访评价
                    Object followupEvaluate = map.get("followupEvaluate");
                    result.put("followupDoctor", followupEvaluate);
                    jsonArray.add(result);
                }

        );
        return jsonArray.toJSONString();
    }

}
