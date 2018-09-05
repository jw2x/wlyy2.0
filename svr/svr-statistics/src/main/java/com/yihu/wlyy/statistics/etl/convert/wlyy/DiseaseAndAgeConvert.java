package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.etl.convert.wlyy.model.HealthLable;
import com.yihu.wlyy.statistics.util.IdCardUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 疾病年龄
 * Created by chenweida on 2017/10/16.
 */
public class DiseaseAndAgeConvert implements Convert {
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        List<DataModel> returnList = new ArrayList<>();
        Map<String, List<String>> healthLablesMap = new HashMap<>();//key是患者code value是患者标签 1高血压 2糖尿病 3 65岁以上
        //初始化标签Map
        initHealthLabesMap(jdbcTemplate, healthLablesMap, oneList);
        //把标签Map设置到对应的维度里面


        for (DataModel dataModel : oneList) {
            List<String> labels = healthLablesMap.get(dataModel.getPatient());
            if (labels != null && labels.size() > 0) {
                labels.stream().forEach(str -> {
                    try {
                        DataModel dataModelTemp = new DataModel();
                        BeanUtils.copyProperties(dataModel, dataModelTemp);
                        DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(dataModelTemp, str);
                        returnList.add(dataModelTemp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            //判断患者是否是老年人是
            int age = IdCardUtil.getAgeForIdcard(dataModel.getIdcard());
            if (age >= 65) {
                try {
                    DataModel dataModelTemp = new DataModel();
                    BeanUtils.copyProperties(dataModel, dataModelTemp);
                    DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(dataModelTemp, "3");
                    returnList.add(dataModelTemp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return returnList;
    }

    private void initHealthLabesMap(JdbcTemplate jdbcTemplate, Map<String, List<String>> healthLablesMap, List<DataModel> oneList) {
        //得到患者的疾病标签（只要高血压 糖尿病）
        String sql = "SELECT " +
                "  spli.label AS health_lable, " +
                "  spli.patient " +
                "FROM " +
                "  wlyy_sign_patient_label_info spli " +
                "WHERE " +
                "  spli.label_type = '3' " +
                "AND spli. STATUS = '1' " +
                "AND ( spli.label =1 or spli.label =2 )";
        List<HealthLable> healthLables = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HealthLable.class));
        healthLables.stream().forEach(one -> {
            List<String> labels = healthLablesMap.get(one.getPatient());
            if (labels == null) {
                labels = new ArrayList<String>();
            }
            labels.add(one.getHealthLable());
            healthLablesMap.put(one.getPatient(), labels);
        });
//        //-得到患者的老年人标签
//        oneList.stream().forEach(one->{
//            int age = IdCardUtil.getAgeForIdcard(one.getIdcard());
//            if(age>=65){
//                num++;
//                List<String> labels = healthLablesMap.get(one.getPatient());
//                if (labels == null) {
//                    labels = new ArrayList<String>();
//                }
//                labels.add("3");
//                healthLablesMap.put(one.getPatient(), labels);
//            }
//        });
//        System.out.println("num:" + num);
    }
}
