package com.yihu.jw.controller;

import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.entity.health.bank.ActiveRecordDO;
import com.yihu.jw.entity.health.bank.TaskPatientDetailDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ActiveRecordService;
import com.yihu.jw.service.TaskPatientDtailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘文彬 on 2018/6/12.
 */
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康银行活动活跃记录",description = "健康银行活动活跃记录")
public class ActiveRecordController extends EnvelopRestEndpoint {
    @Autowired
    private ActiveRecordService activeRecordService;
    @Autowired
    private TaskPatientDtailService taskPatientDtailService;
    @Autowired
    private Tracer tracer;

    @PostMapping(value = HealthBankMapping.healthBank.createActiveRecord)
    @ApiOperation(value = "添加健康银行活动活跃记录")
    public MixEnvelop<ActiveRecordDO, ActiveRecordDO> addActiveRecord(@RequestBody JSONObject object){
        try{
            String openId = object.getString("patientOpenid");
            String idCard = object.getString("patientIdcard");
            String unionId = object.getString("unionId");
            String taskCode = object.getString("taskCode");
            Integer originalStatus = object.getInteger("originalStatus");
            Integer currentStatus = object.getInteger("currentStatus");
            String patientId = object.getString("patientId");
            TaskPatientDetailDO taskPatientDetailDO = taskPatientDtailService.selectByPatientId(openId,idCard,unionId,taskCode);
            if (taskPatientDetailDO == null){
                return MixEnvelop.getError("尚未报名！");
            }else {
                ActiveRecordDO activeRecordDO = activeRecordService.insert(taskPatientDetailDO.getSaasId(),taskPatientDetailDO.getTaskId(),taskPatientDetailDO.getActivityId(),originalStatus,currentStatus,patientId);
                if(activeRecordDO!=null){
                    return MixEnvelop.getSuccess("添加成功",activeRecordDO);
                }else{
                    return MixEnvelop.getError("添加失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError("添加失败");
        }
    }
}
