package com.yihu.wlyy.statistics.etl.convert.wlyy;

import com.yihu.jw.entity.base.statistics.DimensionQuotaDO;
import com.yihu.wlyy.statistics.etl.convert.Convert;
import com.yihu.wlyy.statistics.etl.convert.wlyy.model.ServerType;
import com.yihu.wlyy.statistics.vo.DataModel;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/10/18.
 * 服务类型转换器
 */
public class ServerTypeConvert implements Convert {
    @Override
    public List<DataModel> convert(JdbcTemplate jdbcTemplate, List<DataModel> oneList, String slaveLevel, DimensionQuotaDO temp) {
        List<DataModel> returnList = new ArrayList<>();
        //得到患者的服务类型
        String sql = "select w.sign_code signCode,w.server_type serverType from wlyy_sign_family_server w ";
        List<ServerType> serverTypes = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ServerType.class));
        //List转Map LIST key签约表code value是服务类型
        Map<String, List<String>> serverTypesMap = new HashMap<>();
        serverTypes.forEach(one -> {
            List<String> serverTypeList = serverTypesMap.get(one.getSignCode());
            if (serverTypeList == null) {
                serverTypeList = new ArrayList<String>();
            }
            serverTypeList.add(one.getServerType());
            serverTypesMap.put(one.getSignCode(),serverTypeList);
        });
        oneList.stream().forEach(one -> {
            List<String> serverTypeList = serverTypesMap.get(one.getBusinessId());
            if (serverTypeList != null) {
                for (int i = 0; i < serverTypeList.size(); i++) {
                    try {
                        DataModel dataModelTemp = new DataModel();
                        BeanUtils.copyProperties(one, dataModelTemp);
                        DataModel.class.getMethod("setSlaveKey" + slaveLevel, String.class).invoke(dataModelTemp, serverTypeList.get(i));
                        returnList.add(dataModelTemp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return returnList;
    }
}
