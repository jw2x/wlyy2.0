package com.yihu.jw.base.service.doctor;

import com.alibaba.fastjson.JSONArray;
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
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.doctor.BaseDoctorDO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

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
    private BaseDoctorDao baseDoctorDao;

    @Autowired
    private BaseDoctorHospitalService baseDoctorHospitalService;

    @Autowired
    private BaseDoctorRoleService baseDoctorRoleService;

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
     * @param nameOrIdcard
     * @param orgCode
     * @param docStatus
     * @return
     */
    public JSONArray getDoctorFullInfo(String nameOrIdcard,String orgCode,String docStatus,int page,int size,String sort) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        String pattern = "^\\d+";
        if(StringUtils.isEmpty(nameOrIdcard) && StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus)){
            result = baseDoctorDao.queryDoctorFullInfo();
        }else if(!StringUtils.isEmpty(nameOrIdcard)){
            boolean isIdcard = Pattern.matches(pattern, nameOrIdcard);
            if(isIdcard){
                if(StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus) ){
                    result = baseDoctorDao.queryDoctorFullInfoByIdcard("%" + nameOrIdcard + "%");
                }else if(!StringUtils.isEmpty(orgCode)){
                    result = baseDoctorDao.queryDoctorFullInfoByIdcardAndOrg("%" + nameOrIdcard + "%",orgCode);
                }else if(!StringUtils.isEmpty(docStatus)){
                    result = baseDoctorDao.queryDoctorFullInfoByIdcardAndDocDel("%" + nameOrIdcard + "%",docStatus);
                }else{
                    result = baseDoctorDao.queryDoctorFullInfoByIdcardAndOrgAndDocDel("%" + nameOrIdcard + "%",orgCode,docStatus);
                }
            }else{
                if(StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus) ){
                    result = baseDoctorDao.queryDoctorFullInfoByName("%" + nameOrIdcard + "%");
                }else if(!StringUtils.isEmpty(orgCode)){
                    result = baseDoctorDao.queryDoctorFullInfoByNameAndOrg("%" + nameOrIdcard + "%",orgCode);
                }else if(!StringUtils.isEmpty(docStatus)){
                    result = baseDoctorDao.queryDoctorFullInfoByNameAndDocDel("%" + nameOrIdcard + "%",docStatus);
                }else{
                    result = baseDoctorDao.queryDoctorFullInfoByNameAndOrgAndDocDel("%" + nameOrIdcard + "%",orgCode,docStatus);
                }
            }
        }else if(StringUtils.isEmpty(orgCode) && !StringUtils.isEmpty(docStatus) ){
            result = baseDoctorDao.queryDoctorFullInfoByDocDel(docStatus);
        }else if(!StringUtils.isEmpty(orgCode) && StringUtils.isEmpty(docStatus)){
            result = baseDoctorDao.queryDoctorFullInfoByOrg(orgCode);
        }else {
            result = baseDoctorDao.queryDoctorFullInfoByOrgAndDocDel(orgCode,docStatus);
        }
        return JavaBeanUtils.getInstance().mapListJson(result);
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
        JSONArray role = jsonObject.getJSONArray("role");
        JSONArray hospital = jsonObject.getJSONArray("hospital");
        if(null == doctor || CollectionUtils.isEmpty(role) || CollectionUtils.isEmpty(hospital)){
            result.put("msg","parameter doctor or hospital of jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        //组装医生信息
        BaseDoctorDO baseDoctorDO = null;
        try {
            baseDoctorDO = objectMapper.readValue(doctor.toJSONString(),BaseDoctorDO.class);
        } catch (IOException e) {
           result.put("msg","convert doctor jsonObject to BaseDoctorDO failed," + e.getCause());
           result.put("response",ConstantUtils.FAIL);
           return result.toJSONString();
        }
        baseDoctorDO.setPassword(baseDoctorDO.getIdcard().substring(11,17));
        this.save(baseDoctorDO);

        //组装医生角色关联关系
        BaseDoctorRoleDO baseDoctorRoleDO = null;
        List<BaseDoctorRoleDO> baseDoctorRoleDOList = new ArrayList<>();
        try {
            for(Object object : role){
                baseDoctorRoleDO = objectMapper.readValue(object.toString(),BaseDoctorRoleDO.class);
                baseDoctorRoleDO.setDoctorCode(baseDoctorDO.getId());
                baseDoctorRoleDOList.add(baseDoctorRoleDO);
            }
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        baseDoctorRoleService.batchInsert(baseDoctorRoleDOList);

        // 组装医生任职机构及职业信息
        BaseDoctorHospitalDO baseDoctorHospitalDO = null;
        List<BaseDoctorHospitalDO> hospitalDOList = new ArrayList<>();
        try {
            for(Object object : hospital){
                baseDoctorHospitalDO = objectMapper.readValue(object.toString(),BaseDoctorHospitalDO.class);
                baseDoctorHospitalDO.setDoctorCode(baseDoctorDO.getId());
                hospitalDOList.add(baseDoctorHospitalDO);
            }
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        baseDoctorHospitalService.batchInsert(hospitalDOList);
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",baseDoctorDO);
        return result.toJSONString();
    }

    /**
     * 修改医生
     * @param jsonData
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String updateDoctor(String jsonData){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(jsonData)){
            result.put("msg","jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject doctor = jsonObject.getJSONObject("doctor");
        JSONArray role = jsonObject.getJSONArray("role");
        JSONArray hospital = jsonObject.getJSONArray("hospital");
        if(null == doctor || CollectionUtils.isEmpty(role) || CollectionUtils.isEmpty(hospital)){
            result.put("msg","parameter doctor or hospital of jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        //判断医生id是否存在
        if(StringUtils.isEmpty(doctor.getString("id"))){
            result.put("msg","parameter id for doctor is null when update doctor");
            result.put("response", ConstantUtils.FAIL);
            return result.toJSONString();
        }
        // 修改医生信息
        BaseDoctorDO baseDoctorDO = null;
        try {
            baseDoctorDO = objectMapper.readValue(doctor.toJSONString(),BaseDoctorDO.class);
        } catch (IOException e) {
            result.put("msg","convert doctor jsonObject to BaseDoctorDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        this.save(baseDoctorDO);

        //修改医生角色关联关系
        BaseDoctorRoleDO baseDoctorRoleDO = null;
        Set<Object> roleIdList = baseDoctorRoleService.findRoleIdList(baseDoctorDO.getId());
        try {
            for(Object object : hospital){
                baseDoctorRoleDO = objectMapper.readValue(object.toString(),BaseDoctorRoleDO.class);
                if(roleIdList.contains(baseDoctorRoleDO.getId())){
                    roleIdList.remove(baseDoctorRoleDO.getId());
                }
                baseDoctorRoleDO.setDoctorCode(baseDoctorDO.getId());
                baseDoctorRoleService.save(baseDoctorRoleDO);
            }
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
        }
        baseDoctorHospitalService.delete(roleIdList.toArray());

        // 修改医生任职机构及职业信息
        BaseDoctorHospitalDO baseDoctorHospitalDO = null;
        Set<Object> hospitalIdList = baseDoctorHospitalService.findDocHospIdList(baseDoctorDO.getId());
        try {
            for(Object object : hospital){
                baseDoctorHospitalDO = objectMapper.readValue(object.toString(),BaseDoctorHospitalDO.class);
                if(hospitalIdList.contains(baseDoctorHospitalDO.getId())){
                    hospitalIdList.remove(baseDoctorHospitalDO.getId());
                }
                baseDoctorHospitalDO.setDoctorCode(baseDoctorDO.getId());
                baseDoctorHospitalService.save(baseDoctorHospitalDO);
            }
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
        }
        baseDoctorHospitalService.delete(hospitalIdList.toArray());
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",baseDoctorDO);
        return result.toJSONString();
    }

    /**
     * 生效或失效单个医生
     * @param doctorCode
     * @param del
     * @return
     */
    public String enableOrDisableDoctor(String doctorCode,String del){
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(doctorCode) || StringUtils.isEmpty(del)){
            result.put("msg","parameter doctorCode or del is null");
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        BaseDoctorDO baseDoctorDO = baseDoctorDao.findOne(doctorCode);
        if( null == baseDoctorDO ){
            result.put("msg","doctor not exist for id:" + doctorCode);
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        baseDoctorDO.setDel(del);
        this.save(baseDoctorDO);
        result.put("response",ConstantUtils.SUCCESS);
        return result.toJSONString();
    }

    /**
     * 获取医生已选中的机构/职务树形结构
     * @param doctorCode
     * @return
     */
    public String getDoctorDutyTree(String doctorCode){
        if(StringUtils.isEmpty(doctorCode)){
            return "";
        }
        List<BaseDoctorHospitalDO> list = baseDoctorHospitalService.getOrgAndDutyListByDoctorCode(doctorCode);
        List<OrgTree> orgTreeList = new ArrayList<>();
        for(BaseDoctorHospitalDO one : list){
            OrgTree orgTreeParent = new OrgTree();
            orgTreeParent.setParentCode("");
            orgTreeParent.setCode(one.getHospCode());
            orgTreeParent.setName(one.getHospName());
            orgTreeList.add(orgTreeParent);

            OrgTree orgTreeChild = new OrgTree();
            orgTreeChild.setParentCode(one.getHospCode());
            orgTreeChild.setCode(one.getDoctorDutyCode());
            orgTreeChild.setName(one.getDoctorDutyName());
            orgTreeList.add(orgTreeChild);
        }
        return getOrgTree(orgTreeList);
    }

    /**
     * 获取医生已选中的机构/科室树形结构
     * @param doctorCode
     * @return
     */
    public String getDoctorDeptTree(String doctorCode){
        if(StringUtils.isEmpty(doctorCode)){
            return "";
        }
        String sql = "select" +
                "  hos.doctor_code ," +
                "  hos.hosp_code as parentCode," +
                "  org.name as parentName," +
                "  hos.dept_code as childCode," +
                "  dept.name as childName" +
                " from" +
                "  base_doctor_hospital hos," +
                "  base_org org," +
                "  dict_hospital_dept dept" +
                " where" +
                "  hos.hosp_code = org.code" +
                "  and" +
                "  hos.hosp_code = dept.org_code" +
                "  and" +
                "  hos.dept_code = dept.code" +
                "  and doctor_code = '{doctorCode}'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql.replace("{doctorCode}",doctorCode));
        List<OrgTree> orgTreeList = new ArrayList<>();
        for(Map<String,Object> one : list){
            OrgTree orgTreeParent = new OrgTree();
            orgTreeParent.setParentCode("");
            orgTreeParent.setCode(String.valueOf(one.get("parentCode")));
            orgTreeParent.setName(String.valueOf(one.get("parentName")));
            orgTreeList.add(orgTreeParent);

            OrgTree orgTreeChild = new OrgTree();
            orgTreeChild.setParentCode(String.valueOf(one.get("parentCode")));
            orgTreeChild.setCode(String.valueOf(one.get("childCode")));
            orgTreeChild.setName(String.valueOf(one.get("childName")));
            orgTreeList.add(orgTreeChild);
        }
        return getOrgTree(orgTreeList);
    }

    /**
     * 构建树形结构
     * @return
     */
    public String getOrgTree(List<OrgTree> orgTreeList ){
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(orgTreeList);
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        filter.getExcludes().add("parentNodeId");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");
        return JSONObject.toJSONString(treeNode, filter);
    }
}
