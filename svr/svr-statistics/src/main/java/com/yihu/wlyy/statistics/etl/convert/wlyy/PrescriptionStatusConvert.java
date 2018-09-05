package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.util.Contant;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 长处方疾病
 * Created by zhangdan on 2017/10/26.
 */
public class PrescriptionStatusConvert implements Convert {

    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        for (DataModel one : oneList) {
            try {
//                Object value = DataModel.class.getMethod("get" + temp.getKey()).invoke(one);
                //获取到prescriptionCode，求长处方状态
                String sql = "SELECT status FROM wlyy_prescription WHERE code=?";
                //int status = jdbcTemplate.queryForObject(sql, Integer.class);
                Object[] args = {one.getPrescriptionCode()};
                int status = jdbcTemplate.queryForObject(sql, args, Integer.class);
                String key = getStatusCode(status);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ;
        return oneList;
    }

    public String getStatusCode(Integer status) {
        if (status >= 0 && status < 100) {
            return Contant.prescriptionStatus.status_1;
        } else if (status==100) {
            return Contant.prescriptionStatus.status_2;
        } else if (status==-2) {
            return Contant.prescriptionStatus.status_3;
        } else if (status==-1) {
            return Contant.prescriptionStatus.status_4;
        } else if (status==-3||status==-4||status==-5) {
            return Contant.prescriptionStatus.status_5;
        } else{
            return "0";
        }
    }
}
