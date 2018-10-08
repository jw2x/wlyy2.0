package com.yihu.jw.base.service.dict;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.base.dao.dict.DictIcd10Dao;
import com.yihu.jw.base.enums.SystemDictEnum;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.dict.DictIcd10DO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * ICD10字典服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * litaohong    1.0  2018年08月31日 Created
 *
 * </pre>
 * @since 1.
 */
@Service
public class DictIcd10Service extends BaseJpaService<DictIcd10DO, DictIcd10Dao> {

    @Autowired
    private DictIcd10Dao dictIcd10Dao;

    /**
     * 查询某一租户下的Icd10字典信息，如果saadId为空表示当前用户角色为超级管理员，超级管理员可以看到所有数据
     * @param saasId
     * @return
     */
    public String queryAll(String saasId){
        JSONObject jsonObject = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
           list = dictIcd10Dao.findCodeAndName();
        }else{
           list = dictIcd10Dao.findCodeAndNameBySaasId(saasId);
        }
        jsonObject.put(SystemDictEnum.Icd10Dict.toString(),list);
        return jsonObject.toJSONString();
    }

}
