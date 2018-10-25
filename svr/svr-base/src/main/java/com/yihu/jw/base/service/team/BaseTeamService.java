package com.yihu.jw.base.service.team;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.base.dao.team.BaseTeamDao;
import com.yihu.jw.base.dao.team.BaseTeamMemberDao;
import com.yihu.jw.base.service.dict.DictHospitalDeptService;
import com.yihu.jw.base.service.doctor.BaseDoctorService;
import com.yihu.jw.base.service.org.OrgTree;
import com.yihu.jw.base.util.ConstantUtils;
import com.yihu.jw.base.util.JavaBeanUtils;
import com.yihu.jw.entity.base.dict.DictHospitalDeptDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorHospitalDO;
import com.yihu.jw.entity.base.doctor.BaseDoctorRoleDO;
import com.yihu.jw.entity.base.patient.BasePatientDO;
import com.yihu.jw.entity.base.patient.PatientMedicareCardDO;
import com.yihu.jw.entity.base.team.BaseTeamMemberDO;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yihu.jw.entity.base.team.BaseTeamDO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 
 * 团队信息服务service
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
public class BaseTeamService extends BaseJpaService<BaseTeamDO, BaseTeamDao> {

    @Autowired
    private BaseTeamMemberService baseTeamMemberService;

    @Autowired
    private BaseTeamMemberDao baseTeamMemberDao;

    @Autowired
    private BaseTeamDao baseTeamDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BaseDoctorService baseDoctorService;

    @Autowired
    private DictHospitalDeptService dictHospitalDeptService;

