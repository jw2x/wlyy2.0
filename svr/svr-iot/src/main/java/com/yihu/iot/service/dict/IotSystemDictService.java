package com.yihu.iot.service.dict;

import com.alibaba.fastjson.JSONObject;
import com.yihu.iot.dao.dict.IotSystemDictDao;
import com.yihu.iot.service.common.MyJdbcTemplate;
import com.yihu.jw.iot.dict.IotSystemDictDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yeshijie on 2018/1/16.
 */
@Service
public class IotSystemDictService extends BaseJpaService<IotSystemDictDO,IotSystemDictDao> {

    @Autowired
    private IotSystemDictDao iotSystemDictDao;
    @Autowired
    private MyJdbcTemplate myJdbcTemplate;

    /**
     * 查询字典
     * @param dictName
     * @return
     */
    public Map<String,String> findByDictName(String dictName){
        String sql = "SELECT code,value from iot_system_dict WHERE dict_name = ? and del = 1 ORDER BY sort";
        Map<String,String> map = new HashMap<>();
        List<JSONObject> list = myJdbcTemplate.queryJson(sql,new Object[]{dictName});
        list.forEach(json->{
            map.put(json.get("code").toString(),json.getString("value"));
        });
        return map;
    }




}
