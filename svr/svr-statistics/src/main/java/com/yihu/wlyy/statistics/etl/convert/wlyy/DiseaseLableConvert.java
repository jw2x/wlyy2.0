package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.etl.convert.wlyy.model.HealthLable;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/5.
 */

public class DiseaseLableConvert implements Convert {


    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        List<DataModel> returnList = new ArrayList<>();
        //得到全部的服务标签
        String sql = "SELECT " +
                "  spli.label AS health_lable, " +
                "  spli.patient " +
                "FROM " +
                "  wlyy_sign_patient_label_info spli " +
                "WHERE " +
                "  spli.label_type = '3' " +
                "AND spli. STATUS = '1'";
        List<HealthLable> healthLables = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HealthLable.class));
        //疾病标签可能有多个 转mapkey是患者code value是服务标签的code
        Map<String, List<String>> healthLablesMap = new HashMap<>();
        healthLables.stream().forEach(one -> {
            List<String> value = new ArrayList<String>();
            if (healthLablesMap.containsKey(one.getPatient())) {
                value = healthLablesMap.get(one.getPatient());
            }
            value.add(one.getHealthLable());
            healthLablesMap.put(one.getPatient(),value);
        });
        //给DataModel复制维度数据
        oneList.stream().forEach(one -> {
            try {
                //如果没有标签信息默认在未分组
                List<String> value = healthLablesMap.get(one.getPatient());
                if(value==null||value.size()==0){
                    value=new ArrayList<String>();
                    value.add("0");
                }
                //如果
                for (int i = 0; i < value.size(); i++) {
                    DataModel dataModelTemp = new DataModel();
                    BeanUtils.copyProperties(one, dataModelTemp);
                    DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(dataModelTemp, value.get(i));
                    returnList.add(dataModelTemp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return returnList;
    }

}
