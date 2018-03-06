package com.yihu.jw.controller.archives;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.archives.ArchivesContants;
import com.yihu.jw.fegin.archives.ArchivesFeign;
import com.yihu.jw.restmodel.archives.PatientArchivesInfoVO;
import com.yihu.jw.restmodel.archives.PatientArchivesVO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.archives.PatientArchivesMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Trick on 2018/2/12.
 */
@RestController
@RequestMapping(ArchivesContants.Common.archives)
@Api(tags = "居民建档相关操作", description = "居民建档相关操作")
public class ArchivesController {

    @Autowired
    private ArchivesFeign archivesFeign;

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchives)
    @ApiOperation(value = "分页查找档案列表", notes = "分页查找档案列表")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<PatientArchivesVO> findPatientArchives(@RequestParam(value = "name", required = false)String name,
                                                          @RequestParam(value = "status", required = false)String status,
                                                          @RequestParam(value = "cancelReseanType", required = false)String cancelReseanType,
                                                          @RequestParam(value = "page", required = false)Integer page,
                                                          @RequestParam(value = "size", required = false )Integer size){
        return archivesFeign.findPatientArchives(name,status,cancelReseanType,page,size);
    }

    @GetMapping(value = PatientArchivesMapping.Archives.findPatientArchivesInfos)
    @ApiOperation(value = "查询健康信息详情列表", notes = "查询健康信息详情列表")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<PatientArchivesInfoVO> queryPatientArchivesInfoPage(@RequestParam(value = "code", required = false)String code){
        return archivesFeign.queryPatientArchivesInfoPage(code);
    }

    @PostMapping(value = PatientArchivesMapping.Archives.createPatientArchives)
    @ApiOperation(value = "创建健康信息详情列表")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<Boolean> createPatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                  @RequestParam(value = "list", required = true)String list){
        return archivesFeign.createPatientArchives(patientArchives,list);
    }

    @PutMapping(value = PatientArchivesMapping.Archives.updatePatientArchives)
    @ApiOperation(value = "更新健康信息详情列表")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop<Boolean> updatePatientArchives(@RequestParam(value = "patientArchives", required = true)String patientArchives,
                                                  @RequestParam(value = "list", required = true)String list){
        return archivesFeign.updatePatientArchives(patientArchives,list);
    }
}
