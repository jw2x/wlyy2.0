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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

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
    private PatientMedicardCardService patientMedicardCardService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 居民id
     * @param patientId
     * @return
     */
    public String getPatientInfo(String patientId) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(patientId)){
            result.put("result","parameter patientId is null");
            return result.toString();
        }
        List<BasePatientDO> patientDOList = this.findByField("id",patientId);
        if(CollectionUtils.isEmpty(patientDOList)){
            result.put("result","not exist patient for id:"+patientId);
            return result.toString();
        }
        result.put("patient",patientDOList.get(0));
        List<PatientMedicareCardDO> cards = patientMedicardCardService.findPatientCardByCode(patientId);
        result.put("medicareCard",cards);
        return result.toJSONString();
    }

    /**
     * 获取用户基础信息，参数为空查全部
     * @param nameOrIdcard
     * @param page
     * @param size
     * @param sorts
     * @return
     */
    public List<Map<String,Object>> queryPatientBaseInfo(String nameOrIdcard,int page,int size,String sorts)throws Exception{
        List<Map<String,Object>> result = new ArrayList<>();
        if(StringUtils.isEmpty(nameOrIdcard)){
            result = basePatientDao.findBaseInfo(createPage(page,size,sorts));
        }else{
            String pattern = "^\\d+";
            boolean isMatch = Pattern.matches(pattern, nameOrIdcard);
            if(isMatch){
                result = basePatientDao.findByIdcard("%"+nameOrIdcard+"%",createPage(page,size,sorts));
                return result;
            }else{
                result = basePatientDao.findByName("%"+nameOrIdcard+"%",createPage(page,size,sorts));
                return result;
            }
        }
        return result;
    }

    /**
     * 新增居民
     * @param jsonData
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 修改居民
     * @param jsonData
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String updatePatient(String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject patient = jsonObject.getJSONObject("patient");
        JSONArray patientMedicareCards = jsonObject.getJSONArray("medicareCard");
        if(null == patient || CollectionUtils.isEmpty(patientMedicareCards)){
            return ConstantUtils.FAIL;
        }
        BasePatientDO basePatientDO = objectMapper.readValue(patient.toJSONString(),BasePatientDO.class);
        if(StringUtils.isEmpty(basePatientDO.getId())){
            return ConstantUtils.FAIL;
        }
        // 保存修改的居民信息
        this.save(basePatientDO);
        Set<Object> cardIdList = patientMedicardCardService.findIdListByPatientCode(basePatientDO.getId());
        // 有些卡可能是新增或修改的，一条一条修改居民相关的卡的信息
        for (Object obj : patientMedicareCards) {
            PatientMedicareCardDO card = objectMapper.readValue(obj.toString(), PatientMedicareCardDO.class);
            card.setPatientCode(basePatientDO.getId());
            if(cardIdList.contains(card.getId())){
                cardIdList.remove(card.getId());
            }
            patientMedicardCardService.save(card);
        }
        // 有些卡可能是删除的
        if(cardIdList.size() > 0){
            patientMedicardCardService.delete(cardIdList.toArray());
        }
        return ConstantUtils.SUCCESS;
    }
}
