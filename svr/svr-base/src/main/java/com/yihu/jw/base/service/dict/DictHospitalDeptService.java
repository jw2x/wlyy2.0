package com.yihu.jw.base.service.dict;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.yihu.jw.base.dao.dict.DictHospitalDeptDao;
import com.yihu.jw.base.enums.SystemDictEnum;
import com.yihu.jw.base.service.org.BaseOrgService;
import com.yihu.jw.entity.base.dict.DictIcd10DO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 医院科室字典服务service
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
public class DictHospitalDeptService extends BaseJpaService<DictHospitalDeptDO, DictHospitalDeptDao> {

    @Autowired
    private DictHospitalDeptDao dictHospitalDeptDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BaseOrgService baseOrgService;
    /**
     * 查询某一租户下的医院科室字典信息，如果saadId为空表示当前用户角色为超级管理员，超级管理员可以看到所有数据
     * @param saasId
     * @return
     */
    public JSONObject queryAll(String saasId, Pageable pageable) throws Exception{
        JSONObject jsonObject = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
            list = dictHospitalDeptDao.findCodeAndName(pageable);
        }else{
            List orgCodeList = baseOrgService.findOrgCodeBySaasId(saasId);
            list = dictHospitalDeptDao.findByOrgCodeIn(objectMapper.writeValueAsString(orgCodeList),pageable);
        }
        jsonObject.put(SystemDictEnum.HospitalDeptDict.toString(),list);
        return jsonObject;
    }

    /**
     * 根据机构标识获取科室
     * @param orgCode
     * @return
     */
    public List<DictHospitalDeptDO> findDeptByOrgCode(String orgCode){
        List<DictHospitalDeptDO>  list = new ArrayList<>();
       if(StringUtils.isEmpty(orgCode)){
           return list;
       }
       return dictHospitalDeptDao.findByOrgCode(orgCode);
    }
}
