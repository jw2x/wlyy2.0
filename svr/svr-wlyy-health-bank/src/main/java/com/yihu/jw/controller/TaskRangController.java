package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.health.bank.TaskRangDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskRangService;
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
 * @create 2018-06-08 17:02
 * @desc 任务范围
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务范围相关操作",description = "健康任务范围相关操作")
public class TaskRangController extends EnvelopRestEndpoint {

    @Autowired
    private TaskRangService service;
    @Autowired
    private Tracer tracer;


    /**
     * 添加任务范围
     *
     * @param taskRang 任务范围对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createTaskRang)
    @ApiOperation(value = "添加任务范围")
    public MixEnvelop<Boolean, Boolean> insert(@ApiParam(name = "taskRang",value = "任务范围JSON")
                                   @RequestParam(value = "taskRang",required = true)String taskRang){
        try {
            TaskRangDO taskRangDO = toEntity(taskRang,TaskRangDO.class);
            return service.insert(taskRangDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 更新任务范围
     *
     * @param taskRang 任务范围对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateTaskRang)
    @ApiOperation(value = "更新任务范围")
    public MixEnvelop<Boolean, Boolean> update(@ApiParam(name = "taskRang",value = "任务范围JSON")
                                   @RequestParam(value = "taskRang",required = true)String taskRang){
        try {
            TaskRangDO taskRangDO = toEntity(taskRang,TaskRangDO.class);
            return service.update(taskRangDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 查看任务范围
     *
     * @param taskRang 任务范围对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findTaskRang)
    @ApiOperation(value = "查询任务范围")
    public MixEnvelop<TaskRangDO, TaskRangDO> select(@ApiParam(name = "taskRang",value = "任务范围JSON")
                                      @RequestParam(value = "taskRang",required = true)String taskRang,
                                         @ApiParam(name = "page", value = "第几页，从1开始")
                                      @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                         @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                      @RequestParam(value = "size", required = false)Integer size){
        try {
            TaskRangDO taskRangDO = toEntity(taskRang,TaskRangDO.class);
            return service.selectByCondition(taskRangDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
