package com.yihu.rehabilitation.controller;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.rehabilitation.RehabilitationInformationDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationInformationVO;
import com.yihu.jw.rm.iot.IotRequestMapping;
import com.yihu.rehabilitation.service.RehabilitationInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;

/**
 * @author humingfen on 2018/4/25.
 */
@RestController
@RequestMapping(value = RehabilitationRequestMapping.Common.information)
@Api(tags = "就诊信息相关操作", description = "就诊信息相关操作")
public class RehabilitationInformationController extends EnvelopRestController {

    @Autowired
    private RehabilitationInformationService rehabilitationInformationService;

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationPage)
    @ApiOperation(value = "分页查找就诊信息", notes = "分页查找就诊信息")
    public Envelop<RehabilitationInformationVO> findCompanyPage(@ApiParam(name = "hospital", value = "就诊医院名称", defaultValue = "")
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
            RehabilitationInformationVO informationVO = toEntity(jsonData, RehabilitationInformationVO.class);
            RehabilitationInformationDO informationDO = rehabilitationInformationService.convertToModel(informationVO, RehabilitationInformationDO.class);
            return Envelop.getSuccess(RehabilitationRequestMapping.Information.message_success_create, rehabilitationInformationService.create(informationDO));
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationById)
    @ApiOperation(value = "根据id查找就诊信息", notes = "根据id查找就诊信息")
    public Envelop<RehabilitationInformationVO> findByCode(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            RehabilitationInformationDO informationDO = rehabilitationInformationService.findById(id);
            RehabilitationInformationVO informationVO = rehabilitationInformationService.convertToModel(informationDO, RehabilitationInformationVO.class);
            return Envelop.getSuccess(RehabilitationRequestMapping.Information.message_success_find, informationVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Information.findInformationById)
    @ApiOperation(value = "根据patientId查找就诊信息", notes = "根据patientId查找就诊信息")
    public Envelop<RehabilitationInformationVO> findByPatientId(@ApiParam(name = "patientId", value = "patientId")
                                                           @RequestParam(value = "patientId", required = true) String patientId) {
        try {
            RehabilitationInformationDO informationDO = rehabilitationInformationService.findByPatientId(patientId);
            RehabilitationInformationVO informationVO = rehabilitationInformationService.convertToModel(informationDO, RehabilitationInformationVO.class);
            return Envelop.getSuccess(RehabilitationRequestMapping.Information.message_success_find, informationVO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Information.api_update)
    @ApiOperation(value = "修改就诊信息", notes = "修改就诊信息")
    public Envelop<RehabilitationInformationVO> updCompany(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                            @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            RehabilitationInformationVO informationVO = toEntity(jsonData, RehabilitationInformationVO.class);
            RehabilitationInformationDO informationDO = rehabilitationInformationService.convertToModel(informationVO, RehabilitationInformationDO.class);
            rehabilitationInformationService.update(informationDO);
            return Envelop.getSuccess(RehabilitationRequestMapping.Information.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Information.api_delete)
    @ApiOperation(value = "删除企业", notes = "删除企业")
    public Envelop<RehabilitationInformationVO> delCompany(@ApiParam(name = "id", value = "id")
                                            @RequestParam(value = "id", required = true) String id) {
        try {
            rehabilitationInformationService.delete(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Information.message_success_find);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
