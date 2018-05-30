package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ActivityRuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhinan
 * @create 2018-04-27 15:24
 * @desc health activity info controller
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康活动参与情况相关操作",description = "健康活动参与情况相关操作")
public class ActivityDetailController extends EnvelopRestController {

    @Autowired
    private ActivityRuleService service;
    @Autowired
    private Tracer tracer;

/*
    *//**
     * patient attend activity
     *
     * @param activityInfo
     * @return
     *//*
    @PostMapping(value = HealthBankMapping.healthBank.createActivityInfo)
    @ApiOperation(value = "参与活动")
    public Envelop<Boolean> attendActivity(@ApiParam(name = "activityInfo",value = "健康活动详情JSON")
                                            @RequestParam(value = "activityInfo",required = true)String activityInfo){
        try {
            ActivityRuleDO activityRuleDO = toEntity(activityInfo,ActivityRuleDO.class);
            return service.insert(activityRuleDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    *//**
     * find health activity
     *
     * @param activityInfo
     * @param page
     * @param size
     * @return
     *//*
    @PostMapping(value = HealthBankMapping.healthBank.findActivityInfo)
    @ApiOperation(value = "查看健康活动")
    public Envelop<ActivityRuleDO> getActivityInfo(@ApiParam(name = "activityInfo",value = "健康活动JSON")
                                                @RequestParam(value = "activityInfo",required = false)String activityInfo,
                                                   @ApiParam(name = "page", value = "第几页，从1开始")
                                                @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                   @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                @RequestParam(value = "size", required = false)Integer size){
        try{
            ActivityRuleDO activityRuleDO = toEntity(activityInfo,ActivityRuleDO.class);
            return service.findByCondition(activityRuleDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    *//**
     * patient update activity status
     *
     * @param activityInfo
     * @return
     *//*
    @PostMapping(value = HealthBankMapping.healthBank.updateActivityInfo)
    @ApiOperation(value = "更新活动状态")
    public Envelop<Boolean> updateActivity(@ApiParam(name = "activityInfo",value = "健康活动JSON")
                                        @RequestParam(value = "activityInfo",required = true)String activityInfo){
        try {
            ActivityRuleDO activityRuleDO = toEntity(activityInfo,ActivityRuleDO.class);
            return service.update(activityRuleDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }*/
}
