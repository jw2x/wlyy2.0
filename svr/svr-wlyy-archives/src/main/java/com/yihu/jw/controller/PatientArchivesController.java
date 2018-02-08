package com.yihu.jw.controller;

import com.alibaba.fastjson.JSON;
import com.yihu.jw.entity.archives.PatientArchives;
import com.yihu.jw.entity.archives.PatientArchivesInfo;
import com.yihu.jw.iot.device.IotDeviceQualityInspectionPlanDO;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.archives.Test;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.jw.service.PatientArchivesSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trick on 2018/2/7.
 */
@RestController
@RequestMapping(PatientArchivesMapping.api_archives_common)
@Api(tags = "居民建档相关操作", description = "居民建档相关操作")
public class PatientArchivesController extends EnvelopRestController {
    @Autowired
    private PatientArchivesSevice patientArchivesSevice;

    @GetMapping(value = "test")
    @ApiOperation(value = "测试")
    public Test test(){
        Test t = new Test();
        t.setFileName("11");
        t.setFileType("1");
        return t;
    }

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchives)
    @ApiOperation(value = "查询健康信息列表")
    public Envelop<PatientArchivesVO> findPatientArchives(@ApiParam(name = "name", value = "姓名（模糊匹配）")
                                                          @RequestParam(value = "name", required = false)String name,
                                                          @ApiParam(name = "status", value = "档案状态")
                                                          @RequestParam(value = "status", required = false)String status,
                                                          @ApiParam(name = "cancelReseanType", value = "注销状态")
                                                          @RequestParam(value = "cancelReseanType", required = false)String cancelReseanType,
                                                          @ApiParam(name = "page", value = "分页") @RequestParam(value = "page", required = false)Integer page,
                                                          @ApiParam(name = "size", value = "每一页大小")@RequestParam(value = "size", required = false )Integer size){
        try {
            return patientArchivesSevice.queryPatientArchivesPage(page,size,status, cancelReseanType ,name);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchivesInfos)
    @ApiOperation(value = "查询健康信息详情列表")
    public Envelop<PatientArchivesInfoVO> queryPatientArchivesInfoPage(@ApiParam(name = "code", value = "档案编号")
                                                                           @RequestParam(value = "code", required = false)String code){
        try {
            return patientArchivesSevice.queryPatientArchivesInfoPage(code);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
    @GetMapping(value = PatientArchivesMapping.Archives.createPatientArchives)
    @ApiOperation(value = "创建健康信息详情列表")
    public Envelop<Boolean> createPatientArchives(@ApiParam(name = "patientArchives", value = "建档基本信息Json")
                                                  @RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                  @ApiParam(name = "list", value = "建档详情")
                                                  @RequestParam(value = "list", required = true)List<PatientArchivesInfoVO> list){
        try {
            PatientArchives ps = toEntity(patientArchives, PatientArchives.class);
            List<PatientArchivesInfo> infos = new ArrayList<>();
            convertToModels(list,infos,PatientArchivesInfo.class);
            return patientArchivesSevice.createPatientArchives(ps,infos);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = PatientArchivesMapping.Archives.updatePatientArchives)
    @ApiOperation(value = "更新健康信息详情列表")
    public Envelop<Boolean> updatePatientArchives(@ApiParam(name = "patientArchives", value = "建档基本信息Json")
                                                  @RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                  @ApiParam(name = "list", value = "建档详情")
                                                  @RequestParam(value = "list", required = true)List<PatientArchivesInfoVO> list){
        try {
            PatientArchives ps = toEntity(patientArchives, PatientArchives.class);
            List<PatientArchivesInfo> infos = new ArrayList<>();
            convertToModels(list,infos,PatientArchivesInfo.class);
            return patientArchivesSevice.updatePatientArchives(ps,infos);
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
