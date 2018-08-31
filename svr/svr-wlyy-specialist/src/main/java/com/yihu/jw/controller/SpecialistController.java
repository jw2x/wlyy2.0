package com.yihu.jw.controller;

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.specialist.*;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Trick on 2018/4/25.
 */
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "专科医生相关操作", description = "专科医生相关操作")
public class SpecialistController extends EnvelopRestEndpoint {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private Tracer tracer;

    @PostMapping(value = SpecialistMapping.specialist.createSpecialistRelation)
    @ApiOperation(value = "创建专科医生与患者匹配关系")
    public MixEnvelop<Boolean, Boolean> createSpecialistPatientRelation(@ApiParam(name = "specialistPatientRelation", value = "实体JSON")
                                                                @RequestParam(value = "specialistPatientRelation", required = false)String specialistPatientRelation){
        try {
            SpecialistPatientRelationDO ps = toEntity(specialistPatientRelation, SpecialistPatientRelationDO.class);
            return specialistService.createSpecialistsPatientRelation(ps);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findSpecialistPatientRelation)
    @ApiOperation(value = "获取专科医生关系列表")
    public MixEnvelop<SpecialistPatientRelationVO, SpecialistPatientRelationVO> findSpecialistPatientRelation(@ApiParam(name = "doctor", value = "专科医生ID")
                                                                                  @RequestParam(value = "doctor", required = false)String doctor,
                                                                                 @ApiParam(name = "page", value = "第几页，从1开始")
                                                                              @RequestParam(value = "page", required = false)Integer page,
                                                                                 @ApiParam(name = "size", value = "，每页分页大小")
                                                                                  @RequestParam(value = "size", required = false)Integer size){
        try {
            return specialistService.findSpecialistPatientRelation(doctor,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findSpecialistPatientRelationCout)
    @ApiOperation(value = "获取专科医生下未分配标签居民数目")
    public MixEnvelop<Long, Long> findSpecialistPatientRelationCout(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor){
        try {
            return specialistService.findSpecialistPatientRelationCout(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findNoLabelPatientRelation)
    @ApiOperation(value = "获取专科医生下未分配标签居民")
    public MixEnvelop<PatientRelationVO, PatientRelationVO> findNoLabelPatientRelation(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor){
        try {
            return specialistService.findNoLabelPatientRelation(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.saveHealthAssistant)
    @ApiOperation(value = "保存配置居民计管师居民")
    public MixEnvelop<Boolean, Boolean> saveHealthAssistant(@ApiParam(name = "json", value = "计管师居民实体") @RequestParam(required = true)String json){
        try {
            List<SpecialistPatientRelationDO> info = (List<SpecialistPatientRelationDO>) JSONArray.parseArray(json, SpecialistPatientRelationDO.class);
            return specialistService.saveHealthAssistant(info);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientRelatioByAssistant)
    @ApiOperation(value = "根据计管师获取居民信息")
    public MixEnvelop<PatientRelationVO, PatientRelationVO> findPatientRelatioByAssistant(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor,
                                                                       @ApiParam(name = "assistant", value = "计管师") @RequestParam(required = true)String assistant,
                                                                       @ApiParam(name = "page", value = "第几页，1开始") @RequestParam(required = true)Integer page,
                                                                       @ApiParam(name = "size", value = "每页大小") @RequestParam(required = true)Integer size) {
        try {
            return specialistService.findPatientRelatioByAssistant(doctor,assistant,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.getPatientByLabel)
    @ApiOperation(value = "根据标签获取居民信息")
    public MixEnvelop<PatientLabelVO, PatientLabelVO> getPatientByLabel(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor,
                                                        @ApiParam(name = "labelType", value = "标签类型") @RequestParam(required = true)String labelType,
                                                        @ApiParam(name = "labelCode", value = "标签code") @RequestParam(required = true)String labelCode,
                                                        @ApiParam(name = "page", value = "第几页，1开始") @RequestParam(required = true)Integer page,
                                                        @ApiParam(name = "size", value = "每页大小") @RequestParam(required = true)Integer size){
        try {
            return specialistService.getPatientByLabel(doctor,labelType,labelCode,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    @GetMapping(value = SpecialistMapping.specialist.getLabelpatientCount)
    @ApiOperation(value = "根据标签获取居民数量")
    public MixEnvelop<Long, Long> getLabelpatientCount(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor,
                                                 @ApiParam(name = "labelType", value = "标签类型") @RequestParam(required = true)String labelType,
                                                 @ApiParam(name = "labelCode", value = "标签code") @RequestParam(required = true)String labelCode) {
        try {
            return specialistService.getLabelpatientCount(doctor,labelType,labelCode);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.getAssistantPatientCount)
    @ApiOperation(value = "根据计管获取居民数量")
    public MixEnvelop<Long, Long> getAssistantPatientCount(@ApiParam(name = "doctor", value = "医生") @RequestParam(required = true)String doctor,
                                                     @ApiParam(name = "assistant", value = "计管师医生") @RequestParam(required = true)String assistant) {
        try {
            return specialistService.getAssistantPatientCount(doctor,assistant);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.getDoctorPatientByName)
    @ApiOperation(value = "搜索专科医生居民")
    public MixEnvelop<PatientRelationVO, PatientRelationVO> getDoctorPatientByName(@ApiParam(name = "doctor", value = "医生code") @RequestParam(required = true)String doctor,
                                                                @ApiParam(name = "nameKey", value = "居民姓名模糊") @RequestParam(required = false)String nameKey,
                                                                @ApiParam(name = "page", value = "第几页，1开始") @RequestParam(required = true)Integer page,
                                                                @ApiParam(name = "size", value = "每页大小") @RequestParam(required = true)Integer size) {
        try {
            return specialistService.getDoctorPatientByName(doctor,nameKey,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientNoAssistant)
    @ApiOperation(value = "获取未分配计管师居民列表")
    public MixEnvelop<PatientRelationVO, PatientRelationVO> findPatientNoAssistant(@ApiParam(name = "doctor", value = "医生code") @RequestParam(required = true)String doctor,
                                                                @ApiParam(name = "page", value = "第几页，1开始") @RequestParam(required = true)Integer page,
                                                                @ApiParam(name = "size", value = "每页大小") @RequestParam(required = true)Integer size){
        try {
            return specialistService.findPatientNoAssistant(doctor,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.signSpecialistTeam)
    @ApiOperation(value = "提交专科医生签约")
    public MixEnvelop<SpecialistTeamVO, SpecialistTeamVO> signSpecialistTeam(@ApiParam(name = "patient", value = "居民code") @RequestParam(required = true)String patient,
                                                           @ApiParam(name = "patientName", value = "居民姓名") @RequestParam(required = true)String patientName,
                                                           @ApiParam(name = "doctor", value = "医生code") @RequestParam(required = true)String doctor,
                                                           @ApiParam(name = "doctorName", value = "医生姓名") @RequestParam(required = true)String doctorName,
                                                           @ApiParam(name = "teamCode", value = "团队code") @RequestParam(required = true)Long teamCode) {
        try {
            return specialistService.signSpecialistTeam( patient,  patientName,  doctor,  doctorName,  teamCode);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.agreeSpecialistTeam)
    @ApiOperation(value = "专科医生审核")
    public MixEnvelop<Boolean, Boolean> agreeSpecialistTeam(@ApiParam(name = "state", value = "状态0为拒绝，1为同意") @RequestParam(required = true)String state,
                                                   @ApiParam(name = "relationCode", value = "关联code") @RequestParam(required = true)String relationCode,
                                                   @ApiParam(name = "remark", value = "审核失败原因") @RequestParam(required = false)String remark,
                                                   @ApiParam(name = "health_assistant", value = "计管医生CODE") @RequestParam(required = false)String health_assistant,
                                                   @ApiParam(name = "health_assistant_name", value = "计管医生名称") @RequestParam(required = false)String health_assistant_name) {
        try {
            return specialistService.agreeSpecialistTeam(state,relationCode,remark,health_assistant,health_assistant_name);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientSigninfo)
    @ApiOperation(value = "获取签约信息")
    public MixEnvelop<PatientSignInfoVO, PatientSignInfoVO> findPatientSigninfo(String code){
        try {
            return specialistService.findPatientSigninfo(code);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientTeamList)
    @ApiOperation(value = "获取居民所有有效签约团队信息")
    public MixEnvelop<SpecialistTeamVO, SpecialistTeamVO> findPatientTeamList(@ApiParam(name = "patient", value = "居民code") @RequestParam(required = true)String patient){
        try {
            return specialistService.findPatientTeamList(patient);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientSignSpecialist)
    @ApiOperation(value = "获取居民所有有效签约医生信息")
    public MixEnvelop findPatientSignSpecialist(@ApiParam(name = "patient", value = "居民code") @RequestParam(required = true)String patient){
        try {
            return specialistService.findPatientSignSpecialist(patient);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findPatientSignSpecialistInfo)
    @ApiOperation(value = "获取居民与当前专科医生有效签约信息")
    public MixEnvelop findPatientSignSpecialistInfo(@ApiParam(name = "patient", value = "居民code") @RequestParam(required = true)String patient,
                                                    @ApiParam(name = "doctor", value = "专科医生code") @RequestParam(required = true)String doctor) {
        try {
            return specialistService.findPatientSignSpecialistInfo(patient,doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
    
    @GetMapping(value = SpecialistMapping.specialist.findDoctorAndDoctorHealthBySpecialDoctor)
    @ApiOperation(value = "获取当前专科医生有关联的家庭医生和健管师列表")
    public MixEnvelop findDoctorAndDoctorHealthBySpecialDoctor(
            @ApiParam(name = "doctor", value = "专科医生code") @RequestParam(required = true)String doctor){
        try {
            return specialistService.findDoctorAndDoctorHealthBySpecialDoctor(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
    
    @GetMapping(value = SpecialistMapping.specialist.findSpecialistSignFamilyPatientCout)
    @ApiOperation(value = "获取专科医生家庭医生共管居民数目")
    public MixEnvelop<Long, Long> findSpecialistSignFamilyPatientCout(
            @ApiParam(name = "specialdoctor", value = "专科医生") @RequestParam(required = true)String specialdoctor,
            @ApiParam(name = "familydoctor", value = "家庭医生") @RequestParam(required = true)String familydoctor
    ){
        try {
            return specialistService.findSpecialistSignFamilyPatientCout(specialdoctor,familydoctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
    
    @GetMapping(value = SpecialistMapping.specialist.getSpecialistSignFamilyPatientByName)
    @ApiOperation(value = "搜索专科医生家庭医生共管居民")
    public MixEnvelop<PatientRelationVO, PatientRelationVO> getSpecialistSignFamilyPatientByName(
            @ApiParam(name = "specialdoctor", value = "专科医生") @RequestParam(required = true)String specialdoctor,
            @ApiParam(name = "familydoctor", value = "家庭医生") @RequestParam(required = true)String familydoctor,
            @ApiParam(name = "nameKey", value = "居民姓名模糊") @RequestParam(required = false)String nameKey,
            @ApiParam(name = "page", value = "第几页，1开始") @RequestParam(required = true)Integer page,
            @ApiParam(name = "size", value = "每页大小") @RequestParam(required = true)Integer size) {
        try {
            return specialistService.getSpecialistSignFamilyPatientByName(specialdoctor,familydoctor,nameKey,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.getPatientAndDiseaseByDoctor)
    @ApiOperation(value = "根据医生code获取签约居民信息及签约疾病类型")
    public MixEnvelop<PatientDisseaseInfoVO, PatientDisseaseInfoVO> getPatientAndDiseaseByDoctor(
            @ApiParam(name = "doctor", value = "医生code") @RequestParam(required = true)String doctor){
        try {
            return specialistService.getPatientAndDiseaseByDoctor(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


//    @PostMapping(value = SpecialistMapping.specialist.createArticle)
//    @ApiOperation(value = "创建专科医生健康文章")
//    public Envelop<Boolean> createSpecialistArticle(@ApiParam(name = "specialistArticle", value = "专科医生健康文章JSON")
//                                                    @RequestParam(value = "specialistArticle", required = false)String specialistArticle){
//        try {
//            SpecialistArticleDO ps = toEntity(specialistArticle, SpecialistArticleDO.class);
//            return specialistService.createSpecialistArticle(ps);
//        }catch (Exception e){
//            e.printStackTrace();
//            tracer.getCurrentSpan().logEvent(e.getMessage());
//            return Envelop.getError(e.getMessage());
//        }
//    }

//    @PostMapping(value = SpecialistMapping.specialist.createConsult)
//    @ApiOperation(value = "创建专科医生咨询")
//    public Envelop<Boolean> createSpscialistConsult(@ApiParam(name = "specialistConsult", value = "专科医生咨询实体JSON")
//                                                    @RequestParam(value = "specialistConsult", required = false)String specialistConsult){
//        try {
//            SpecialistConsultDO ps = toEntity(specialistConsult, SpecialistConsultDO.class);
//            return specialistService.createSpscialistConsult(ps);
//        }catch (Exception e){
//            e.printStackTrace();
//            tracer.getCurrentSpan().logEvent(e.getMessage());
//            return Envelop.getError(e.getMessage());
//        }
//    }

//    @PostMapping(value = SpecialistMapping.specialist.createSpecialists)
//    @ApiOperation(value = "创建专科医生")
//    public Envelop<Boolean> createSpecialists(@ApiParam(name = "specialists", value = "专科医生实体")
//                                              @RequestParam(value = "code", required = false)String specialists){
//        try {
//            List<SpecialistDO> infos = new ObjectMapper().readValue(specialists, new TypeReference<List<SpecialistDO>>(){});
//            return specialistService.createSpecialists(infos);
//        }catch (Exception e){
//            e.printStackTrace();
//            tracer.getCurrentSpan().logEvent(e.getMessage());
//            return Envelop.getError(e.getMessage());
//        }
//    }
}
