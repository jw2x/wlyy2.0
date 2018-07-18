package com.yihu.jw.controller;

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.entity.specialist.SpecialistPatientRelationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.specialist.*;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistScreenResultService;
import com.yihu.jw.service.SpecialistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangdan on 2018/7/6.
 */
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "专科医生疾病筛查相关操作", description = "专科医生疾病筛查相关操作")
public class SpecialistScreenResultController extends EnvelopRestController {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private Tracer tracer;
    @Autowired
    private SpecialistScreenResultService specialistScreenResultService;

    @GetMapping(value = SpecialistMapping.screen.getScreenResult)
    @ApiOperation(value = "专科医生获取筛查记录列表")
    public Envelop<SurveyScreenResultVo> createSpecialistPatientRelation(@ApiParam(name = "doctor", value = "专科医生ID")@RequestParam(value = "doctor")String doctor,
                                                            @ApiParam(value = "当前页")@RequestParam(value = "pageNo") int pageNo,
                                                            @ApiParam(value = "显示记录数")@RequestParam(value = "pageSize") int pageSize,
                                                            @ApiParam(value = "1已跟踪 2已预约")@RequestParam(value = "type",required = false) int type){
        try {
            return specialistScreenResultService.getScreenList(doctor,type,pageNo,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.screen.getScreenCount)
    @ApiOperation(value = "专科医生首页--筛查已跟踪和已预约的数目")
    public Envelop<Map<String,Object>> findSpecialistPatientRelation(@ApiParam(name = "doctor", value = "专科医生ID")@RequestParam(value = "doctor")String doctor){
        try {
            return specialistScreenResultService.getResultCount(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.screen.getScreenResultDetail)
    @ApiOperation(value = "查看筛查结果记录详情.")
    public Envelop<Map<String,Object>> getScreenResultDetail(@ApiParam(value = "筛查结果唯一code")@RequestParam(value = "code") String code) {
        try {
            return specialistScreenResultService.getScreenResultDetail(code);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }
}
