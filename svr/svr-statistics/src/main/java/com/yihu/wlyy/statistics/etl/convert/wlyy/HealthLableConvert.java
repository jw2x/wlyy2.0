package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.etl.convert.wlyy.model.HealthLable;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/5.
 */

public class HealthLableConvert implements Convert {


    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        //得到全部的服务标签
        String sql = "SELECT " +
                "  spli.label AS health_lable, " +
                "  spli.patient " +
                "FROM " +
                "  wlyy_sign_patient_label_info spli " +
                "WHERE " +
                "  spli.label_type = '2' " +
                "AND spli. STATUS = '1'";
        List<HealthLable> healthLables = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HealthLable.class));
        Map<String, String> healthLablesMap = new HashMap<>();
        healthLables.stream().forEach(one -> {
            healthLablesMap.put(one.getPatient(), one.getHealthLable());
        });

        oneList.stream().forEach(one -> {
            try {
                Object value = healthLablesMap.get(one.getPatient());
                if (StringUtils.isEmpty(value)) {
                    value="0";
                }
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return oneList;
    }

}
