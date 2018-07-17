package com.yihu.rehabilitation.controller;

import com.yihu.jw.rehabilitation.RehabilitationInformationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationInformationVO;
import com.yihu.rehabilitation.service.RehabilitationInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;

import java.util.List;

/**
 * @author humingfen on 2018/4/25.
 */
@RestController
@RequestMapping(value = RehabilitationRequestMapping.Information.information)
@Api(tags = "就诊信息相关操作", description = "就诊信息相关操作")
public class RehabilitationInformationController extends EnvelopRestController {

    @Autowired
    private RehabilitationInformationService rehabilitationInformationService;

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationPage)
    @ApiOperation(value = "分页查找就诊信息", notes = "分页查找就诊信息")
    public Envelop<RehabilitationInformationVO> findInformationPage(@ApiParam(name = "hospital", value = "就诊医院名称", defaultValue = "")
                                                 @RequestParam(value = "hospital", required = false) String hospital,
                                                 @ApiParam(name = "patientId", value = "居民id", defaultValue = "")
                                                 @RequestParam(value = "patientId", required = false) String patientId,
                                                 @ApiParam(name = "page", value = "第几页", defaultValue = "")
                                                 @RequestParam(value = "page", required = false) Integer page,
                                                 @ApiParam(name = "size", value = "每页记录数", defaultValue = "")
                                                 @RequestParam(value = "size", required = false) Integer size){
        try {
            if(page == null|| page < 0){
                page = 1;
            }
            if(size == null){
                size = 10;
            }
            return rehabilitationInformationService.queryPage(page, size, patientId, hospital);

        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
    
    @PostMapping(value = RehabilitationRequestMapping.Information.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建就诊信息", notes = "创建就诊信息")
    public Envelop<RehabilitationInformationVO> create(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                                           @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            RehabilitationInformationDO informationDO = toEntity(jsonData, RehabilitationInformationDO.class);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_create, rehabilitationInformationService.create(informationDO));
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationById)
    @ApiOperation(value = "根据id查找就诊信息", notes = "根据id查找就诊信息")
    public Envelop<RehabilitationInformationDO> findById(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            RehabilitationInformationDO informationDO = rehabilitationInformationService.findById(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, informationDO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationByPatientId)
    @ApiOperation(value = "根据patientId查找就诊信息", notes = "根据patientId查找就诊信息")
    public Envelop<RehabilitationInformationDO> findByPatientId(@ApiParam(name = "patientId", value = "patientId")
                                                           @RequestParam(value = "patientId", required = true) String patientId) {
        try {
            List<RehabilitationInformationDO> informationDO = rehabilitationInformationService.findByPatientId(patientId);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, informationDO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Information.api_update)
    @ApiOperation(value = "修改就诊信息", notes = "修改就诊信息(记得传入修改id)")
    public Envelop<RehabilitationInformationVO> updateInformation(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            RehabilitationInformationDO informationDO = toEntity(jsonData, RehabilitationInformationDO.class);
            rehabilitationInformationService.update(informationDO);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_update);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Information.api_delete)
    @ApiOperation(value = "删除就诊信息", notes = "删除就诊信息")
    public Envelop<RehabilitationInformationVO> delInformation(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            rehabilitationInformationService.delete(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_delete);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
