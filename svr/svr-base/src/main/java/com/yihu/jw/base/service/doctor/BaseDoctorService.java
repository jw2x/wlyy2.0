package com.yihu.jw.base.service.doctor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.doctor.BaseDoctorDao;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.doctor.BaseDoctorDO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 医生基础信息服务service
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
public class BaseDoctorService extends BaseJpaService<BaseDoctorDO, BaseDoctorDao> {

    @Autowired
    private BaseDoctorHospitalService baseDoctorHospitalService;

    @Autowired
    private BaseDoctorRoleDictService baseDoctorRoleDictService;


    /**
     * 获取医生信息
     * @param orgId 医生所属机构id
     * @param doctorId 医生id
     * @return
     */
    public Map<String,Object> getDoctorInfo(String orgId, String doctorId) throws Exception{
        Map<String,Object> resultMap = new HashMap<>();

        if(StringUtils.isEmpty(orgId) || StringUtils.isEmpty(doctorId)){
            return resultMap;
        }

        //医生基本信息
        List<BaseDoctorDO> doctors = this.findByField("id",doctorId);
        if(CollectionUtils.isEmpty(doctors)){
            return resultMap;
        }
        resultMap = JavaBeanUtils.bean2Map(doctors.get(0));

        //医生执业信息
        String[] paramNames = {"hospCode","doctorCode"};
        Object[] paramValue = {orgId,doctorId};
        List<BaseDoctorHospitalDO> baseDoctorHospitalDOS = baseDoctorHospitalService.findByFields(paramNames,paramValue);
        if(CollectionUtils.isEmpty(baseDoctorHospitalDOS)){
            return resultMap;
        }
        Map<String,Object> doctorHospMap = JavaBeanUtils.bean2Map(baseDoctorHospitalDOS.get(0));
        resultMap.putAll(doctorHospMap);
        return resultMap;
    }

}
