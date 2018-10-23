package com.yihu.jw.base.service.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.system.SystemDictDao;
import com.yihu.jw.base.enums.SystemDictEnum;
import com.yihu.jw.base.service.dict.*;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.system.SystemDictDO;
import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service - 系统字典
 * Created by LiTaohong on 2017/12/01.
 */
@Service
public class SystemDictService extends BaseJpaService<SystemDictDO, SystemDictDao> {

    @Autowired
    private DictIcd10Service dictIcd10Service;

    @Autowired
    private DictHospitalDeptService dictHospitalDeptService;

    @Autowired
    private DictJobTitleService dictJobTitleService;

    @Autowired
    private DictHealthProblemService dictHealthProblemService;

    @Autowired
    private DictDiseaseService dictDiseaseService;

    @Autowired
    private DictMedicineService dictMedicineService;

    @Autowired
    private SystemDictEntryService systemDictEntryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public long getCount(String filters) throws ParseException {
        return super.getCount(filters);
    }

    /**
     * 根据字典类型获取系统所有相关字典，
     * @param saasId
     * @return
     */
    public JSONArray getDistListBySaasId(String type, String saasId,String name, String sorts, int page, int size) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (SystemDictEnum.Icd10Dict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictIcd10Service.queryAll(saasId, createPage(page,size,sorts));
        } else if (SystemDictEnum.HospitalDeptDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictHospitalDeptService.queryAll(saasId, createPage(page,size,sorts));

        } else if (SystemDictEnum.JobTitleDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictJobTitleService.queryAll(saasId, createPage(page,size,sorts));

        } else if (SystemDictEnum.HealthProblemDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictHealthProblemService.queryAll(saasId, createPage(page,size,sorts));

        } else if (SystemDictEnum.MedicineDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictMedicineService.queryAll(saasId, createPage(page,size,sorts));

        } else if (SystemDictEnum.DiseaseDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictDiseaseService.queryAll(saasId, createPage(page,size,sorts));
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * 获取系统所有相关字典，
     * @param userId
     * @return
     */
    public JSONArray getAllDistList(String userId){
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }
    /**
     * 根据字典类型获取系统所有相关字典，
     * @param userId
     * @return
     */
    public JSONArray getDistListByType(String type, String userId, String sorts, int page, int size) throws Exception {
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(userId)) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        if (SystemDictEnum.Icd10Dict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictIcd10Service.queryAll(userId, createPage(page,size,sorts));
        } else if (SystemDictEnum.HospitalDeptDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictHospitalDeptService.queryAll("", createPage(page,size,sorts));

        } else if (SystemDictEnum.JobTitleDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictJobTitleService.queryAll("", createPage(page,size,sorts));

        } else if (SystemDictEnum.HealthProblemDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictHealthProblemService.queryAll("", createPage(page,size,sorts));

        } else if (SystemDictEnum.MedicineDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictMedicineService.queryAll("", createPage(page,size,sorts));

        } else if (SystemDictEnum.DiseaseDict == SystemDictEnum.valueOf(type)) {
            jsonObject = dictDiseaseService.queryAll("", createPage(page,size,sorts));
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * 新增字典，包括字典项值
     * "obj": {
     "      dict:
                {
                code": "SYSTEM_SETTING",
     "          name": "系统设置",
     "          pyCode": "XTSZ",
     "          saasId": "string",
     "          type": "basic"
            }
            valueArr:[
                {
                dictCode:"SYSTEM_SETTING",
                code:"SYSTEM_SETTING_0",
                pyCode:"",
                value:"",
                sort:"",
                remark:"",
                 },{
                ....
                  }
            ]
     }
     *
     */
    @Transactional(rollbackFor = Exception.class)
    public String createSystemDict(String jsonData) throws Exception{
        if(StringUtils.isEmpty(jsonData)){
            return "none params(jsonData)";
        }
        JSONObject jsonParam = JSONObject.parseObject(jsonData);
        if(null == jsonParam.get("dict")){
            return "no dict element in " + jsonData;
        }
        JSONObject dictJson = (JSONObject)jsonParam.get("dict");
        SystemDictDO systemDictDO = objectMapper.readValue(dictJson.toString(),SystemDictDO.class);

        if(StringUtils.isEmpty(systemDictDO.getCode()) || StringUtils.isEmpty(systemDictDO.getName())){
            return "code or name of dict is required";
        }

        List<SystemDictEntryDO> systemDictEntryDOList = new ArrayList<>();
        JSONArray dictValueArr = jsonParam.getJSONArray("valueArr");
        dictValueArr.forEach((oneObj)->systemDictEntryDOList.add((SystemDictEntryDO)oneObj));

        save(systemDictDO);

        systemDictEntryService.batchInsert(systemDictEntryDOList);

        return ConstantUtils.SUCCESS;
    }
}
