package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */

public class SimpleConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel>  oneList, String slaveLevel, DimensionQuotaDO temp ) {
        oneList.stream().forEach(one -> {
            try {
                Object value = DataModel.class.getMethod("get" + temp.getKey()).invoke(one);
                if(StringUtils.isEmpty(value)){
                    value="0";
                }
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, value.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return oneList;
    }

}
