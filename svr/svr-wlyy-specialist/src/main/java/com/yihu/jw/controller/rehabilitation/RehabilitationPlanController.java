package com.yihu.jw.controller.rehabilitation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.dao.rehabilitation.RehabilitationOperateRecordsDao;
import com.yihu.jw.entity.specialist.rehabilitation.*;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.HospitalServiceItemDO;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.rehabilitation.RehabilitationPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by humingfen on 2018/8/17.
 */
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "康复服务套餐管理相关操作", description = "康复服务套餐管理相关操作")
public class RehabilitationPlanController extends EnvelopRestEndpoint {

    @Autowired
    private RehabilitationPlanService rehabilitationPlanService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private RehabilitationOperateRecordsDao rehabilitationOperateRecordsDao;

    @PostMapping(value = SpecialistMapping.rehabilitation.createRehabilitationPlanTemplate)
    @ApiOperation(value = "康复服务套餐模板创建")
    public MixEnvelop<String, String> createRehabilitationPlanTemplate(@ApiParam(name = "rehabilitationTemplate", value = "实体JSON")
                                                             @RequestParam(value = "rehabilitationTemplate", required = true)String rehabilitationTemplate){
        try {
            RehabilitationPlanTemplateDO templateDO = toEntity(rehabilitationTemplate, RehabilitationPlanTemplateDO.class);
            return rehabilitationPlanService.createRehabilitationTemplate(templateDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.createRehabilitationTemplateDetail)
    @ApiOperation(value = "康复服务套餐模板明细创建")
    public MixEnvelop<Boolean, Boolean> createRehabilitationTemplateDetail(@ApiParam(name = "rehabilitationTemplateDetail", value = "实体JSON")
                                                             @RequestParam(value = "rehabilitationTemplateDetail", required = true)String rehabilitationTemplateDetail){
        try {
            List<RehabilitationTemplateDetailDO> details = new ObjectMapper().readValue(rehabilitationTemplateDetail, new TypeReference<List<RehabilitationTemplateDetailDO>>(){});
            return rehabilitationPlanService.createRehabilitationTemplateDetail(details);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlanTemplate)
    @ApiOperation(value = "获取康复服务套餐模板列表")
    public MixEnvelop<RehabilitationPlanTemplateDO, RehabilitationPlanTemplateDO> findRehabilitationPlanTemplate(@ApiParam(name = "adminTeamCode", value = "行政团队id")
                                                                                   @RequestParam(value = "adminTeamCode", required = false)Long adminTeamCode,
                                                                               @ApiParam(name = "doctor", value = "专科医生")
                                                                              @RequestParam(value = "doctor", required = false)String doctor,
                                                                              @ApiParam(name = "patient", value = "患者")
                                                                              @RequestParam(value = "patient", required = false)String patient){
        try {
            return rehabilitationPlanService.findRehabilitationPlanTemplate(adminTeamCode, doctor, patient);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findTemplateDetailByTemplateId)
    @ApiOperation(value = "获取康复服务套餐模板明细")
    public MixEnvelop<HospitalServiceItemDO, HospitalServiceItemDO> findTemplateDetailByTemplateId(@ApiParam(name = "templateId", value = "模板id")
                                                                                      @RequestParam(value = "templateId", required = true)String templateId){
        try {
            return rehabilitationPlanService.findTemplateDetailByTemplateId(templateId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.updateRehabilitationTemplateDetail)
    @ApiOperation(value = "编辑康复服务套餐模板明细")
    public MixEnvelop<Boolean, Boolean> updateRehabilitationTemplateDetail(@ApiParam(name = "rehabilitationTemplateDetail", value = "实体JSON")
                                                                           @RequestParam(value = "rehabilitationTemplateDetail", required = true)String rehabilitationTemplateDetail){
        try {
            List<RehabilitationTemplateDetailDO> details = new ObjectMapper().readValue(rehabilitationTemplateDetail, new TypeReference<List<RehabilitationTemplateDetailDO>>(){});
            return rehabilitationPlanService.updateRehabilitationTemplateDetail(details);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @PostMapping(value = SpecialistMapping.rehabilitation.deleteRehabilitationPlanTemplate)
    @ApiOperation(value = "删除康复模板")
    public MixEnvelop<Boolean, Boolean> deleteRehabilitationPlanTemplate(@ApiParam(name = "id", value = "康复模板id")
                                                                           @RequestParam(value = "id", required = true)String id){
        try {
            return rehabilitationPlanService.deleteRehabilitationPlanTemplate(id);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /******************************** 居民康复计划 ***************************************/

    @PostMapping(value = SpecialistMapping.rehabilitation.createPatientRehabilitationPlan)
    @ApiOperation(value = "居民康复服务套餐创建")
    public MixEnvelop<String, String> createPatientRehabilitationPlan(@ApiParam(name = "rehabilitationPlan", value = "实体JSON")
                                                                       @RequestParam(value = "rehabilitationPlan", required = true)String rehabilitationPlan){
        try {
            JSONObject json = new JSONObject(rehabilitationPlan);
            JSONArray array = new JSONArray();
            for(Object planDetail : json.getJSONArray("detail")) {
                JSONObject j = (JSONObject)planDetail;
                String executeTime = j.get("executeTime").toString();
                String[] result = null;
                if(executeTime.contains(",")){
                    result = executeTime.split(",");
                }else {
                    result = new String[1];
                    result[0] = executeTime;
                }
                int len =  result.length;
                while(len > 0){
                    len --;
                    JSONObject temp = new JSONObject(j.toString());
                    temp.put("executeTime", result[len]);
                    temp.put("createUser", json.get("createUser"));
                    temp.put("createUserName", json.get("createUserName"));
                    array.put(temp);
                }
            }
            String planDetails = array.toString();
            ObjectMapper object = new ObjectMapper();
            object.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
            List<RehabilitationDetailDO> details = object.readValue(planDetails, new TypeReference<List<RehabilitationDetailDO>>(){});
            PatientRehabilitationPlanDO planDO = toEntity(rehabilitationPlan, PatientRehabilitationPlanDO.class);
            planDO = rehabilitationPlanService.createPatientRehabilitationPlan(planDO);
            details = rehabilitationPlanService.createRehabilitationDetail(details, planDO.getId());
            return MixEnvelop.getSuccess(SpecialistMapping.api_success);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findServiceItemsByHospital)
    @ApiOperation(value = "获取机构服务项目列表")
    public MixEnvelop<HospitalServiceItemDO,HospitalServiceItemDO> findServiceItemsByHospital(@ApiParam(name = "doctorHospital", value = "医生所在机构")
                                             @RequestParam(required = true)String doctorHospital,
                                             @ApiParam(name = "signHospital", value = "居民签约机构")
                                             @RequestParam(required = false)String signHospital){
        try {
            return rehabilitationPlanService.findServiceItemsByHospital(doctorHospital, signHospital);
        } catch (Exception e) {
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.createServiceQrCode)
    @ApiOperation(value = "根据康复计划明细id和对话sessionId生成服务码")
    public MixEnvelop<String,String> createServiceQrCode(@ApiParam(name = "planDetailId", value = "康复计划项目明细ID")@RequestParam(value = "planDetailId", required = true)String planDetailId,
                                                         @ApiParam(name = "sessionId", value = "IM对话sessionId")@RequestParam(value = "sessionId", required = true)String sessionId){
        try {
            return rehabilitationPlanService.createServiceQrCode(planDetailId,sessionId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.checkAfterQrCode)
    @ApiOperation(value = "居民扫码后验证是否是关联的居民扫码")
    public MixEnvelop<Boolean,Boolean> checkAfterQrCode(@ApiParam(name = "planDetailId", value = "康复计划项目明细ID")@RequestParam(value = "planDetailId", required = true)String planDetailId,
                                                         @ApiParam(name = "patientCode", value = "居民端登录的居民code")@RequestParam(value = "patientCode", required = true)String patientCode){
        try {
            String message="";
            Boolean flag = true;
            if (rehabilitationPlanService.checkAfterQrCode(planDetailId,patientCode)==200){
                message = "验证成功！";
            }
            if (rehabilitationPlanService.checkAfterQrCode(planDetailId,patientCode)==-1){
                message = "请相关居民扫描二维码";
                flag=false;
            }
            if (rehabilitationPlanService.checkAfterQrCode(planDetailId,patientCode)==-10000){
                message = "相关康复管理数据错误,请联系工作人员！";
                flag=false;
            }
            return MixEnvelop.getSuccess(message,flag);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.saveRehabilitationOperateRecodr)
    @ApiOperation(value = "新增operateRecodr")
    public MixEnvelop<RehabilitationOperateRecordsDO,RehabilitationOperateRecordsDO> saveRehabilitationOperateRecodr(@ApiParam(name = "dataJson", value = "实体json",defaultValue = "{\"rehabilitationDetailId\":\"402803f6657f195301657f4fbd3c0001\",\"patientCode\":\"00\",\"patientName\":\"11\",\"doctorCode\":\"22\",\"doctorName\":\"33\",\"node\":\"jjjjjj\",\"relationRecordType\":\"4\",\"relation_record_code\":\"55\",\"relationRecordImg\":\"666666\",\"status\":\"0\"}")@RequestParam(value = "dataJson", required = true)String dataJson){
        try {
            RehabilitationOperateRecordsDO rehabilitationOperateRecordsDO= toEntity(dataJson, RehabilitationOperateRecordsDO.class);
            List<RehabilitationOperateRecordsDO> list = rehabilitationOperateRecordsDao.findByRehabilitationDetailId(rehabilitationOperateRecordsDO.getRehabilitationDetailId());
            if (list!=null && list.size()>0){
                return MixEnvelop.getError("该明细日志已存在！");
            }
            return MixEnvelop.getSuccess(SpecialistMapping.api_success,rehabilitationPlanService.saveRehabilitationRecord(rehabilitationOperateRecordsDO));
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
