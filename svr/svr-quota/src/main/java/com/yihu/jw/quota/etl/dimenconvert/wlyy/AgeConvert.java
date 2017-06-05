package com.yihu.jw.quota.etl.dimenconvert.wlyy;

import com.yihu.jw.quota.etl.Contant;
import com.yihu.jw.quota.etl.dimenconvert.Convert;
import com.yihu.jw.quota.util.IdCardUtil;
import com.yihu.jw.quota.vo.DataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */

public class AgeConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    public List<DataModel> convert(List oneList, String slaveLevel) {
        oneList.stream().forEach(one -> {
            try {
                Object value = DataModel.class.getMethod("getSlaveKey" + slaveLevel).invoke(one);
                Integer age= IdCardUtil.getAgeForIdcard(String.valueOf(value));
                String key = getAgeCode(age);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
        } else if (age >= 51 && age <= 65) {
            return Contant.convert.level_age_5;
        } else {
            return Contant.convert.level_age_6;
        }
    }
}
