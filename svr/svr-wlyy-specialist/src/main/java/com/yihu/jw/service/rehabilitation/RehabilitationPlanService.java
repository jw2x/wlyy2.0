package com.yihu.jw.service.rehabilitation;

import com.yihu.jw.dao.rehabilitation.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationDetailDao;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.entity.rehabilitation.RehabilitationPlanningDO;
import com.yihu.jw.dao.rehabilitation.RehabilitationPlanTemplateDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationTemplateDetailDao;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.rehabilitation.*;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.restmodel.specialist.PatientSignInfoVO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.FileUploadService;
import com.yihu.jw.service.SpecialistHospitalServiceItemService;
import com.yihu.jw.service.SpecialistService;
import com.yihu.jw.util.HttpClientUtil;
import com.yihu.jw.util.common.QrcodeUtil;
import com.yihu.fastdfs.FastDFSUtil;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * Created by humingfen on 2018/8/17.
 */
@Service
@Transactional
public class RehabilitationPlanService extends BaseJpaService<RehabilitationPlanningDO, RehabilitationPlanningDO> {

    @Value("${neiwang.enable}")
    private Boolean isneiwang;  //如果不是内网项目要转到到内网wlyy在上传
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    private RehabilitationPlanTemplateDao templateDao;
    @Autowired
    private RehabilitationTemplateDetailDao templateDetailDao;
    @Autowired
    private PatientRehabilitationPlanDao patientRehabilitationPlanDao;
    @Autowired
    private RehabilitationDetailDao rehabilitationDetailDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private FastDFSUtil fastDFSHelper;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private RehabilitationOperateRecordsDao rehabilitationOperateRecordsDao;
    @Autowired
    private SpecialistHospitalServiceItemService hospitalServiceItemService;
    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private HttpClientUtil httpClientUtil;

    public ObjEnvelop createRehabilitationTemplate(RehabilitationPlanTemplateDO templateDO) {
        templateDO.setCreateTime(new Date());
        templateDO.setDel(1);
        templateDO = templateDao.save(templateDO);
        return ObjEnvelop.getSuccess(SpecialistMapping.api_success,templateDO.getId());
    }

    public ObjEnvelop createRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return ObjEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public ObjEnvelop updateRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        String templateId = details.get(0).getTemplateId();
        if(templateId != null && templateId.length() > 0){
            templateDetailDao.deleteByTemplateId(templateId);
        }
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return ObjEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop findRehabilitationPlanTemplate(Long adminTeamCode, String doctor, String patient) {

        if(adminTeamCode == null && StringUtils.isNotBlank(doctor) && StringUtils.isNotBlank(patient)){
            PatientSignInfoVO patientSignSpecialistInfo = (PatientSignInfoVO) specialistService.findPatientSignSpecialistInfo(patient, doctor).getObj();
            adminTeamCode = patientSignSpecialistInfo.getTeamCode();
        }
        List<RehabilitationPlanTemplateDO> list = templateDao.findByAdminTeamCode(adminTeamCode);
        return MixEnvelop.getSuccessList(SpecialistMapping.api_success, list);
    }

