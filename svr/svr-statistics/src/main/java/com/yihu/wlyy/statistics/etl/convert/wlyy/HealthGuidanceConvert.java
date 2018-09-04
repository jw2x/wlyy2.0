package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 健康指导推送统计已读、未读
 * Created by zhangdan on 2017/10/26.
 */
public class HealthGuidanceConvert implements Convert {

    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        for (DataModel one : oneList) {
            try {
                Object value = DataModel.class.getMethod("get" + temp.getKey()).invoke(one);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, value.toString());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        ;
        return oneList;
    }

}
