package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.yihu.jw.entity.health.bank.CreditsDetailDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.CreditsDetailService;
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
 * @create 2018-04-27 16:51
 * @desc credits log info
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康积分相关操作",description = "健康积分相关操作")
public class CreditsDetailController extends EnvelopRestController {

    @Autowired
    private Tracer tracer;
    @Autowired
    private CreditsDetailService service;

    /**
     * 查看积分记录
     *
     * @param creditsDetail 积分对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findCreditsLogInfo)
    @ApiOperation(value = "查看积分记录")
    public Envelop<CreditsDetailDO> selectCreditsLogInfo(@ApiParam(name = "creditsDetail",value = "积分记录JSON")
                                                          @RequestParam(value = "creditsDetail",required = false)String creditsDetail,
                                                         @ApiParam(name = "page", value = "第几页，从1开始")
                                                          @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                         @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                          @RequestParam(value = "size", required = false)Integer size){
        try{
            CreditsDetailDO creditsDetailDO = toEntity(creditsDetail,CreditsDetailDO.class);
            return service.findByCondition(creditsDetailDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }



    /**
     * 添加积分
     *
     * @param creditsDetail 积分对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createCreditsDetail)
    @ApiOperation(value = "添加积分记录")
    public Envelop<CreditsDetailDO> insert(@ApiParam(name = "creditsDetail",value = "积分记录JSON")
                                   @RequestParam(value = "creditsDetail",required = true)String creditsDetail){
        try {
            CreditsDetailDO creditsDetailDO = toEntity(creditsDetail,CreditsDetailDO.class);
            return service.insert(creditsDetailDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 根据活动查找积分
     *
     * @param activityId 活动id
     *
     * @param patientId 居民id
     *
     * @param page 页码
     *
     * @param size 分页大小
     *
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.selectByActivity)
    @ApiOperation(value = "根据活动查找积分")
    public Envelop<CreditsDetailDO> seletcByActivity(@ApiParam(name = "activityId",value = "活动id")
                                                         @RequestParam(value = "activityId",required = true)String activityId,
                                                         @ApiParam(name = "patientId",value = "居民id")
                                                         @RequestParam(value = "patientId",required = true) String patientId,
                                                         @ApiParam(name = "page", value = "第几页，从1开始")
                                                         @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                         @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                         @RequestParam(value = "size", required = false)Integer size){
        try{
            return service.selectByActivity(activityId,patientId,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    /**
     * 查看积分排行
     *
     * @param object {"filter":[""],"page":"","size":""}
     * @return
     */
  /*  @PostMapping(value = HealthBankMapping.healthBank.selectByRanking)
    @ApiOperation(value = "查询积分排名")
    public Envelop<AccountDO> selectByRanking(@RequestBody JSONObject object){
        try{
            JSONArray array = object.getJSONArray("filter");
            Integer page = object.getInteger("page");
            Integer size = object.getInteger("size");
            List<String> patientIds = new ArrayList<>();
            for (int i=0;array != null && array.size()!=0&& i<array.size();i++){
                patientIds.add(array.getString(i));
            }
            return service.selectByRanking(patientIds,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }*/


}
