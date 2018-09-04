package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 长处方疾病
 * Created by zhangdan on 2017/10/26.
 */
public class PrescriptionPatientDiseaseConvert implements Convert {
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        List<DataModel> returnList = new ArrayList<>();
        //初始化标签Map
        //把标签Map设置到对应的维度里面
        List<String> keyList = null;
        Map<String,List<String>>  returnMap=initHealthLabesMap(jdbcTemplate, temp);
        for (DataModel one : oneList) {
            try {
                keyList = returnMap.get(one.getPatient());
                if (keyList != null) {
                    for (int i=0;i<keyList.size();i++) {
                        String key =keyList.get(i);
                        DataModel dataModelTemp = new DataModel();
                        BeanUtils.copyProperties(one, dataModelTemp);
                        DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(dataModelTemp, key);
                        returnList.add(dataModelTemp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }

    private  Map<String,List<String>> initHealthLabesMap(JdbcTemplate jdbcTemplate, DimensionQuotaDO temp) {
//        //得到长处方的疾病标签（只要高血压 糖尿病）
        Map<String,List<String>>  returnMap = new HashMap<>();
        String sql = "SELECT distinct " +
                "  d.health_problem AS healthProblem, " +
                "  p.patient AS patient " +
                "FROM " +
                "  wlyy_prescription p, " +
                "  wlyy_prescription_diagnosis d " +
                "WHERE " +
                "p.`code`=d.prescription_code AND p.status=100 ";
        List<Map<String, Object>> diagnosisList = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> map : diagnosisList) {
            String key= (String) map.get("patient");
            List<String> healthProblemList=returnMap.get(key);
            if(healthProblemList==null){
                healthProblemList=new ArrayList<>();
            }
            healthProblemList.add((String) map.get("healthProblem"));
            returnMap.put(key,healthProblemList);
        }
        return returnMap;
    }
}
