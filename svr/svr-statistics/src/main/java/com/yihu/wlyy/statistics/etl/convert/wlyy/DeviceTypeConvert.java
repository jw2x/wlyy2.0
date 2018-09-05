package com.yihu.wlyy.statistics.etl.convert.wlyy;


import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/5.
 */
public class DeviceTypeConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate,List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        String sql = temp.getDictSql();
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        for(DataModel one:oneList) {
            try {
                String key = getKey(one,result);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return oneList;
    }

    //1、血糖，2、血压
    public String getKey(DataModel dataModel,List<Map<String,Object>> dict){

        if("1".equals(dataModel.getServerType())){
             for (Map<String,Object> one : dict){
                String result = one.get("name")+"";
                if(result.equals("血糖")){
                    return one.get("code")+"";
                }
            }
        }else if("2".equals(dataModel.getServerType())){
            for (Map<String,Object> one : dict){
                String result = one.get("name")+"";
                if(result.equals("血压")){
                    return one.get("code")+"";
                }
            }
        }
        return "";
    }


}
