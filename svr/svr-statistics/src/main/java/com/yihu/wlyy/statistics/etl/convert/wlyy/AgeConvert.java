package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.util.Contant;
import com.yihu.wlyy.statistics.util.IdCardUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/5.
 */
public class AgeConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp ) {
        Map<String,Object> tempMap = new HashMap<>();
        List<DataModel> result = new ArrayList<>();
        for(DataModel one:oneList) {
            try {
                Object value = one.getIdcard();
                Integer age= IdCardUtil.getAgeForIdcard(String.valueOf(value));
                String key = getAgeCode(age);
                tempMap.put(String.valueOf(value),one);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, key);
//                result.add(one);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return oneList;
    }

    public String getAgeCode(Integer age) {
        if (age <= 6) {
            return Contant.convert.level_age_1;
        } else if (age >= 7 && age <= 18) {
            return Contant.convert.level_age_2;
        } else if (age >= 19 && age <= 30) {
            return Contant.convert.level_age_3;
        } else if (age >= 31 && age <= 50) {
            return Contant.convert.level_age_4;
        } else if (age >= 51 && age < 65) {
            return Contant.convert.level_age_5;
        } else {
            return Contant.convert.level_age_6;
        }
    }
}
