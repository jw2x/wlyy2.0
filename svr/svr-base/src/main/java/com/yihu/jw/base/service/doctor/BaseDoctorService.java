package com.yihu.jw.base.service.doctor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.doctor.BaseDoctorDao;
import com.yihu.jw.base.service.org.OrgTree;
import com.yihu.jw.base.service.org.OrgTreeService;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.doctor.BaseDoctorDO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrgTreeService orgTreeService;

    @Autowired
    private ObjectMapper objectMapper;


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
        resultMap = JavaBeanUtils.getInstance().bean2Map(doctors.get(0));

        //医生执业信息
        String[] paramNames = {"hospCode","doctorCode"};
        Object[] paramValue = {orgId,doctorId};
        List<BaseDoctorHospitalDO> baseDoctorHospitalDOS = baseDoctorHospitalService.findByFields(paramNames,paramValue);
        if(CollectionUtils.isEmpty(baseDoctorHospitalDOS)){
            return resultMap;
        }
        Map<String,Object> doctorHospMap = JavaBeanUtils.getInstance().bean2Map(baseDoctorHospitalDOS.get(0));
        resultMap.putAll(doctorHospMap);
        return resultMap;
    }

    /**
     * 获取医生信息（包括医生任职的医院相关信息）
     * @param name
     * @param idcard
     * @param orgCode
     * @param docStatus
     * @return
     */
    public List<Map<String,Object>> getDoctorFullInfo(String name,String idcard,String orgCode,String docStatus){
        List<Map<String,Object>> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("select doc.id,doc.name,doc.idcard,case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,doc.del as status,hos.hosp_name as hosptialName,dept.name as deptName,hos.role_name as roleName,hos.job_title_name jobTitleName from base_doctor doc,base_doctor_hospital hos,dict_hospital_dept dept where doc.id = hos.doctor_code and hos.dept_code = dept.code and hos.del = 1");
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(idcard) && StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus)){
           result = jdbcTemplate.queryForList(sql.toString());
        }else if(StringUtils.isEmpty(name) || StringUtils.isEmpty(idcard)){
            if (!StringUtils.isEmpty(name)) {
                sql.append(" and doc.name = '").append(idcard).append("'");
            }else{
                sql.append(" and doc.idcard = '").append(name).append("'");
            }
            if(StringUtils.isEmpty(orgCode) && !StringUtils.isEmpty(docStatus)){
                sql.append(" and doc.del = '").append(docStatus).append("'");
            }else if(!StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus)){
                sql.append(" and hos.hosp_code = '").append(orgCode).append("'");
            }else{
                sql.append(" and hos.hosp_code = '").append(orgCode).append("'");
                sql.append(" and doc.del = '").append(docStatus).append("'");
            }
            result = jdbcTemplate.queryForList(sql.toString());
        }else if(StringUtils.isEmpty(orgCode)){
            sql.append(" and doc.hosp_code = '").append(orgCode).append("'");
            result = jdbcTemplate.queryForList(sql.toString());
        }else{
            sql.append(" and doc.del = '").append(docStatus).append("'");
            result = jdbcTemplate.queryForList(sql.toString());
        }
        return result;
    }

    /**
     * 新增医生
     * @param jsonData
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String createDoctor(String jsonData){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(jsonData)){
            result.put("msg","jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject doctor = jsonObject.getJSONObject("doctor");
        JSONObject hospital = jsonObject.getJSONObject("hospital");
        if(null == doctor || null == hospital){
            result.put("msg","parameter doctor or hospital of jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        BaseDoctorDO baseDoctorDO = null;
        BaseDoctorHospitalDO baseDoctorHospitalDO = null;
        try {
            baseDoctorDO = objectMapper.readValue(doctor.toJSONString(),BaseDoctorDO.class);
        } catch (IOException e) {
           result.put("msg","convert doctor jsonObject to BaseDoctorDO failed," + e.getCause());
           result.put("response",ConstantUtils.FAIL);
           return result.toJSONString();
        }
        this.save(baseDoctorDO);
        try {
            baseDoctorHospitalDO = objectMapper.readValue(hospital.toJSONString(),BaseDoctorHospitalDO.class);
            baseDoctorHospitalDO.setDoctorCode(baseDoctorDO.getId());
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
        }
        baseDoctorHospitalService.save(baseDoctorHospitalDO);
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",baseDoctorDO);
        return result.toJSONString();
    }


    /**
     * 构建区域树形结构（）
     * @Param level 参考OrgTree的Level枚举类型
     * @return
     */
    public String getOrgTree(OrgTree.Level level){
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(orgTreeService.findListByLevel(level.getLevelValue()));
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        return JSONObject.toJSONString(treeNode, filter);
    }
}
