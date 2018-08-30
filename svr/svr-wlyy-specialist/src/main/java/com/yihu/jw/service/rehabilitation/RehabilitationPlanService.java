package com.yihu.jw.service.rehabilitation;

import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationDetailDao;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yihu.jw.dao.rehabilitation.PatientRehabilitationPlanDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationPlanTemplateDao;
import com.yihu.jw.dao.rehabilitation.RehabilitationTemplateDetailDao;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import com.yihu.jw.restmodel.iot.common.UploadVO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.FileUploadService;
import com.yihu.jw.util.common.QrcodeUtil;
import com.yihu.fastdfs.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by humingfen on 2018/8/17.
 */
@Service
@Transactional
public class RehabilitationPlanService {

    @Value("${neiwang.enable}")
    private Boolean isneiwang;  //如果不是内网项目要转到到内网wlyy在上传
    @Value("${fastDFS.fastdfs_file_url}")
    private String fastdfs_file_url;
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

    public MixEnvelop<String, String> createRehabilitationTemplate(RehabilitationPlanTemplateDO templateDO) {
        templateDO.setCreateTime(new Date());
        templateDO.setDel(1);
        templateDO = templateDao.save(templateDO);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,templateDO.getId());
    }

    public MixEnvelop<Boolean, Boolean> createRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<Boolean,Boolean> updateRehabilitationTemplateDetail(List<RehabilitationTemplateDetailDO> details) {
        String templateId = details.get(0).getTemplateId();
        if(templateId != null && templateId.length() > 0){
            templateDetailDao.deleteByTemplateId(templateId);
        }
        for(RehabilitationTemplateDetailDO detail : details){
            detail.setCreateTime(new Date());
            templateDetailDao.save(detail);
        }
        return MixEnvelop.getSuccess(SpecialistMapping.api_success,true);
    }

    public MixEnvelop<RehabilitationPlanTemplateDO, RehabilitationPlanTemplateDO> findRehabilitationPlanTemplate(Integer adminTeamCode, Integer page, Integer size) {

        if(page != null && size != null){
            String sql = "select * from wlyy_rehabilitation_plan_template t where t.admin_team_code = '" + adminTeamCode + "' and t.del = 1 ORDER BY t.create_time DESC LIMIT "+(page-1)*size+","+size;
            List<RehabilitationPlanTemplateDO> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(RehabilitationPlanTemplateDO.class));
            String countSql = "select count(1) from wlyy_rehabilitation_plan_template t where t.admin_team_code = '" + adminTeamCode + "' and t.del = 1";
            Long count = jdbcTemplate.queryForObject(countSql, Long.class);
            return MixEnvelop.getSuccessListWithPage(SpecialistMapping.api_success,list,page,size,count);
        }else {
            List<RehabilitationPlanTemplateDO> list = templateDao.findByAdminTeamCode(adminTeamCode);
            return MixEnvelop.getSuccessList(SpecialistMapping.api_success,list, list.size());
        }
    }

    /**
     * 根据模板id获取机构服务项目id，然后找出具体服务项目内容
     * @param templateId
     * @return
     */
    public MixEnvelop<RehabilitationTemplateDetailDO, RehabilitationTemplateDetailDO> findTemplateDetailByTemplateId(String templateId) {
        List<String> hospitalServiceItemIds = templateDetailDao.findHospitalServiceItemIdByTemplateId(templateId);
        return MixEnvelop.getSuccess(SpecialistMapping.api_success);
    }

    public PatientRehabilitationPlanDO createPatientRehabilitationPlan(PatientRehabilitationPlanDO planDO) {
        planDO.setCreateTime(new Date());
        planDO.setStatus(1);
        return patientRehabilitationPlanDao.save(planDO);
    }

    public List<RehabilitationDetailDO> createRehabilitationDetail(List<RehabilitationDetailDO> details, String planId) {
        for(RehabilitationDetailDO detail : details) {
//            HospitalServiceItemDO hospitalServiceItemDO = hospitalServiceItemService.findById(detail.getHospitalServiceItemId());
            detail.setPlanId(planId);
            detail.setCreateTime(new Date());
            detail.setStatus(0);
        }
        return (List<RehabilitationDetailDO>)rehabilitationDetailDao.save(details);
    }

    public MixEnvelop<String,String> createServiceQrCode(String planDetailId){
        RehabilitationDetailDO rehabilitationDetailDO = rehabilitationDetailDao.findById(planDetailId);
        String fileUrl = "";
        if (rehabilitationDetailDO!=null) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(rehabilitationDetailDO.getServiceQrCode())) {
                fileUrl = rehabilitationDetailDO.getServiceQrCode();
            } else {
                String contentJsonStr="{\"planDetailId\":\""+planDetailId+"\"}";
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

    public Integer checkAfterQrCode(String planDetailId,String patietCode){
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
}
