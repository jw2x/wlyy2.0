package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.entity.health.bank.ActivityDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangzhinan
 * @create 2018-04-27 14:14
 * @desc 健康活动
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康活动相关操作",description = "健康活动相关操作")
public class ActivityController extends EnvelopRestController{

    @Autowired
    private ActivityService service;
    @Autowired
    private Tracer tracer;


    /**
     *  publish activity
     *
     * @param activity 活动对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createActivity)
    @ApiOperation(value = "发布活动")
    public Envelop<Boolean> publishActivity(@ApiParam(name = "activity",value = "健康活动JSON")
                                          @RequestParam(value = "activity",required = true)String activity){
        try {
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.insert(activityDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    /**
     * find health activity
     *
     * @param activity 活动对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findActivity)
    @ApiOperation(value = "查看健康活动")
    public Envelop<ActivityDO> getActivity(@ApiParam(name = "activity",value = "健康活动JSON")
                                                    @RequestParam(value = "activity",required = false)String activity,
                                                @ApiParam(name = "page", value = "第几页，从1开始")
                                                @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                @RequestParam(value = "size", required = false)Integer size){
        try{
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.findByCondition(activityDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * out activity
     *
     * @param activity 活动对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateActivity)
    @ApiOperation(value = "下架活动")
    public Envelop<Boolean> outActivity(@ApiParam(name = "activity",value = "健康活动JSON")
                                            @RequestParam(value = "activity",required = true)String activity){
        try {
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.update(activityDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }
}
