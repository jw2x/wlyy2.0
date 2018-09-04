package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 长处方疾病
 * Created by zhangdan on 2017/10/26.
 */
public class DispensaryTypeConvert implements Convert {

    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp ) {
        for(DataModel one:oneList){
            try {
//                Object value = DataModel.class.getMethod("get" + temp.getKey()).invoke(one);
                //获取到prescriptionCode，求长处方配送方式
                String sql ="SELECT dispensary_type FROM wlyy_prescription WHERE code=?";
                Object[] args = {one.getPrescriptionCode()};
                String type=jdbcTemplate.queryForObject(sql,args,String.class);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return oneList;
    }
}
