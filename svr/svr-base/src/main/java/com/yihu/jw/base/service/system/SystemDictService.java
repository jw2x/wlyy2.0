package com.yihu.jw.base.service.system;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.yihu.jw.base.service.dict.*;
import com.yihu.jw.entity.base.system.SystemDictDO;
import com.yihu.jw.base.dao.system.SystemDictDao;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 获取系统所有相关字典，
     * @param userId
     * @return
     */
    public String getAllDistList(String userId){
        JSONObject jsonObject = new JSONObject();
//        dictIcd10Service.queryAll();
        return null;
    }
}
