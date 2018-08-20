package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.entity.health.bank.ActivityDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-04-27 14:14
 * @desc 健康活动
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康活动相关操作",description = "健康活动相关操作")
public class ActivityController extends EnvelopRestEndpoint {

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
    public MixEnvelop<Boolean, Boolean> publishActivity(@ApiParam(name = "activity",value = "健康活动JSON")
                                          @RequestParam(value = "activity",required = true)String activity){
        try {
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.insert(activityDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
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
    public MixEnvelop<ActivityDO, ActivityDO> getActivity(@ApiParam(name = "activity",value = "健康活动JSON")
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
            return MixEnvelop.getError(e.getMessage());
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
    public MixEnvelop<Boolean, Boolean> outActivity(@ApiParam(name = "activity",value = "健康活动JSON")
                                            @RequestParam(value = "activity",required = true)String activity){
        try {
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.update(activityDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 查看参与的活动
     *
     * @param activity 活动对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.selectByPatient)
    @ApiOperation(value = "参与的活动")
    public MixEnvelop<ActivityDO, ActivityDO> selectByPatient(@ApiParam(name = "activity",value = "健康活动JSON")
                                           @RequestParam(value = "activity",required = false)String activity,
                                                  @ApiParam(name = "page", value = "第几页，从1开始")
                                           @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                  @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                           @RequestParam(value = "size", required = false)Integer size){
        try{
            ActivityDO activityDO = toEntity(activity,ActivityDO.class);
            return service.selectByPatient(activityDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids id集合[""]
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.batchActivity)
    @ApiOperation(value = "批量删除活动")
    public MixEnvelop<Boolean, Boolean> batchDelete(@ApiParam(name="ids",value = "id集合")
                                        @RequestParam(value = "ids",required = false)String ids){
        try{
            MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
            JSONArray array = JSONArray.parseArray(ids);
            List<String> activityIds = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                activityIds.add(array.getString(i));
            }
            service.batchDelete(activityIds);
            envelop.setObj(true);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


}
