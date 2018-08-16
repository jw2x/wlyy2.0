package com.yihu.health.controller.archives;


import com.yihu.health.service.archives.ArchivesService;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Trick on 2018/2/22.
 */
@RestController
@RequestMapping(PatientArchivesMapping.api_archives_common)
@Api(tags = "居民建档相关操作", description = "居民建档相关操作")
public class ArchivesController {

    @Autowired
    private ArchivesService archivesService;

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchives)
    @ApiOperation(value = "查询健康信息列表")
    public MixEnvelop<PatientArchivesVO, PatientArchivesVO> findPatientArchives(@ApiParam(name = "name", value = "姓名（模糊匹配）")
                                                          @RequestParam(value = "name", required = false)String name,
                                                             @ApiParam(name = "status", value = "档案状态")
                                                          @RequestParam(value = "status", required = false)String status,
                                                             @ApiParam(name = "cancelReseanType", value = "注销状态")
                                                          @RequestParam(value = "cancelReseanType", required = false)String cancelReseanType,
                                                             @ApiParam(name = "page", value = "分页") @RequestParam(value = "page", required = false)Integer page,
                                                             @ApiParam(name = "size", value = "每一页大小")@RequestParam(value = "size", required = false )Integer size){
        try {
            return archivesService.queryPatientArchivesPage(page,size,status, cancelReseanType ,name);
        }catch (Exception e){
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchivesInfos)
    @ApiOperation(value = "查询健康信息详情列表")
    public MixEnvelop<PatientArchivesInfoVO, PatientArchivesInfoVO> queryPatientArchivesInfoPage(@ApiParam(name = "code", value = "档案编号")
                                                                       @RequestParam(value = "code", required = false)String code){
        try {
            return archivesService.queryPatientArchivesInfoPage(code);
        }catch (Exception e){
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }
    @PostMapping(value = PatientArchivesMapping.Archives.createPatientArchives)
    @ApiOperation(value = "创建健康信息详情列表")
    public MixEnvelop<Boolean, Boolean> createPatientArchives(@ApiParam(name = "patientArchives", value = "建档基本信息Json")
                                                  @RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                     @ApiParam(name = "list", value = "建档详情Json")
                                                  @RequestParam(value = "list", required = true)String list){
        try {

            return archivesService.createPatientArchives(patientArchives,list);
        }catch (Exception e){
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PutMapping(value = PatientArchivesMapping.Archives.updatePatientArchives)
    @ApiOperation(value = "更新健康信息详情列表")
    public MixEnvelop<Boolean, Boolean> updatePatientArchives(@ApiParam(name = "patientArchives", value = "建档基本信息Json")
                                                  @RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                     @ApiParam(name = "list", value = "建档详情")
                                                  @RequestParam(value = "list", required = true)String list){
        try {
            return archivesService.updatePatientArchives(patientArchives,list);
        }catch (Exception e){
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
