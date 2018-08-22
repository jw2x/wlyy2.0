package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.health.bank.TaskGoodsDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskGoodsService;
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
 * @create 2018-06-08 16:52
 * @desc 任务商品
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务商品相关操作",description = "健康任务商品相关操作")
public class TaskGoodsController extends EnvelopRestEndpoint {
    @Autowired
    private TaskGoodsService service;
    @Autowired
    private Tracer tracer;


    /**
     * 更新任务商品
     *
     * @param taskGoods 任务商品对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createTaskGoods)
    @ApiOperation(value = "添加任务商品")
    public MixEnvelop<Boolean, Boolean> insert(@ApiParam(name = "taskGoods",value = "任务商品JSON")
                                   @RequestParam(value = "taskGoods",required = true)String taskGoods){
        try {
            TaskGoodsDO taskGoodsDO = toEntity(taskGoods,TaskGoodsDO.class);
            return service.insert(taskGoodsDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 更新任务商品
     *
     * @param taskGoods 任务商品
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateTaskGoods)
    @ApiOperation(value = "更新任务商品")
    public MixEnvelop<Boolean, Boolean> update(@ApiParam(name = "taskGoods",value = "任务商品JSON")
                                   @RequestParam(value = "taskGoods",required = true)String taskGoods){
        try {
            TaskGoodsDO taskGoodsDO = toEntity(taskGoods,TaskGoodsDO.class);
            return service.update(taskGoodsDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 查询任务商品
     *
     * @param taskGoods 任务商品对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findTaskGoods)
    @ApiOperation(value = "查询任务商品")
    public MixEnvelop<TaskGoodsDO, TaskGoodsDO> select(@ApiParam(name = "taskGoods",value = "任务商品JSON")
                                      @RequestParam(value = "taskGoods",required = true)String taskGoods,
                                          @ApiParam(name = "page", value = "第几页，从1开始")
                                      @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                          @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                      @RequestParam(value = "size", required = false)Integer size){
        try {
            TaskGoodsDO taskGoodsDO = toEntity(taskGoods,TaskGoodsDO.class);
            return service.selectByCondition(taskGoodsDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
