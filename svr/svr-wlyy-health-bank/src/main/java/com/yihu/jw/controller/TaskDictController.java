package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/6/8.
 */

import com.yihu.jw.entity.health.bank.TaskDictDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskDictService;
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
 * @create 2018-06-08 16:42
 * @desc 任务字典 Controller
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务字典相关操作",description = "健康任务字典相关操作")
public class TaskDictController extends EnvelopRestEndpoint {
    @Autowired
    private TaskDictService service;
    @Autowired
    private Tracer tracer;

    /**
     * 添加任务字典
     *
     * @param taskDict 任务字典对象
     *
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createTaskDict)
    @ApiOperation(value = "添加任务字典")
    public MixEnvelop<Boolean, Boolean> insert(@ApiParam(name = "taskDict",value = "任务字典JSON")
                                       @RequestParam(value = "taskDict",required = true)String taskDict){
        try {
            TaskDictDO taskDictDO = toEntity(taskDict,TaskDictDO.class);
            return service.insert(taskDictDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 更新任务字典
     * @param taskDict 更新任务字典
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateTaskDict)
    @ApiOperation(value = "更新任务字典")
    public MixEnvelop<Boolean, Boolean> update(@ApiParam(name = "taskDict",value = "任务字典JSON")
                                   @RequestParam(value = "taskDict",required = true)String taskDict){
        try {
            TaskDictDO taskDictDO = toEntity(taskDict,TaskDictDO.class);
            return service.update(taskDictDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 查询任务字典
     *
     * @param taskDict 任务字典对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findTaskDict)
    @ApiOperation(value = "查询任务字典")
    public MixEnvelop<TaskDictDO, TaskDictDO> select(@ApiParam(name = "taskDict",value = "任务字典JSON")
                                      @RequestParam(value = "taskDict",required = true)String taskDict,
                                         @ApiParam(name = "page", value = "第几页，从1开始")
                                      @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                         @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                      @RequestParam(value = "size", required = false)Integer size){
        try {
            TaskDictDO taskDictDO = toEntity(taskDict,TaskDictDO.class);
            return service.selectByCondition(taskDictDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
