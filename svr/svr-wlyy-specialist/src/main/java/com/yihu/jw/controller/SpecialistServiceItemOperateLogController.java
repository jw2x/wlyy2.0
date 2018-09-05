package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/9/1.
 */

import com.yihu.jw.entity.specialist.SpecialistServiceItemOperateLogDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistServiceItemOperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhinan
 * @create 2018-09-01 15:15
 * @desc 操作记录
 **/


@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "服务项目操作记录相关操作", description = "服务项目操作记录相关操作")
public class SpecialistServiceItemOperateLogController extends EnvelopRestEndpoint {

    @Autowired
    private Tracer tracer;
    @Autowired
    private SpecialistServiceItemOperateLogService specialistServiceItemOperateLogService;

    /**
     * 查找服务项目操作记录
     *
     * @param operateLog
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectByOperate)
    @ApiOperation(value = "服务项目操作记录查询")
    public MixEnvelop<SpecialistServiceItemOperateLogDO,SpecialistServiceItemOperateLogDO> select(@ApiParam(name = "operateLog", value = "操作记录JSON")
                                                                           @RequestParam(value = "operateLog")String operateLog,
                                                                           @ApiParam(value = "当前页",name = "page")
                                                                           @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                                           @ApiParam(value = "显示记录数",name = "size",defaultValue = "10")
                                                                           @RequestParam(value = "size") Integer size){
        try {
            SpecialistServiceItemOperateLogDO specialistServiceItemOperateLogDO = toEntity(operateLog,SpecialistServiceItemOperateLogDO.class);
            return specialistServiceItemOperateLogService.select(specialistServiceItemOperateLogDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
