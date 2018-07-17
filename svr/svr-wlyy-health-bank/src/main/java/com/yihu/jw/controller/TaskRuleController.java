package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/6/8.
 */

import com.alibaba.fastjson.JSONArray;
import com.yihu.jw.entity.health.bank.TaskRuleDO;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskRuleService;
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
 * @create 2018-06-08 16:25
 * @desc 任务规则 controller
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务规则相关操作",description = "健康任务规则相关操作")
public class TaskRuleController extends EnvelopRestController {

    @Autowired
    private TaskRuleService service;
    @Autowired
    private Tracer tracer;


    /**
     * 添加任务规则
     *
     * @param taskRule 任务规则对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createTaskRule)
    @ApiOperation(value = "添加任务规则")
    public Envelop<Boolean> insert(@ApiParam(name = "taskRule",value = "健康任务规则JSON")
                                          @RequestParam(value = "taskRule",required = true)String taskRule){
        try {
            TaskRuleDO taskRuleDO = toEntity(taskRule,TaskRuleDO.class);
            return service.insert(taskRuleDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    /**
     * 更新任务规则
     *
     * @param taskRule 任务规则对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateTaskRule)
    @ApiOperation(value = "更新任务规则")
    public Envelop<Boolean> update(@ApiParam(name = "taskRule",value = "健康任务规则JSON")
                                          @RequestParam(value = "taskRule",required = true)String taskRule){
        try {
            TaskRuleDO taskRuleDO = toEntity(taskRule,TaskRuleDO.class);
            return service.update(taskRuleDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 查看任务规则
     *
     * @param taskRule 任务规则对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findTaskRule)
    @ApiOperation(value = "查询任务规则")
    public Envelop<TaskRuleDO> select(@ApiParam(name = "taskRule",value = "健康任务规则JSON")
                                         @RequestParam(value = "taskRule",required = true)String taskRule,
                                         @ApiParam(name = "page", value = "第几页，从1开始")
                                         @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                         @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                         @RequestParam(value = "size", required = false)Integer size){
        try {
            TaskRuleDO taskRuleDO = toEntity(taskRule,TaskRuleDO.class);
            return service.selectByCondition(taskRuleDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }


    /**
     * 批量删除任务规则
     *
     * @param ids []
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.batchTaskRule)
    @ApiOperation(value = "批量删除任务规则")
    public Envelop<Boolean> batchDelete(@ApiParam(name="ids",value = "id集合[]")
                                        @RequestParam(value = "ids",required = false)String ids){
        try{
            Envelop<Boolean> envelop = new Envelop<>();
            JSONArray array = JSONArray.parseArray(ids);
            List<String> taskRuleIds = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                taskRuleIds.add(array.getString(i));
            }
            service.batchDelete(taskRuleIds);
            envelop.setObj(true);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

}
