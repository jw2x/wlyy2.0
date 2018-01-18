package com.yihu.iot.service.dict;

import com.yihu.base.mysql.query.BaseJpaService;
import com.yihu.iot.dao.dict.IotSystemDictDao;
import com.yihu.iot.service.common.MyJdbcTemplate;
import com.yihu.jw.iot.dict.IotSystemDictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    public List<JSONObject> findByDictName(String dictName){
//        String sql = "SELECT code,value from iot_system_dict WHERE dict_name = ? and del = 1 ORDER BY sort";
//        return myJdbcTemplate.queryJson(sql,new Object[]{dictName});
//    }




}
