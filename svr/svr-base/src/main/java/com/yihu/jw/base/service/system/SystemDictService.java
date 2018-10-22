package com.yihu.jw.base.service.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.yihu.jw.base.enums.SystemDictEnum;
import com.yihu.jw.base.service.dict.*;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.entity.base.dict.*;
import com.yihu.jw.entity.base.system.SystemDictDO;
import com.yihu.jw.base.dao.system.SystemDictDao;
import com.yihu.jw.entity.base.system.SystemDictEntryDO;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service - 系统字典
 * Created by LiTaohong on 2017/12/01.
 */
@Service
public class SystemDictService extends BaseJpaService<SystemDictDO, SystemDictDao> {

    @Autowired
    private SystemDictDao systemDictDao;

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
        }else{
            jsonObject = this.queryAll("", createPage(page,size,sorts));
        }
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * 查询某一租户下的医院科室字典信息，如果saadId为空表示当前用户角色为超级管理员，超级管理员可以看到所有数据
     * @param saasId
     * @return
     */
    public JSONObject queryAll(String saasId, Pageable pageable) throws Exception{
        JSONObject jsonObject = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        if(StringUtils.isEmpty(saasId)){
            list = systemDictDao.findCodeAndName(pageable);
        }else{
            list = systemDictDao.findCodeAndNameBySaasId(saasId,pageable);
        }
        jsonObject.put(SystemDictEnum.SystemDict.toString(),list);
        return jsonObject;
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

    /**
     * 查询字典分页信息
     * @param type
     * @param userId
     * @param filters
     * @param sorts
     * @param page
     * @param size
     * @return
     */
    public JSONObject queryDictPageByType( String userId,String type,String filters,String sorts,int page,int size) throws ParseException {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(userId)) {
            result.put("msg","parameter dictType or userId is null");
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        List list = new ArrayList();
        long count = 0;
        if (SystemDictEnum.Icd10Dict == SystemDictEnum.valueOf(type)) {
            list = dictIcd10Service.search(null,filters,sorts,page,size);
            count = dictIcd10Service.getCount(filters);
        } else if (SystemDictEnum.HospitalDeptDict == SystemDictEnum.valueOf(type)) {
            list = dictHospitalDeptService.search(null,filters,sorts,page,size);
            count = dictHospitalDeptService.getCount(filters);

        } else if (SystemDictEnum.JobTitleDict == SystemDictEnum.valueOf(type)) {
            list = dictJobTitleService.search(null,filters,sorts,page,size);
            count = dictJobTitleService.getCount(filters);

        } else if (SystemDictEnum.HealthProblemDict == SystemDictEnum.valueOf(type)) {
            list = dictHealthProblemService.search(null,filters,sorts,page,size);
            count = dictHealthProblemService.getCount(filters);

        } else if (SystemDictEnum.MedicineDict == SystemDictEnum.valueOf(type)) {
            list = dictMedicineService.search(null,filters,sorts,page,size);
            count = dictMedicineService.getCount(filters);

        } else if (SystemDictEnum.DiseaseDict == SystemDictEnum.valueOf(type)) {
            list = dictDiseaseService.search(null,filters,sorts,page,size);
            count = dictDiseaseService.getCount(filters);
        }else{
            list = this.search(null,filters,sorts,page,size);
            count = this.getCount(filters);
        }
        result.put("response",ConstantUtils.SUCCESS);
        result.put("count",count);
        result.put("msg",list);
        return result;
    }
}