    /**
     * 新增团队
     * @param jsonData
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public String createTeam(String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject teamJSONObject = jsonObject.getJSONObject("team");
        JSONArray teamMemberArray = jsonObject.getJSONArray("teamMember");
        if(null == teamJSONObject || CollectionUtils.isEmpty(teamMemberArray) ){
            return ConstantUtils.FAIL;
        }
        BaseTeamDO baseTeamDO = objectMapper.readValue(teamJSONObject.toJSONString(),BaseTeamDO.class);
        this.save(baseTeamDO);
        List<BaseTeamMemberDO> memberList = new ArrayList<>();
        teamMemberArray.forEach((teamMember)->{
            try {
                BaseTeamMemberDO baseTeamMemberDO = objectMapper.readValue(teamMember.toString(),BaseTeamMemberDO.class);
                baseTeamMemberDO.setTeamCode(baseTeamDO.getId());
                memberList.add(baseTeamMemberDO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        baseTeamMemberService.batchInsert(memberList);
        return ConstantUtils.SUCCESS;
    }

    /**
     * 修改团队
     * @param jsonData
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateTeam(String jsonData){
        JSONObject result = new JSONObject();
        if(org.springframework.util.StringUtils.isEmpty(jsonData)){
            result.put("msg","jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject team = jsonObject.getJSONObject("team");
        JSONArray teamMembers = jsonObject.getJSONArray("teamMember");
        if(null == team || CollectionUtils.isEmpty(teamMembers)){
            result.put("msg","parameter team or teamMember of jsonData is null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        //判断团队id是否存在
        if(org.springframework.util.StringUtils.isEmpty(team.getString("id"))){
            result.put("msg","parameter id for team is null when update team");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        // 修改团队信息
        BaseTeamDO baseTeamDO = null;
        try {
            baseTeamDO = objectMapper.readValue(team.toJSONString(),BaseTeamDO.class);
        } catch (IOException e) {
            result.put("msg","convert team jsonObject to BaseTeamDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        this.save(baseTeamDO);

        //修改团队成员信息
        BaseTeamMemberDO baseTeamMemberDO = null;
        Set<Object> roleIdList = baseTeamMemberService.findMemberIdList(baseTeamDO.getId());
        try {
            for(Object object : teamMembers){
                baseTeamMemberDO = objectMapper.readValue(object.toString(),BaseTeamMemberDO.class);
                if(roleIdList.contains(baseTeamMemberDO.getId())){
                    roleIdList.remove(baseTeamMemberDO.getId());
                }
                baseTeamMemberService.save(baseTeamMemberDO);
            }
        } catch (IOException e) {
            result.put("msg","convert teamMember jsonObject to BaseTeamMemberDO failed," + e.getCause());
            result.put("response",ConstantUtils.FAIL);
        }
        baseTeamMemberService.delete(roleIdList.toArray());
        result.put("response",ConstantUtils.SUCCESS);
        result.put("msg",baseTeamDO);
        return result;
    }


    /**
     * 查看团队成员 （姓名，身份证）
     * @param orgCode
     * @param teamCode
     * @return
     */
    public JSONObject getTeamMemberList(String orgCode,String teamCode) throws Exception {
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(orgCode) || StringUtils.isEmpty(teamCode)){
            result.put("msg","parameter orgCode or orgCode is null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }
        List<Map<String,Object>> list = baseTeamMemberDao.getTeamMemberList();
        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg", JavaBeanUtils.getInstance().mapListJson(list));
        return result;
    }

    /**
     * 团队id
     * @param teamId
     * @return
     */
    public JSONObject getTeamById(String teamId) throws Exception{
        JSONObject result = new JSONObject();
        if(StringUtils.isEmpty(teamId)){
            result.put("msg","parameter teamCode is null");
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        List<BaseTeamDO> teamDOList = this.findByField("id",teamId);
        if(CollectionUtils.isEmpty(teamDOList)){
            result.put("msg","not exist team for id:"+ teamId);
            result.put("response",ConstantUtils.FAIL);
            return result;
        }
        List<BaseTeamMemberDO> members = baseTeamMemberService.findMembersByTeamCode(teamId);
        result.put("response",ConstantUtils.SUCCESS);
        JSONObject teamInfo = new JSONObject();
        teamInfo.put("team",teamDOList.get(0));
        teamInfo.put("teamMember",members);
        result.put("msg",teamInfo);
        return result;
    }


    /**
     * 生成 机构/科室/医生 树形结构
     */
    public JSONObject generateOneOrgDeptDoctorTree(String jsonData) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONObject result = new JSONObject();
        String orgCode = jsonObject.getString("orgCode");
        String orgName = jsonObject.getString("orgName");
        if(StringUtils.isEmpty(orgCode) || StringUtils.isEmpty(orgName)){
            result.put("msg","parameter orgCode or orgName is not allowed to be null");
            result.put("response", ConstantUtils.FAIL);
            return result;
        }

        List<OrgTree> orgTreeList = new ArrayList<>();

        OrgTree orgTree = new OrgTree();
        orgTree.setParentCode("");
        orgTree.setCode(orgCode);
        orgTree.setName(orgName);

        orgTreeList.add(orgTree);

        //获取该机构下的科室列表
        List<DictHospitalDeptDO> deptList = dictHospitalDeptService.findDeptByOrgCode(orgCode);
        deptList.forEach(one -> {
            OrgTree deptTree = new OrgTree();
            deptTree.setParentCode(orgCode);
            deptTree.setCode(one.getCode());
            deptTree.setName(one.getName());
            orgTreeList.add(deptTree);
        });

        //获取该机构下的医生列表
        String sql = " SELECT " +
                "  hos.doctor_code AS doctorCode," +
                "  doc.name AS doctorName," +
                "  org.code  AS orgCode," +
                "  org.name     AS orgName," +
                "  dept.code    AS deptCode," +
                "  dept.name    AS deptName" +
                " FROM " +
                "  base_doctor_hospital hos," +
                "  dict_hospital_dept dept," +
                "  base_org org," +
                "  base_doctor doc" +
                " WHERE " +
                "  hos.doctor_code = doc.id AND" +
                "  hos.org_code = org.code AND" +
                "  hos.org_code = dept.org_code AND" +
                "  hos.dept_code = dept.code AND hos.org_code = '" + orgCode +"'";

        List<Map<String,Object>> doctorList = jdbcTemplate.queryForList(sql);
        Map<String,Map<String,Object>> deptDoctorMap = new HashMap<>();
        for(Map<String,Object> doctorMap : doctorList){
            String deptCode = String.valueOf(doctorMap.get("deptCode"));
            if(deptDoctorMap.containsKey(deptCode)){
                deptDoctorMap.get(deptCode).putAll(doctorMap);
            }else{
                deptDoctorMap.put(deptCode,doctorMap);
            }
        }

        // 循环科室医生，组装tree结构
        for(String key : deptDoctorMap.keySet()){
            OrgTree deptTree = new OrgTree();
            deptTree.setParentCode(orgCode);
            deptTree.setCode(String.valueOf(deptDoctorMap.get(key).get("deptCode")));
            deptTree.setName(String.valueOf(deptDoctorMap.get(key).get("deptName")));

            OrgTree doctorTree = new OrgTree();
            doctorTree.setParentCode(String.valueOf(deptDoctorMap.get(key).get("deptCode")));
            doctorTree.setCode(String.valueOf(deptDoctorMap.get(key).get("doctorCode")));
            doctorTree.setName(String.valueOf(deptDoctorMap.get(key).get("doctorName")));

            orgTreeList.add(deptTree);
            orgTreeList.add(doctorTree);
        }

        result.put("response", ConstantUtils.SUCCESS);
        result.put("msg",objectMapper.readValue(baseDoctorService.makeTree(orgTreeList,false),JSONArray.class));
        return result;
    }
}