    /**
     * 根据模板id修改康复模板删除状态
     * @param id
     * @return
     */
    public ObjEnvelop deleteRehabilitationPlanTemplate(String id) {
        templateDao.updateDelById(id);
        return ObjEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    /**
     * 根据模板id获取机构服务项目id，然后找出具体服务项目内容
     * @param templateId
     * @return
     */
    public MixEnvelop<HospitalServiceItemDO, HospitalServiceItemDO> findTemplateDetailByTemplateId(String templateId) {
        List<String> hospitalServiceItemIds = templateDetailDao.findHospitalServiceItemIdByTemplateId(templateId);
        return hospitalServiceItemService.selectById(hospitalServiceItemIds);
    }

    public PatientRehabilitationPlanDO createPatientRehabilitationPlan(PatientRehabilitationPlanDO planDO) {
        planDO.setCreateTime(new Date());
        planDO.setStatus(1);
        return patientRehabilitationPlanDao.save(planDO);
    }

    public List<RehabilitationDetailDO> createRehabilitationDetail(List<RehabilitationDetailDO> details, String planId) {
        for(RehabilitationDetailDO detail : details) {
            List<String> list = new ArrayList<>();
            list.add(detail.getHospitalServiceItemId());
            HospitalServiceItemDO hospitalServiceItemDO = hospitalServiceItemService.selectById(list).getDetailModelList().get(0);
            detail.setHospital(hospitalServiceItemDO.getHospital());
            detail.setHospitalName(hospitalServiceItemDO.getHospitalName());
            detail.setExpense(hospitalServiceItemDO.getExpense());
            detail.setPlanId(planId);
            detail.setCreateTime(new Date());
            detail.setStatus(0);
        }
        return (List<RehabilitationDetailDO>)rehabilitationDetailDao.save(details);
    }

    public MixEnvelop findServiceItemsByHospital(String doctorHospital, String signHospital) {
        JSONArray jsonArray = new JSONArray();
        List<String> list = new ArrayList<>();
        list.add(doctorHospital);
        List<HospitalServiceItemDO> docHospitalServiceItemDO = hospitalServiceItemService.selectByHospital(list).getDetailModelList();
        if(StringUtils.isNotBlank(signHospital)){
            if(signHospital.equals(doctorHospital)) {
                for (Object object : docHospitalServiceItemDO) {
                    JSONObject json = (JSONObject) JSONObject.toJSON(object);
                    json.put("type", "社区");
                    jsonArray.add(json);
                }
            }else if(!signHospital.equals(doctorHospital)){
                JSONArray array = new JSONArray();
                List<String> itemIds = new ArrayList<>();
                list.remove(0);
                list.add(signHospital);
                List<HospitalServiceItemDO> signHospitalServiceItemDO = hospitalServiceItemService.selectByHospital(list).getDetailModelList();
                HashSet signSet = new HashSet();
                for(HospitalServiceItemDO h : signHospitalServiceItemDO){
                    signSet.add(h.getServiceItemId());
                }
                //将服务项目重复的都放入itemIds集合里,并在列表中删除该条数据
                List<HospitalServiceItemDO> temp = new ArrayList<>();
                temp.addAll(docHospitalServiceItemDO);
                for(HospitalServiceItemDO h : docHospitalServiceItemDO){
                    int size = signSet.size();
                    String itemId = h.getServiceItemId();
                    signSet.add(itemId);
                    if(signSet.size() == size){
                        itemIds.add(itemId);
                        temp.remove(h);
                    }
                }
                array.addAll(signHospitalServiceItemDO);
                array.addAll(temp);
                for(Object obj : array){
                    //往实体类插入数据
                    JSONObject json = (JSONObject) JSONObject.toJSON(obj);
                    if(json.get("hospital").equals(signHospital)){
                        json.put("type", "社区");
                    }else if(json.get("hospital").equals(doctorHospital)){
                        json.put("type", "医院");
                    }
                    if(itemIds.contains(json.get("serviceItemId"))){
                        json.put("type", "社区，医院");
                    }
                    jsonArray.add(json);
                }
            }
        }else {
            jsonArray.addAll(docHospitalServiceItemDO);
        }
        return MixEnvelop.getSuccessList(SpecialistMapping.api_success, jsonArray);
    }

    /**
     * 调用服务包创建接口
     * @param planDO
     * @param details
     */
    public String addServicePackage(PatientRehabilitationPlanDO planDO, List<RehabilitationDetailDO> details) {
        JSONObject jsonData = new JSONObject();
        JSONObject servicePackageVO = new JSONObject();
        JSONObject signRecordVO = new JSONObject();
        JSONArray detailsVOList = new JSONArray();
        //服务项目数据
        for(RehabilitationDetailDO detail : details){
            JSONObject detailsVO = new JSONObject();
            detailsVO.put("executionType", "1");
            detailsVO.put("executionTime", DateUtil.dateToStr(detail.getExecuteTime(), "yyyy-MM-dd hh:mm:ss"));
            List<String> hospitalServiceIds = new ArrayList<>();
            hospitalServiceIds.add(detail.getHospitalServiceItemId());
            HospitalServiceItemDO signHospitalServiceItemDO = hospitalServiceItemService.selectById(hospitalServiceIds).getDetailModelList().get(0);
            detailsVO.put("code", signHospitalServiceItemDO.getServiceItemId());
            detailsVO.put("name", signHospitalServiceItemDO.getSpecialistServiceItemDO().getTitle());
            detailsVOList.add(detailsVO);
        }
        //服务包数据
        servicePackageVO.put("detailsVOList", detailsVOList);
        servicePackageVO.put("saasId", "xmihealth");
        servicePackageVO.put("name", planDO.getTitle());
        servicePackageVO.put("introduce", planDO.getTitle());
        servicePackageVO.put("creater", planDO.getCreateUser());
        servicePackageVO.put("type", "1");
        servicePackageVO.put("price", planDO.getTotalExpense());
        //签约记录数据
        signRecordVO.put("servicePackageName", planDO.getTitle());
        signRecordVO.put("patient", planDO.getPatient());
        signRecordVO.put("name", planDO.getName());
        PatientSignInfoVO patientSignInfoVO = (PatientSignInfoVO) specialistService.findPatientSignSpecialistInfo(planDO.getPatient(), planDO.getCreateUser()).getObj();
        signRecordVO.put("idcard", patientSignInfoVO.getIdcard());
        signRecordVO.put("ssc", patientSignInfoVO.getSsc());
        signRecordVO.put("signDoctor", patientSignInfoVO.getDoctor());
        signRecordVO.put("signDoctorName", patientSignInfoVO.getDoctorName());
        signRecordVO.put("hospital", patientSignInfoVO.getHospital());
        signRecordVO.put("hospitalName", patientSignInfoVO.getHospitalName());
        signRecordVO.put("adminTeamCode", patientSignInfoVO.getTeamCode());
        signRecordVO.put("price", planDO.getTotalExpense());
        signRecordVO.put("saasId", "xmihealth");

        jsonData.put("servicePackageVO", servicePackageVO);
        jsonData.put("signRecordVO", signRecordVO);

        String response = null;
        try {
            response = httpClientUtil.postBody(baseUrl + "base_rehabilitation/create", jsonData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject rs = JSONObject.parseObject(response);
        if ("success".equals(rs.getString("message"))) {
            return rs.getJSONObject("obj").getString("id");
        }
        return null;
    }

    public void updateServicePackageId(String planId, String servicePackageId) {
        patientRehabilitationPlanDao.updateServicePackageId(planId, servicePackageId);
    }

    public MixEnvelop<String,String> createServiceQrCode(String planDetailId,String sessionId){
        RehabilitationDetailDO rehabilitationDetailDO = rehabilitationDetailDao.findById(planDetailId);
        String fileUrl = "";
        if (rehabilitationDetailDO!=null) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(rehabilitationDetailDO.getServiceQrCode())) {
                fileUrl = rehabilitationDetailDO.getServiceQrCode();
            } else {
                //String contentJsonStr="{\"planDetailId\":\""+planDetailId+"\",\"sessionId\":\""+sessionId+"\"}";
                String contentJsonStr=""+"?paramStr="+planDetailId+","+sessionId;
                InputStream ipt = QrcodeUtil.createQrcode(contentJsonStr, 300, "png");
                isneiwang = false;
                if (isneiwang) {
                    // 圖片列表
                    List<String> tempPaths = new ArrayList<String>();
                    try {
                        ObjectNode imgNode = fastDFSHelper.upload(ipt, "png", "plan_service_qrcode" + System.currentTimeMillis());
                        com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSONObject.parseObject(imgNode.toString());
                        tempPaths.add(json.getString("fileId"));
                        String urls = "";
                        for (String image : tempPaths) {
                            if (urls.length() == 0) {
                                urls = image;
                            } else {
                                urls += "," + image;
                            }
                        }
                        fileUrl = fastdfs_file_url + urls;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        UploadVO uploadVO = fileUploadService.request(request, ipt, "png");
                        if (uploadVO!=null){
                            fileUrl = uploadVO.getFullUrl();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //更新到康复计划居民关系表中
                String sql = "update wlyy_rehabilitation_plan_detail set service_qr_code='" + fileUrl + "' where id='" + planDetailId + "'";
                jdbcTemplate.update(sql);
            }
        }
        return MixEnvelop.getSuccess("获取二维码成功！",fileUrl);
    }

    public Integer checkAfterQrCode(String planDetailId,String patietCode)throws Exception{
        int result = 0;
        String sql ="SELECT rp.patient FROM `wlyy_rehabilitation_plan_detail` pd LEFT JOIN wlyy_patient_rehabilitation_plan rp ON pd.plan_id = rp.id WHERE pd.id='"+planDetailId+"'";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        if (list!=null && list.size()>0){
            if (String.valueOf(list.get(0).get("patient")).equals(patietCode)){
                result =200;
            }else {
                result = -1;
            }
        }else {
            result = -10000;
        }
        return result;
    }

    public RehabilitationOperateRecordsDO saveRehabilitationRecord(RehabilitationOperateRecordsDO rehabilitationOperateRecordsDO){
        RehabilitationDetailDO rehabilitationDetailDO = rehabilitationDetailDao.findById(rehabilitationOperateRecordsDO.getRehabilitationDetailId());
        rehabilitationOperateRecordsDO.setId(getCode());
        rehabilitationOperateRecordsDO.setReserveTime(rehabilitationDetailDO.getExecuteTime());
        rehabilitationOperateRecordsDO.setCompleteTime(new Date());
        return rehabilitationOperateRecordsDao.save(rehabilitationOperateRecordsDO);
    }

    /**
     * 更新康复计划项目状态
     * @param status
     * @param planId
     * @return
     */
    public Envelop updatePlanStatusById(Integer status, String planId) throws Exception{
        if(patientRehabilitationPlanDao.updateStatusById(status,planId)>0){

            return Envelop.getSuccess(SpecialistMapping.api_success);
        }
        return Envelop.getError("更新失败！");
    }
}
