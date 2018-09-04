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
public class UseDeviceTypeConvert implements Convert {
    /**
     * @param oneList    数据
     * @param slaveLevel 从1开始
     * @return
     */
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate,List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        String sql = temp.getDictSql();
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        Map<String,Object> map = null;
        for(DataModel one:oneList) {
            try {
                map = getTeam(one,jdbcTemplate);
                if(map!=null){
                    one.setTeam(map.get("grant_admin_team")+"");
                    one.setServerType(map.get("device_name")+"");
                }else{
                    continue;
                }
                String key = getKey(one,result);
                DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(one, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return oneList;
    }

    public String getKey(DataModel dataModel,List<Map<String,Object>> dict){

        for (Map<String,Object> one : dict){
            String result = one.get("name")+"";
            if(dataModel.getServerType().equals(result)||dataModel.getServerType().indexOf(result)>0){
                return one.get("code")+"";
            }
        }
        return "";
    }

    public Map<String,Object> getTeam(DataModel dataModel,JdbcTemplate jdbcTemplate){
        String deviceCode =  dataModel.getHealthProblem();
        String sql = " select * from device.wlyy_devices d where d.device_code ='"+deviceCode+"' and is_grant=1";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
        if(result.size()>0){
            return result.get(0);
        }
        return null;
    }


}
