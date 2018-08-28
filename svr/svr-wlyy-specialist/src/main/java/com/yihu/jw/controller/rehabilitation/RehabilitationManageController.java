package com.yihu.jw.controller.rehabilitation;

import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.rehabilitation.RehabilitationManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘文彬 on 2018/8/16.
 */
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "康复计划管理相关操作", description = "康复计划管理相关操作")
public class RehabilitationManageController {

    @Autowired
    private RehabilitationManageService rehabilitationManageService;
    @Autowired
    private Tracer tracer;

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlanList)
    @ApiOperation(value = "康复管理-康复计划列表")
    public MixEnvelop findRehabilitationPlan(@ApiParam(name = "doctorCode", value = "医生code")
                                             @RequestParam(value = "doctorCode", required = true)String doctorCode,
                                             @ApiParam(name = "patientCondition", value = "居民条件，可以按身份证或者居民名称模糊匹配")
                                             @RequestParam(value = "patientCondition", required = false)String patientCondition,
                                             @ApiParam(name = "diseaseCode", value = "疾病类型")
                                             @RequestParam(value = "diseaseCode", required = false)String diseaseCode,
                                             @ApiParam(name = "planType", value = "安排类型（1康复计划，2转社区医院，3转家庭病床）")
                                             @RequestParam(value = "planType", required = false)Integer planType,
                                             @ApiParam(name = "todaybacklog", value = "今日待办（1、今日待办，2、全部）")
                                             @RequestParam(value = "todaybacklog", required = false,defaultValue = "1")Integer todaybacklog,
                                             @ApiParam(name = "page", value = "第几页，从1开始")
                                             @RequestParam(value = "page", required = false,defaultValue = "1")Integer page,
                                             @ApiParam(name = "pageSize", value = "，每页分页大小")
                                             @RequestParam(value = "pageSize", required = false,defaultValue = "10")Integer pageSize){
        try {
            return rehabilitationManageService.findRehabilitationPlan(doctorCode,diseaseCode,planType,todaybacklog,patientCondition,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlanDetailList)
    @ApiOperation(value = "康复管理-康复计划详情列表")
    public MixEnvelop findRehabilitationPlanDetailList(@ApiParam(name = "page", value = "第几页，从1开始")
                                                       @RequestParam(value = "page", required = false,defaultValue = "1")Integer page,
                                                       @ApiParam(name = "pageSize", value = "，每页分页大小")
                                                       @RequestParam(value = "pageSize", required = false,defaultValue = "10")Integer pageSize){
        try {
//            return rehabilitationManageService.findRehabilitationPlanDetailList(doctorCode,diseaseCode,planType,todaybacklog,patientCondition,page,pageSize);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
