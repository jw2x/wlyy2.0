package com.yihu.jw.controller.rehabilitation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.entity.specialist.rehabilitation.PatientRehabilitationPlanDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationDetailDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationPlanTemplateDO;
import com.yihu.jw.entity.specialist.rehabilitation.RehabilitationTemplateDetailDO;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.rehabilitation.RehabilitationPlanService;
import com.yihu.jw.util.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                                                                                   @RequestParam(value = "adminTeamCode", required = true)Integer adminTeamCode,
                                                                               @ApiParam(name = "page", value = "第几页，从1开始")
                                                                              @RequestParam(value = "page", required = false)Integer page,
                                                                              @ApiParam(name = "size", value = "，每页分页大小")
                                                                              @RequestParam(value = "size", required = false)Integer size){
        try {
            return rehabilitationPlanService.findRehabilitationPlanTemplate(adminTeamCode, page, size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findTemplateDetailByTemplateId)
    @ApiOperation(value = "获取康复服务套餐模板明细")
    public MixEnvelop<RehabilitationTemplateDetailDO, RehabilitationTemplateDetailDO> findTemplateDetailByTemplateId(@ApiParam(name = "templateId", value = "模板id")
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



    @PostMapping(value = SpecialistMapping.rehabilitation.createServiceQrCode)
    @ApiOperation(value = "根据康复计划id和居民code生成服务码")
    public MixEnvelop<String,String> createServiceQrCode(@ApiParam(name = "planId", value = "计划居民关系唯一标识")@RequestParam(value = "planId", required = true)String planId,
                                                         @ApiParam(name = "patientCode", value = "居民code")@RequestParam(value = "patientCode", required = true)String patientCode){
        try {
            return rehabilitationPlanService.createServiceQrCode(planId,patientCode);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
