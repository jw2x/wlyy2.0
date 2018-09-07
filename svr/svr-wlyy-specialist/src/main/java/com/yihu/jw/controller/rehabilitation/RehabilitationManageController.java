package com.yihu.jw.controller.rehabilitation;

import com.yihu.jw.dao.rehabilitation.RehabilitationOperateRecordsDao;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.rehabilitation.RehabilitationManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @Autowired
    private RehabilitationOperateRecordsDao rehabilitationOperateRecordsDao;

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlanList)
    @ApiOperation(value = "康复管理-康复计划列表")
    public MixEnvelop findRehabilitationPlan(@ApiParam(name = "doctorType", value = "医生类型（1、专科医生，2、家庭医生）", required = true)
                                             @RequestParam(value = "doctorType", required = true)Integer doctorType,
                                             @ApiParam(name = "doctorCode", value = "医生code", required = true)
                                             @RequestParam(value = "doctorCode", required = true)String doctorCode,
                                             @ApiParam(name = "patientCondition", value = "居民条件，可以按身份证或者居民名称模糊匹配", required = false)
                                             @RequestParam(value = "patientCondition", required = false)String patientCondition,
                                             @ApiParam(name = "diseaseCode", value = "疾病类型code", required = false)
                                             @RequestParam(value = "diseaseCode", required = false)String diseaseCode,
                                             @ApiParam(name = "planType", value = "安排类型（1康复计划，2转社区医院，3转家庭病床）", required = false)
                                             @RequestParam(value = "planType", required = false)Integer planType,
                                             @ApiParam(name = "todaybacklog", value = "今日待办（1、今日待办，2、全部）", required = false)
                                             @RequestParam(value = "todaybacklog", required = false,defaultValue = "1")Integer todaybacklog,
                                             @ApiParam(name = "page", value = "第几页，从1开始", required = false)
                                             @RequestParam(value = "page", required = false,defaultValue = "1")Integer page,
                                             @ApiParam(name = "pageSize", value = "每页分页大小", required = false)
                                             @RequestParam(value = "pageSize", required = false,defaultValue = "10")Integer pageSize){
        try {
            return rehabilitationManageService.findRehabilitationPlan(doctorType,doctorCode,diseaseCode,planType,todaybacklog,patientCondition,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlanDetailList)
    @ApiOperation(value = "康复管理-康复计划详情列表")
    public ObjEnvelop findRehabilitationPlanDetailList(@ApiParam(name = "doctorCode", value = "医生code", required = true)
                                                       @RequestParam(value = "doctorCode", required = true)String doctorCode,
                                                       @ApiParam(name = "patientCode", value = "居民code", required = true)
                                                       @RequestParam(value = "patientCode", required = true)String patientCode){
        try {
            return rehabilitationManageService.findRehabilitationPlanDetailList(doctorCode,patientCode);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.calendarPlanDetail)
    @ApiOperation(value = "康复管理-康复计划按日历展示")
    public ObjEnvelop calendarPlanDetail(@ApiParam(name = "executeStartTime", value = "日历开始时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                          @RequestParam(value = "executeStartTime", required = true)String executeStartTime,
                                          @ApiParam(name = "executeEndTime", value = "日历结束时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                          @RequestParam(value = "executeEndTime", required = true)String executeEndTime,
                                          @ApiParam(name = "planId", value = "计划id", required = true)
                                          @RequestParam(value = "planId", required = true)String planId,
                                          @ApiParam(name = "searchTask", value = "快速查找任务：（1、我的任务，2、随访，3、复诊，4、健康教育）", required = false)
                                          @RequestParam(value = "searchTask", required = false)Integer searchTask,
                                          @ApiParam(name = "status", value = "任务状态（0未完成，1已完成，2已预约）", required = false)
                                          @RequestParam(value = "status", required = false)Integer status,
                                          @ApiParam(name = "doctorCode", value = "医生code（专科医生、家庭医生）", required = false)
                                          @RequestParam(value = "doctorCode", required = false)String doctorCode){
        try {
            return rehabilitationManageService.calendarPlanDetail(executeStartTime,executeEndTime,planId,searchTask,status,doctorCode);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.calendarPlanDetailList)
    @ApiOperation(value = "康复管理-康复计划按列表展示")
    public ObjEnvelop calendarPlanDetailList(@ApiParam(name = "executeStartTime", value = "日历开始时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                         @RequestParam(value = "executeStartTime", required = true)String executeStartTime,
                                         @ApiParam(name = "executeEndTime", value = "日历结束时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                         @RequestParam(value = "executeEndTime", required = true)String executeEndTime,
                                         @ApiParam(name = "planId", value = "计划id", required = true)
                                         @RequestParam(value = "planId", required = true)String planId,
                                         @ApiParam(name = "searchTask", value = "快速查找任务：（1、我的任务，2、随访，3、复诊，4、健康教育）", required = false)
                                         @RequestParam(value = "searchTask", required = false)Integer searchTask,
                                         @ApiParam(name = "status", value = "任务状态（0未完成，1已完成，2已预约）", required = false)
                                         @RequestParam(value = "status", required = false)Integer status,
                                         @ApiParam(name = "doctorCode", value = "医生code（专科医生、家庭医生）", required = false)
                                         @RequestParam(value = "doctorCode", required = false)String doctorCode){
        try {
            return rehabilitationManageService.calendarPlanDetailList(planId,searchTask,status,doctorCode,executeStartTime,executeEndTime);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.serviceItemList)
    @ApiOperation(value = "康复管理-多个康复计划服务项目内容信息列表")
    public ObjEnvelop serviceItemList(@ApiParam(name = "planDetailIds", value = "多个服务项目id用‘，’分隔", required = true)
                                             @RequestParam(value = "planDetailIds", required = true)String planDetailIds){
        try {
            return rehabilitationManageService.serviceItemList(planDetailIds);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.serviceItem)
    @ApiOperation(value = "康复管理-康复计划服务项目确认详情页")
    public ObjEnvelop serviceItem(@ApiParam(name = "planDetailId", value = "服务项目id", required = true)
                                   @RequestParam(value = "planDetailId", required = true)String planDetailId){
        try {
            return rehabilitationManageService.serviceItem(planDetailId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.saveGuidanceMessage)
    @ApiOperation(value = "康复管理-保存指导留言")
    public Envelop saveGuidanceMessage(@ApiParam(name = "messageId", value = "消息id", required = true)
                                       @RequestParam(value = "messageId", required = true)String messageId,
//                                       @ApiParam(name = "patientCode", value = "居民code", required = true)
//                                       @RequestParam(value = "patientCode", required = true)String patientCode,
                                       @ApiParam(name = "doctorCode", value = "医生code", required = true)
                                       @RequestParam(value = "doctorCode", required = true)String doctorCode,
                                       @ApiParam(name = "doctorType", value = "医生类型（1、专科医生，2、家庭医生）", required = true)
                                       @RequestParam(value = "doctorType", required = true)Integer doctorType,
                                       @ApiParam(name = "content", value = "聊天内容", required = true)
                                       @RequestParam(value = "content", required = true)String content,
                                       @ApiParam(name = "planDetailId", value = "服务项目id", required = true)
                                       @RequestParam(value = "planDetailId", required = true)String planDetailId){
        try {
            return rehabilitationManageService.saveGuidanceMessage(messageId,doctorCode,doctorType,content,planDetailId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.updateStatusRehabilitationOperate)
    @ApiOperation(value = "康复管理-更新康复计划操作完成日志状态")
    public Envelop updateStatusRehabilitationOperate(@ApiParam(name = "planDetailId", value = "服务项目id", required = true)
                                                        @RequestParam(value = "planDetailId", required = true)String planDetailId,
                                                        @ApiParam(name = "status", value = "状态", required = true)
                                                        @RequestParam(value = "status", required = true)Integer status){
        try {
            return rehabilitationManageService.updateStatusRehabilitationOperate(status,planDetailId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.patientRehabilitationDetail)
    @ApiOperation(value = "康复管理-居民详情页")
    public Envelop patientRehabilitationDetail(@ApiParam(name = "patientCode", value = "居民code", required = true)
                                       @RequestParam(value = "patientCode", required = true)String patientCode,
                                       @ApiParam(name = "healthDoctor", value = "健管师医生code", required = true)
                                       @RequestParam(value = "healthDoctor", required = true)String healthDoctor,
                                       @ApiParam(name = "healthDoctorName", value = "健管师医生名称", required = true)
                                       @RequestParam(value = "healthDoctorName", required = true)String healthDoctorName,
                                       @ApiParam(name = "generalDoctor", value = "全科医生code", required = true)
                                       @RequestParam(value = "generalDoctor", required = true)String generalDoctor,
                                       @ApiParam(name = "generalDoctorName", value = "全科医生名称", required = true)
                                       @RequestParam(value = "generalDoctorName", required = true)String generalDoctorName){
        try {
            return rehabilitationManageService.patientRehabilitationDetail(patientCode,healthDoctor, healthDoctorName,generalDoctor,generalDoctorName);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.recentPlanDetailRecord)
    @ApiOperation(value = "居民康复计划详情页-近期康复相关记录")
    public Envelop recentPlanDetailRecord(
            @ApiParam(name = "patientCode", value = "居民code", required = true)
            @RequestParam(value = "patientCode", required = true)String patientCode,
            @ApiParam(name = "startTime", value = "开始时间（格式：yyyy-MM-dd HH:mm:ss）", required = false)
            @RequestParam(value = "startTime", required = false)String startTime,
            @ApiParam(name = "endTime", value = "结束时间（格式：yyyy-MM-dd HH:mm:ss）", required = false)
            @RequestParam(value = "endTime", required = false)String endTime,
            @ApiParam(name = "page", value = "第几页，从1开始", required = true)
            @RequestParam(value = "page", required = false,defaultValue = "1")Integer page,
            @ApiParam(name = "pageSize", value = "每页分页大小", required = true)
            @RequestParam(value = "pageSize", required = false,defaultValue = "10")Integer pageSize){
        try {
            return rehabilitationManageService.recentPlanDetailRecord(patientCode,startTime,endTime,page,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.serviceDoctorList)
    @ApiOperation(value = "康复管理-医生端居民详情服务医生列表")
    public Envelop serviceDoctorList(@ApiParam(name = "patientCode", value = "居民code", required = true)
                                               @RequestParam(value = "patientCode", required = true)String patientCode,
                                               @ApiParam(name = "healthDoctor", value = "健管师医生code", required = true)
                                               @RequestParam(value = "healthDoctor", required = true)String healthDoctor,
                                               @ApiParam(name = "healthDoctorName", value = "健管师医生名称", required = true)
                                               @RequestParam(value = "healthDoctorName", required = true)String healthDoctorName,
                                               @ApiParam(name = "generalDoctor", value = "全科医生code", required = true)
                                               @RequestParam(value = "generalDoctor", required = true)String generalDoctor,
                                               @ApiParam(name = "generalDoctorName", value = "全科医生名称", required = true)
                                               @RequestParam(value = "generalDoctorName", required = true)String generalDoctorName){
        try {
            return rehabilitationManageService.serviceDoctorList(patientCode,healthDoctor, healthDoctorName,generalDoctor,generalDoctorName);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.appCalendarPlanDetailList)
    @ApiOperation(value = "康复管理-app端、微信端计划的服务项目列表")
    public ObjEnvelop appCalendarPlanDetailList(@ApiParam(name = "executeStartTime", value = "日历开始时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                             @RequestParam(value = "executeStartTime", required = true)String executeStartTime,
                                             @ApiParam(name = "executeEndTime", value = "日历结束时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                             @RequestParam(value = "executeEndTime", required = true)String executeEndTime,
                                             @ApiParam(name = "planId", value = "计划id", required = true)
                                             @RequestParam(value = "planId", required = true)String planId,
                                             @ApiParam(name = "searchTask", value = "快速查找任务：（1、我的任务，2、随访，3、复诊，4、健康教育）", required = false)
                                             @RequestParam(value = "searchTask", required = false)Integer searchTask,
                                             @ApiParam(name = "status", value = "任务状态（0未完成，1已完成，2已预约）", required = false)
                                             @RequestParam(value = "status", required = false)Integer status){
        try {
            return rehabilitationManageService.appCalendarPlanDetailList(planId,searchTask,status,executeStartTime,executeEndTime);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = SpecialistMapping.rehabilitation.dailyJob)
    @ApiOperation(value = "每日康复服务通知")
    public ObjEnvelop dailyJob(@ApiParam(name = "startTime", value = "开始时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                @RequestParam(value = "startTime", required = true)String startTime,
                                @ApiParam(name = "endTime", value = "结束时间（格式：yyyy-MM-dd HH:mm:ss）", required = true)
                                @RequestParam(value = "endTime", required = true)String endTime){
        try {
            return rehabilitationManageService.dailyJob(startTime,endTime);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.updateNoteAndImageRehabilitationOperate)
    @ApiOperation(value = "康复计划完成时更新服务完成笔记和图片接口并且确认完成")
    public ObjEnvelop updateNoteAndImageRehabilitationOperate(@ApiParam(name = "planDetailId", value = "服务项目id", required = true)@RequestParam(value = "planDetailId", required = true)String planDetailId,
                                                           @ApiParam(name = "node", value = "服务完成笔记", required = true)@RequestParam(value = "node", required = false)String node,
                                                           @ApiParam(name = "image", value = "相关记录图片，json格式", required = true)@RequestParam(value = "image", required = false)String image){
        try {
            Map<String,Object> map = rehabilitationManageService.updateNodeAndRelationRecordImg(node,image,planDetailId);
            if(Integer.parseInt(String.valueOf(map.get("count")))>1){
                return ObjEnvelop.getSuccess(SpecialistMapping.api_success,map);
            }
            return ObjEnvelop.getError("update error!");
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return ObjEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = SpecialistMapping.rehabilitation.updatePlanDetailStatusById)
    @ApiOperation(value = "康复管理-更新康复计划项目状态")
    public Envelop updatePlanDetailStatusById(@ApiParam(name = "planDetailId", value = "服务项目id", required = true)
                                                     @RequestParam(value = "planDetailId", required = true)String planDetailId,
                                                     @ApiParam(name = "status", value = "状态", required = true)
                                                     @RequestParam(value = "status", required = true)Integer status){
        try {
            return rehabilitationManageService.updatePlanDetailStatusById(status,planDetailId);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }
}
