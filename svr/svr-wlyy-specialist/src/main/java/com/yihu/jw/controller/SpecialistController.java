package com.yihu.jw.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.entity.specialist.SpecialistArticleDO;
import com.yihu.jw.entity.specialist.SpecialistConsultDO;
import com.yihu.jw.entity.specialist.SpecialistDO;
import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.specialist.SpecialistPatientRelationVO;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
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
public class SpecialistController extends EnvelopRestController {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private Tracer tracer;

    @PostMapping(value = SpecialistMapping.specialist.createSpecialists)
    @ApiOperation(value = "创建专科医生")
    public Envelop<Boolean> createSpecialists(@ApiParam(name = "specialists", value = "专科医生实体")
                                                  @RequestParam(value = "code", required = false)String specialists){
        try {
            List<SpecialistDO> infos = new ObjectMapper().readValue(specialists, new TypeReference<List<SpecialistDO>>(){});
            return specialistService.createSpecialists(infos);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.createSpecialistRelation)
    @ApiOperation(value = "创建专科医生与患者匹配关系")
    public Envelop<Boolean> createSpecialistPatientRelation(@ApiParam(name = "specialistPatientRelation", value = "实体JSON")
                                                                @RequestParam(value = "specialistPatientRelation", required = false)String specialistPatientRelation){
        try {
            SpecialistPatientRelationDO ps = toEntity(specialistPatientRelation, SpecialistPatientRelationDO.class);
            return specialistService.createSpecialistsPatientRelation(ps);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.createConsult)
    @ApiOperation(value = "创建专科医生咨询")
    public Envelop<Boolean> createSpscialistConsult(@ApiParam(name = "specialistConsult", value = "专科医生咨询实体JSON")
                                                        @RequestParam(value = "specialistConsult", required = false)String specialistConsult){
        try {
            SpecialistConsultDO ps = toEntity(specialistConsult, SpecialistConsultDO.class);
            return specialistService.createSpscialistConsult(ps);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.specialist.createArticle)
    @ApiOperation(value = "创建专科医生健康文章")
    public Envelop<Boolean> createSpecialistArticle(@ApiParam(name = "specialistArticle", value = "专科医生健康文章JSON")
                                                        @RequestParam(value = "specialistArticle", required = false)String specialistArticle){
        try {
            SpecialistArticleDO ps = toEntity(specialistArticle, SpecialistArticleDO.class);
            return specialistService.createSpecialistArticle(ps);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.specialist.findSpecialistPatientRelation)
    @ApiOperation(value = "获取专科医生关系列表")
    public Envelop<SpecialistPatientRelationVO> findSpecialistPatientRelation(@ApiParam(name = "doctor", value = "专科医生ID")
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
            return Envelop.getError(e.getMessage());
        }
    }


}
