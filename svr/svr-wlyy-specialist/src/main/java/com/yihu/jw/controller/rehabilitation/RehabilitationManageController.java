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

    @GetMapping(value = SpecialistMapping.rehabilitation.findRehabilitationPlan)
    @ApiOperation(value = "康复管理列表")
    public MixEnvelop findRehabilitationPlan(@ApiParam(name = "page", value = "第几页，从1开始")
                                             @RequestParam(value = "page", required = false)Integer page,
                                             @ApiParam(name = "size", value = "，每页分页大小")
                                             @RequestParam(value = "size", required = false)Integer size){
        try {
            return null;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
