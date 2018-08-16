package com.yihu.rehabilitation.controller;

import com.yihu.jw.entity.rehabilitation.RehabilitationPlanningDO;
import com.yihu.jw.entity.rehabilitation.RehabilitationTreatmentProgramDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationPlanningVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.rehabilitation.service.RehabilitationPlanningService;
import com.yihu.rehabilitation.service.RehabilitationTreatmentProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author humingfen on 2018/5/2.
 */
@RestController
@RequestMapping(value = RehabilitationRequestMapping.Planning.planning)
@Api(tags = "康复计划相关操作", description = "康复计划相关操作")
public class RehabilitationPlanningController extends EnvelopRestEndpoint {
    @Autowired
    private RehabilitationPlanningService planningService;
    @Autowired
    private RehabilitationTreatmentProgramService treatmentProgramService;

    @GetMapping(value = RehabilitationRequestMapping.Planning.findPlanningPage)
    @ApiOperation(value = "分页查找康复计划", notes = "分页查找康复计划")
    public MixEnvelop<RehabilitationPlanningVO, RehabilitationPlanningVO> findPlanningPage(@ApiParam(name = "patientId", value = "居民id", defaultValue = "")
                                                                              @RequestParam(value = "patientId", required = false) String patientId,
                                                                 @ApiParam(name = "programId", value = "康复计划id", defaultValue = "")
                                                                              @RequestParam(value = "programId", required = false) String programId,
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
            return planningService.queryPlanningPage(page, size, patientId, programId);

        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Planning.api_create)
    @ApiOperation(value = "创建康复计划", notes = "创建康复计划")
    public MixEnvelop<RehabilitationPlanningDO, RehabilitationPlanningDO> createPlanning(@ApiParam(name = "jsonData", value = "基本信息Json", defaultValue = "")
                                                                @RequestBody String jsonData) {
        try {
            RehabilitationPlanningDO planningDO = toEntity(jsonData, RehabilitationPlanningDO.class);
            return MixEnvelop.getSuccess(RehabilitationRequestMapping.Common.message_success_create, planningService.create(planningDO));
        }catch (Exception e){
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Planning.findPlanningById)
    @ApiOperation(value = "根据id查找治疗方案", notes = "根据id查找治疗方案")
    public MixEnvelop<RehabilitationPlanningDO, RehabilitationPlanningDO> findById(@ApiParam(name = "id", value = "id")
                                                              @RequestParam(value = "id", required = true) String id) {
        try {
            RehabilitationPlanningDO planningDO = planningService.findById(id);
            return MixEnvelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, planningDO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Planning.findTreatmentByProgramId)
    @ApiOperation(value = "根据programId查找治疗方案", notes = "根据programId查找治疗方案")
    public MixEnvelop<RehabilitationPlanningDO, RehabilitationPlanningDO> findTreatmentByProgramId(@ApiParam(name = "programId", value = "programId")
                                                      @RequestParam(value = "programId", required = true) String programId) {
        try {
            RehabilitationTreatmentProgramDO treatmentProgramDO = treatmentProgramService.findById(programId);
            return MixEnvelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, treatmentProgramDO);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Planning.api_update)
    @ApiOperation(value = "修改治疗方案", notes = "修改治疗方案(记得传入修改id)")
    public MixEnvelop updatePlanning(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                      @RequestBody String jsonData) {
        try {
            RehabilitationPlanningDO planningDO = toEntity(jsonData, RehabilitationPlanningDO.class);
            planningService.update(planningDO);
            return MixEnvelop.getSuccess(RehabilitationRequestMapping.Common.message_success_update);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Planning.api_delete)
    @ApiOperation(value = "删除治疗方案", notes = "删除治疗方案")
    public MixEnvelop delPlanning(@ApiParam(name = "id", value = "id")
                                       @RequestParam(value = "id", required = true) String id) {
        try {
            planningService.delete(id);
            return MixEnvelop.getSuccess(RehabilitationRequestMapping.Common.message_success_delete);
        } catch (Exception e) {
            e.printStackTrace();
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
