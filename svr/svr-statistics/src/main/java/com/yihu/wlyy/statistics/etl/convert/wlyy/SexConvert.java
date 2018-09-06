package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.util.IdCardUtil;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by chenweida on 2017/6/5.
 */

public class SexConvert implements Convert {
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
                String sex= IdCardUtil.getSexForIdcard(String.valueOf(value));
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, sex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return oneList;
    }

}
