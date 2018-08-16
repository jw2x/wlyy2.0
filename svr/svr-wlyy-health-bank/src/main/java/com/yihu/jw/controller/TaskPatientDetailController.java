package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/6/11.
 */

import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskPatientDtailService;
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
 * @create 2018-06-11 10:37
 * @desc 活动参与表
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务参与详情",description = "健康任务参与详情")
public class TaskPatientDetailController extends EnvelopRestEndpoint {
    @Autowired
    private TaskPatientDtailService service;
    @Autowired
    private Tracer tracer;


    /**
     * 参与活动
     *
     * @param taskPatientDetail 参与活动详情对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.attendTask)
    @ApiOperation(value = "参与活动")
    public MixEnvelop<Boolean, Boolean> insert(@ApiParam(name = "taskPatientDetail",value = "参与活动JSON")
                                   @RequestParam(value = "taskPatientDetail",required = true)String taskPatientDetail){
        try {
            TaskPatientDetailDO taskPatientDetailDO = toEntity(taskPatientDetail, TaskPatientDetailDO.class);
            return service.insert(taskPatientDetailDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


}
