package com.yihu.jw.controller;

import com.yihu.jw.entity.health.bank.ActiveRecordDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.ActiveRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘文彬 on 2018/6/12.
 */
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康银行活动活跃记录",description = "健康银行活动活跃记录")
public class ActiveRecordController extends EnvelopRestController {
    @Autowired
    private ActiveRecordService activeRecordService;

    @PostMapping(value = HealthBankMapping.healthBank.createActiveRecord)
    @ApiOperation(value = "添加健康银行活动活跃记录")
    public Envelop<ActiveRecordDO> addActiveRecord(@ApiParam(name = "saasId",value = "saasId")@RequestParam(value = "saasId",required = true)String saasId,
                                                   @ApiParam(name = "taskId",value = "任务id")@RequestParam(value = "taskId",required = false)String taskId,
                                                   @ApiParam(name = "activityId",value = "账户JSON")@RequestParam(value = "activityId",required = false)String activityId,
                                                   @ApiParam(name = "originalStatus",value = "原有状态")@RequestParam(value = "originalStatus",required = true)Integer originalStatus,
                                                   @ApiParam(name = "currentStatus",value = "当前状态")@RequestParam(value = "currentStatus",required = true)Integer currentStatus,
                                                   @ApiParam(name = "patientId",value = "居民code")@RequestParam(value = "patientId",required = true)String patientId){
        try{
            ActiveRecordDO activeRecordDO = activeRecordService.insert(saasId,taskId,activityId,originalStatus,currentStatus,patientId);
            if(activeRecordDO!=null){
                return Envelop.getSuccess("添加成功",activeRecordDO);
            }else{
                return Envelop.getError("添加失败");
            }
        }catch (Exception e){
            return Envelop.getError("添加失败");
        }
    }
}
