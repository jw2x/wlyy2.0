package com.yihu.rehabilitation.controller;

import com.yihu.jw.entity.rehabilitation.RehabilitationPerformanceDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.rehabilitation.RehabilitationPerformanceVO;
import com.yihu.jw.rm.rehabilitation.RehabilitationRequestMapping;
import com.yihu.rehabilitation.service.RehabilitationPerformanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author humingfen on 2018/5/2.
 */
@RestController
@RequestMapping(value = RehabilitationRequestMapping.Performance.performance)
@Api(tags = "康复计划执行情况相关操作", description = "康复计划执行情况相关操作")
public class RehabilitationPerformanceController extends EnvelopRestController {
    @Autowired
    private RehabilitationPerformanceService performanceService;

    @GetMapping(value = RehabilitationRequestMapping.Performance.findPerformancePage)
    @ApiOperation(value = "分页查找康复计划执行情况", notes = "分页查找康复计划执行情况")
    public Envelop<RehabilitationPerformanceVO> findPerformancePage(@ApiParam(name = "patientId", value = "居民id", defaultValue = "")
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
            return performanceService.queryPerformancePage(page, size, patientId);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Performance.api_create)
    @ApiOperation(value = "创建康复计划执行情况", notes = "创建康复计划执行情况")
    public Envelop<RehabilitationPerformanceDO> createPerformance(@ApiParam(name = "jsonData", value = "基本信息Json", defaultValue = "")
                                                            @RequestBody String jsonData) {
        try {
            RehabilitationPerformanceDO performanceDO = toEntity(jsonData, RehabilitationPerformanceDO.class);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_create, performanceService.create(performanceDO));
        }catch (Exception e){
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Performance.findPerformanceById)
    @ApiOperation(value = "根据id查找康复计划执行情况", notes = "根据id查找康复计划执行情况")
    public Envelop<RehabilitationPerformanceDO> findById(@ApiParam(name = "id", value = "id")
                                                      @RequestParam(value = "id", required = true) String id) {
        try {
            RehabilitationPerformanceDO performanceDO = performanceService.findById(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, performanceDO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @GetMapping(value = RehabilitationRequestMapping.Performance.findPerformanceByPatientId)
    @ApiOperation(value = "根据patientId查找康复计划执行情况", notes = "根据patientId查找康复计划执行情况")
    public Envelop<RehabilitationPerformanceDO> findByPatientId(@ApiParam(name = "patientId", value = "patientId")
                                                         @RequestParam(value = "patientId", required = true) String patientId) {
        try {
            List<RehabilitationPerformanceDO> performanceDO = performanceService.findByPatientId(patientId);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_find, performanceDO);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Performance.api_update)
    @ApiOperation(value = "修改康复计划执行情况", notes = "修改康复计划执行情况(记得传入修改id)")
    public Envelop updatePerformance(@ApiParam(name = "jsonData", value = "json", defaultValue = "")
                                  @RequestBody String jsonData) {
        try {
            RehabilitationPerformanceDO PerformanceDO = toEntity(jsonData, RehabilitationPerformanceDO.class);
            performanceService.update(PerformanceDO);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_update);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = RehabilitationRequestMapping.Performance.api_delete)
    @ApiOperation(value = "删除康复计划执行情况", notes = "删除康复计划执行情况")
    public Envelop delPerformance(@ApiParam(name = "id", value = "id")
                               @RequestParam(value = "id", required = true) String id) {
        try {
            performanceService.delete(id);
            return Envelop.getSuccess(RehabilitationRequestMapping.Common.message_success_delete);
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError(e.getMessage());
        }
    }
}
