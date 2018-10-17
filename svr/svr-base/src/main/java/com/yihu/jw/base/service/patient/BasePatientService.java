package com.yihu.jw.base.service.patient;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.patient.BasePatientDao;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 
 * 居民信息服务service
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
public class BasePatientService extends BaseJpaService<BasePatientDO, BasePatientDao> {

    @Autowired
    private BasePatientDao basePatientDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PatientMedicardCardService patientMedicardCardService;

    /**
     * 居民id
     * @param patientId
     * @return
     */
    public Map<String,Object> getPatientInfo(String patientId) throws Exception{
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isEmpty(patientId)){
            return resultMap;
        }
        List<BasePatientDO> patientDOList = this.findByField("id",patientId);
        if(CollectionUtils.isEmpty(patientDOList)){
            return resultMap;
        }
        resultMap = JavaBeanUtils.bean2Map(patientDOList.get(0));
        return resultMap;
    }

    /**
     * 获取用户基础信息，参数为空查全部
     * @param idcard
     * @param name
     * @param page
     * @param size
     * @param sorts
     * @return
     */
    public List<Map<String,Object>> queryPatientBaseInfo(String idcard,String name,int page,int size,String sorts)throws Exception{
        List<Map<String,Object>> result = new ArrayList<>();
        if(!StringUtils.isEmpty(idcard)){
            result = basePatientDao.findByIdcard("%"+idcard+"%",createPage(page,size,sorts));
            return result;
        }
        if(!StringUtils.isEmpty(name)){
            result = basePatientDao.findByName("%"+name+"%",createPage(page,size,sorts));
            return result;
        }
        result = basePatientDao.findBaseInfo(createPage(page,size,sorts));
        return result;
    }

    /**
     * 新增居民
     * @param jsonData
     * @return
     */
    public String createPatient(String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject patient = jsonObject.getJSONObject("patient");
        JSONArray patientMedicareCards = jsonObject.getJSONArray("medicareCard");
        if(null == patient || CollectionUtils.isEmpty(patientMedicareCards)){
            return ConstantUtils.FAIL;
        }
        BasePatientDO basePatientDO = objectMapper.readValue(patient.toJSONString(),BasePatientDO.class);
        List<PatientMedicareCardDO> list = new ArrayList<>();
        patientMedicareCards.forEach((card)->{
            try {
                list.add(objectMapper.readValue(card.toString(),PatientMedicareCardDO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.save(basePatientDO);
        patientMedicardCardService.batchInsert(list);
        return ConstantUtils.SUCCESS;
    }
}
