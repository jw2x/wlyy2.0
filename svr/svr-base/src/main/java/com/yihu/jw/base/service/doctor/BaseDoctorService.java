package com.yihu.jw.base.service.doctor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.doctor.BaseDoctorDao;
import com.yihu.jw.base.service.dict.DictDoctorDutyService;
import com.yihu.jw.base.service.dict.DictHospitalDeptService;
import com.yihu.jw.base.service.org.OrgTree;
import com.yihu.jw.base.service.org.OrgTreeService;
import com.yihu.jw.base.service.org.tree.SimpleTree;
import com.yihu.jw.base.service.org.tree.SimpleTreeNode;
import com.yihu.jw.base.service.org.tree.TreeNode;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.dict.DictDoctorDutyDO;
import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import com.yihu.jw.entity.base.org.BaseOrgDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.doctor.BaseDoctorDO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

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
    private ObjectMapper objectMapper;

    @Autowired
    private OrgTreeService orgTreeService;

    @Autowired
    private DictDoctorDutyService dictDoctorDutyService;

    @Autowired
    private DictHospitalDeptService dictHospitalDeptService;


    /**
     * 获取医生信息
     * @param doctorId 医生id
     * @return
     */
    public JSONObject getOneDoctorInfo(String doctorId) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(doctorId)){
            result.put("msg","parameter doctorId is null ");
            result.put("response",ConstantUtils.FAIL);
            return result;
        }

        //医生基本信息
        BaseDoctorDO doctors = baseDoctorDao.findOne(doctorId);
        if(null == doctors){
            result.put("msg","doctor not exist for id:" + doctorId);
            result.put("response",ConstantUtils.FAIL);
            return result;
        }

        //医生归属业务模块角色信息
        String[] paramNames = {"doctorCode","del"};
        Object[] paramValue = {doctorId,"1"};
        List<BaseDoctorRoleDO> roleList = baseDoctorRoleService.findByFields(paramNames,paramValue);
        if(CollectionUtils.isEmpty(roleList)){
            result.put("msg","doctor role not exist for id:" + doctorId);
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doctor",doctors);
        jsonObject.put("role",roleList);
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",jsonObject);
        return result;
    }

    /**
     * 获取医生信息（包括医生任职的医院相关信息）
     * @param nameOrIdcard
     * @param orgCode
     * @param docStatus
     * @return
     */
    public JSONObject queryDoctorListFullInfo(String nameOrIdcard, String orgCode, String docStatus, int page, int size) throws Exception {
        JSONObject result = new JSONObject();
        String orgCodeVale = null == orgCode ? "" : orgCode;
        String del = null == docStatus ? "" : docStatus;
        String nameOrIdcardValue = null == nameOrIdcard ? "" : "%" + nameOrIdcard + "%";
        int start = 0 == page ? page++ : (page - 1) * size;
        int end = 0 == size ? 15 : page * size;
        String sql = "select" +
                "  tb.id as id," +
                "  tb.name as name," +
                "  tb.idcard as idcard,  " +
                "  tb.sex as sex,  " +
                "  tb.mobile as mobile,  " +
                "  GROUP_CONCAT(tb.org SEPARATOR ',') as orgInfo,  " +
                "  tb.job_title_name as jobTitleName,  " +
                "  tb.status as status " +
                "from  " +
                "  (  " +
                "    select  " +
                "     doc.id,  " +
                "     doc.name,  " +
                "     doc.idcard,  " +
                "     case doc.sex when 1 then '男' when 2 then '女' else '未知' end as sex,  " +
                "     doc.mobile,  " +
                "     concat(hos.org_name,'/',dept.name,'/',hos.doctor_duty_name) as org,  " +
                "     doc.job_title_name,  " +
                "     case doc.del when 0 then '已失效' when 1 then '生效中' end as status,  " +
                "      doc.create_time  " +
                "   from  " +
                "     base_doctor doc,  " +
                "     base_doctor_hospital hos,  " +
                "     dict_hospital_dept dept  " +
                "  where  " +
                "    doc.id = hos.doctor_code  " +
                "    and  " +
                "    hos.org_code = dept.org_code " +
                "    and " +
                "    hos.dept_code = dept.code  " +
                "    and  " +
                "    ((doc.idcard like '{idcard}' or ''= '{idcard}' ) or (doc.name like '{name}'  or ''= '{name}' )  and (hos.org_code = '{orgCode}' or ''= '{orgCode}') and (doc.del = '{docStatus}' or ''= '{docStatus}'))  " +
                "  ) tb  " +
                "GROUP BY tb.id order by tb.create_time desc limit {start},{end} ";
        String finalSql = sql
                .replace("{idcard}",nameOrIdcardValue)
                .replace("{name}",nameOrIdcardValue)
                .replace("{orgCode}",orgCodeVale)
                .replace("{docStatus}",del)
                .replace("{start}",String.valueOf(start))
                .replace("{end}",String.valueOf(end));

        String countSql = " select " +
                "     count(DISTINCT (doc.id)) as count " +
                "   from " +
                "     base_doctor doc, " +
                "     base_doctor_hospital hos, " +
                "     dict_hospital_dept dept " +
                "  where " +
                "    doc.id = hos.doctor_code " +
                "    and " +
                "    hos.org_code = dept.org_code " +
                "    and " +
                "    hos.dept_code = dept.code " +
                "    and " +
                "    ((doc.idcard like '{idcard}' or ''= '{idcard}' ) or (doc.name like '{name}' or ''= '{name}') and (hos.org_code = '{orgCode}' or ''= '{orgCode}') and (doc.del = '{docStatus}' or ''= '{docStatus}')) " ;
        String finalCountSql = countSql
                .replace("{idcard}",nameOrIdcardValue)
                .replace("{name}",nameOrIdcardValue)
                .replace("{orgCode}",orgCodeVale)
                .replace("{docStatus}",del);
        List<Map<String,Object>> list = jdbcTemplate.queryForList(finalSql);
        int count = jdbcTemplate.queryForObject(finalCountSql,Integer.class);
        result.put("count", count);
        result.put("msg", JavaBeanUtils.getInstance().mapListJson(list));
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
        JSONArray role = jsonObject.getJSONArray("role");
        JSONArray hospital = jsonObject.getJSONArray("hospital");
        if(null == doctor || CollectionUtils.isEmpty(hospital)){
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

        //组装医生角色关联关系,医生默认可以没有角色
        if(!CollectionUtils.isEmpty(role)){
        BaseDoctorRoleDO baseDoctorRoleDO = null;
        List<BaseDoctorRoleDO> baseDoctorRoleDOList = new ArrayList<>();
        try {
            for(Object object : role){
                baseDoctorRoleDO = objectMapper.readValue(object.toString(),BaseDoctorRoleDO.class);
                baseDoctorRoleDO.setDoctorCode(baseDoctorDO.getId());
                baseDoctorRoleDO.setDel(baseDoctorDO.getDel());
                baseDoctorRoleDOList.add(baseDoctorRoleDO);
            }
        } catch (IOException e) {
            result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        baseDoctorRoleService.batchInsert(baseDoctorRoleDOList);
        }

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
        if(null == doctor){
            result.put("msg","parameter role of jsonData is null");
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
            BaseDoctorDO oldDoctor = baseDoctorDao.findOne(baseDoctorDO.getId());
            baseDoctorDO.setPassword(oldDoctor.getPassword());
        } catch (IOException e) {
            result.put("msg","convert doctor jsonObject to BaseDoctorDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result.toJSONString();
        }
        this.save(baseDoctorDO);

        //修改医生角色关联关系，医生默认可以没有角色
        if(!CollectionUtils.isEmpty(role)){
            BaseDoctorRoleDO baseDoctorRoleDO = null;
            Set<Object> roleIdList = baseDoctorRoleService.findRoleIdList(baseDoctorDO.getId());
            try {
                for(Object object : role){
                    baseDoctorRoleDO = objectMapper.readValue(object.toString(),BaseDoctorRoleDO.class);
                    if(roleIdList.contains(baseDoctorRoleDO.getId())){
                        roleIdList.remove(baseDoctorRoleDO.getId());
                    }
                    baseDoctorRoleDO.setDoctorCode(baseDoctorDO.getId());
                    baseDoctorRoleDO.setDel(baseDoctorDO.getDel());
                    baseDoctorRoleService.save(baseDoctorRoleDO);
                }
            } catch (IOException e) {
                result.put("msg","convert hospital jsonObject to baseDoctorHospitalDO failed," + e.getCause());
                result.put("response",ConstantUtils.FAIL);
            }
            baseDoctorRoleService.delete(roleIdList.toArray());
        }


        // 修改医生任职机构及职业信息，前端不修改就不做任何操作
         if(!CollectionUtils.isEmpty(hospital)){
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
         }

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
     * 获取医生已选中的区县/机构列表，当有机构是被选中时，要标有checked=true的状态前端加载生成树形机构，
     * @param doctorCode
     * @return
     */
    public JSONObject getOrgListByTownAndDoctorCode(String doctorCode,String townCode) throws Exception {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(townCode)){
            result.put("msg","townCode is not allowed to be null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        List<Map<String, Object>> list = new ArrayList<>();
        list  = orgTreeService.findOrgListByParentCode(townCode);
        if(StringUtils.isEmpty(doctorCode)){
            result.put("response", ConstantUtils.SUCCESS);
            result.put("msg",list);
            return result;
        }
        Map<String,Map<String, Object>> orgCodeMap = new HashMap<>();
        list.forEach( one-> orgCodeMap.put(String.valueOf(one.get("code")),one));
        List<BaseDoctorHospitalDO> selectedList = baseDoctorHospitalService.getOrgAndDutyListByDoctorCode(doctorCode);
        for(BaseDoctorHospitalDO one : selectedList){
            if(orgCodeMap.containsKey(one.getOrgCode())){
                orgCodeMap.get(one.getOrgCode()).put("checked",true);
            }
        }
        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg",JavaBeanUtils.getInstance().mapListJson(orgCodeMap.values()));
        return result;
    }


    /**
     * 获取医生已选中的机构/职务树形结构,当有机构是被选中时，要标有checked=true的状态
     * @param doctorCode
     * @return
     */
    public JSONObject getDoctorDutyTree(String doctorCode) throws Exception {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(doctorCode)){
            result.put("msg","doctorCode is not allowed to be null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        List<DictDoctorDutyDO> dutyDOList = dictDoctorDutyService.search(null);

        List<BaseDoctorHospitalDO> selectedDutylist = baseDoctorHospitalService.getOrgAndDutyListByDoctorCode(doctorCode);
        Set<String> selectedDutySet = new HashSet<>();
        selectedDutylist.forEach( one -> selectedDutySet.add(one.getOrgCode()+one.getDoctorDutyCode()) );

        List<OrgTree> orgTreeList = new ArrayList<>();
        for(BaseDoctorHospitalDO one : selectedDutylist){
            OrgTree orgTreeParent = new OrgTree();
            orgTreeParent.setParentCode("");
            orgTreeParent.setCode(one.getOrgCode());
            orgTreeParent.setName(one.getOrgName());
            orgTreeList.add(orgTreeParent);

            for(DictDoctorDutyDO dictDoctorDutyDO :dutyDOList){
                OrgTree orgTreeChild = new OrgTree();
                orgTreeChild.setParentCode(one.getOrgCode());
                orgTreeChild.setCode(dictDoctorDutyDO.getCode());
                orgTreeChild.setName(dictDoctorDutyDO.getName());
                if(selectedDutySet.contains(one.getOrgCode()+dictDoctorDutyDO.getCode())){
                    orgTreeParent.setChecked(true);
                    orgTreeChild.setChecked(true);
                }
                orgTreeList.add(orgTreeChild);
            }
        }
        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg", objectMapper.readValue(makeTree(orgTreeList),JSONArray.class));
        return result;
    }

    /**
     * 获取医生已选中的机构/科室树形结构
     * @param doctorCode
     * @return
     */
    public JSONObject getDoctorDeptTree(String doctorCode) throws Exception {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(doctorCode)){
            result.put("msg","doctorCode is not allowed to be null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        String deptSql = "select" +
                "  hos.doctor_code ," +
                "  hos.org_code as orgCode," +
                "  org.name as orgName," +
                "  dept.code as deptCode," +
                "  dept.name as deptName" +
                " from" +
                "  base_doctor_hospital hos," +
                "  base_org org," +
                "  dict_hospital_dept dept" +
                " where" +
                "  hos.org_code = org.code" +
                "  and" +
                "  hos.org_code = dept.org_code";

        String doctorSelectedDeptSql = deptSql + " and  hos.dept_code = dept.code and hos.doctor_code = '{doctorCode}'";
        List<Map<String,Object>> selectedDeptlist = jdbcTemplate.queryForList(doctorSelectedDeptSql.replace("{doctorCode}",doctorCode));
        List<Map<String,Object>> deptList = jdbcTemplate.queryForList(deptSql);

        Set<String> selectedDeptCodeSet = new HashSet<>();
        selectedDeptlist.forEach( one -> selectedDeptCodeSet.add(String.valueOf(one.get("orgCode")) + String.valueOf(one.get("deptCode"))));

        List<OrgTree> orgTreeList = new ArrayList<>();
        for(Map<String,Object> one : selectedDeptlist){
            OrgTree orgTreeParent = new OrgTree();
            orgTreeParent.setParentCode("");
            orgTreeParent.setCode(String.valueOf(one.get("orgCode")));
            orgTreeParent.setName(String.valueOf(one.get("orgName")));
            orgTreeList.add(orgTreeParent);

            for(Map<String,Object> dept :deptList){
                OrgTree orgTreeChild = new OrgTree();
                orgTreeChild.setParentCode(String.valueOf(dept.get("orgCode")));
                orgTreeChild.setCode(String.valueOf(dept.get("deptCode")));
                orgTreeChild.setName(String.valueOf(dept.get("deptName")));
                orgTreeList.add(orgTreeChild);
                if(selectedDeptCodeSet.contains(String.valueOf(dept.get("orgCode")) + dept.get("deptCode"))){
                    orgTreeParent.setChecked(true);
                    orgTreeChild.setChecked(true);
                }
            }
        }
        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg",objectMapper.readValue(makeTree(orgTreeList),JSONArray.class));
        return result;
    }

    /**
     * 构建树形结构
     * @return
     */
    public String makeTree(List<OrgTree> orgTreeList ){
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.addAll(orgTreeList);
        SimpleTree tree = new SimpleTree(treeNodes);
        List<SimpleTreeNode> treeNode = tree.getRoot();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("parent");
        filter.getExcludes().add("allChildren");
        filter.getExcludes().add("orderNum");
        filter.getExcludes().add("level");
        return JSONObject.toJSONString(treeNode, filter);
    }

    /**
     * 获取某一科室下的医生列表
     * @param deptCode
     * @return
     */
    public JSONObject getDoctorListByDept(String deptCode) throws Exception {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(deptCode)){
            result.put("msg","parameter deptCode is null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        String sql = "select " +
                "  DISTINCT hos.doctor_code as doctorCode, " +
                "  doc.name as name, " +
                "  hos.dept_code as deptCode " +
                " from " +
                "  base_doctor_hospital hos, " +
                "  base_doctor doc " +
                " where " +
                "  hos.doctor_code = doc.id" +
                " and hos.dept_code = '" + deptCode + "'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg",JavaBeanUtils.getInstance().mapListJson(list));
        return result;
    }
}
