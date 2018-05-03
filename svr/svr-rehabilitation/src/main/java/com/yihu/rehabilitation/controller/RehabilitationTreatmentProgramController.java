package com.yihu.rehabilitation.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.rehabilitation.RehabilitationTreatmentProgramDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationTreatmentProgramVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.rehabilitation.service.RehabilitationTreatmentProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author humingfen on 2018/4/27.
 */
@RestController
@RequestMapping(value = RehabilitationRequestMapping.TreatmentProgram.treatmentProgram)
@Api(tags = "治疗方案相关操作", description = "治疗方案相关操作")
public class RehabilitationTreatmentProgramController extends EnvelopRestController {

    @Autowired
    private RehabilitationTreatmentProgramService treatmentProgramService;

    @GetMapping(value = RehabilitationRequestMapping.TreatmentProgram.findTreatmentProgramPage)
    @ApiOperation(value = "分页查找治疗方案", notes = "分页查找治疗方案")
    public Envelop<RehabilitationTreatmentProgramVO> findTreatmentProgramPage(@ApiParam(name = "name", value = "方案名称", defaultValue = "")
                                                                    @RequestParam(value = "name", required = false) String name,
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
            return treatmentProgramService.queryTreatmentPage(page, size, name);

        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.TreatmentProgram.api_create)
    @ApiOperation(value = "创建治疗方案", notes = "创建治疗方案")
    public Envelop<RehabilitationTreatmentProgramDO> createTreatmentProgram(@ApiParam(name = "jsonData", value = "方案基本信息Json", defaultValue = "")
                                                                         @RequestParam(value = "jsonData", required = false) String jsonData) {
        try {
            RehabilitationTreatmentProgramDO infos = new ObjectMapper().readValue(jsonData, new TypeReference<RehabilitationTreatmentProgramDO>(){});
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_create, treatmentProgramService.create(infos));
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.TreatmentProgram.findTreatmentProgramById)
    @ApiOperation(value = "根据id查找治疗方案", notes = "根据id查找治疗方案")
    public Envelop<RehabilitationTreatmentProgramDO> findById(@ApiParam(name = "id", value = "id")
                                                         @RequestParam(value = "id", required = true) String id) {
        try {
            RehabilitationTreatmentProgramDO treatmentProgramDO = treatmentProgramService.findById(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, treatmentProgramDO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.TreatmentProgram.api_update)
    @ApiOperation(value = "修改治疗方案", notes = "修改治疗方案(记得传入修改id)")
    public Envelop updateTreatmentProgram(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                                                  @RequestParam(value = "jsonData", required = true)String jsonData) {
        try {
            RehabilitationTreatmentProgramDO treatmentProgramDO = toEntity(jsonData, RehabilitationTreatmentProgramDO.class);
            treatmentProgramService.update(treatmentProgramDO);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_update);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.TreatmentProgram.api_delete)
    @ApiOperation(value = "删除治疗方案", notes = "删除治疗方案")
    public Envelop delTreatmentProgram(@ApiParam(name = "id", value = "id")
                                                               @RequestParam(value = "id", required = true) String id) {
        try {
            treatmentProgramService.delete(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_delete);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
