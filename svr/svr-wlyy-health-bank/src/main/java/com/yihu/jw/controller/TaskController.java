package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/4/27.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.entity.health.bank.TaskDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.health.bank.HealthBankMapping;
import com.yihu.jw.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhinan
 * @create 2018-04-27 9:29
 * @desc health blank Controller
 **/
@RestController
@RequestMapping(HealthBankMapping.api_health_bank_common)
@Api(tags = "健康任务相关操作",description = "健康银行相关操作")
public class TaskController extends EnvelopRestEndpoint {

    @Autowired
    private TaskService service;
    @Autowired
    private Tracer tracer;

    /**
     * 指派任务
     *
     * @param task 任务对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.createTask)
    @ApiOperation(value = "添加任务")
    public MixEnvelop<Boolean, Boolean> assigningTask(@ApiParam(name = "task",value = "健康任务JSON")
                                          @RequestParam(value = "task",required = true)String task){
        try {
            TaskDO taskDO = toEntity(task,TaskDO.class);
            return service.insert(taskDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     *  查询任务
     *
     * @param task 任务对象
     * @param page 页码
     * @param size 分页大小
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.findTask)
    @ApiOperation(value = "查询任务")
    public MixEnvelop<TaskDO, TaskDO> assigningTask(@ApiParam(name = "task",value = "健康任务JSON")
                                          @RequestParam(value = "task",required = true)String task,
                                            @ApiParam(name = "page", value = "第几页，从1开始")
                                          @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                            @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                              @RequestParam(value = "size", required = false)Integer size){
        try {
            TaskDO taskDO = toEntity(task,TaskDO.class);
            return service.selectByCondition(taskDO,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 根据编码查询
     *
     * @param object
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.selectByCode)
    @ApiOperation(value = "根据编码查询")
    public MixEnvelop<TaskDO, TaskDO> selectByCode(@RequestBody JSONObject object){
        try {
            JSONArray array = object.getJSONArray("taskCode");
            String patientId = object.getString("patientId");
            Integer page = object.getInteger("page");
            Integer size = object.getInteger("size");
            return service.selectByTask(array,patientId,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }

    /**
     * 更新任务
     *
     * @param task 任务对象
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.updateTask)
    @ApiOperation(value = "更新任务")
    public MixEnvelop<Boolean, Boolean> udpateTask(@ApiParam(name = "task",value = "健康任务JSON")
                                          @RequestParam(value = "task",required = true)String task){
        try {
            TaskDO taskDO = toEntity(task,TaskDO.class);
            return service.update(taskDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 删除任务
     *
     * @param ids id集合[]
     * @return
     */
    @PostMapping(value = HealthBankMapping.healthBank.batchTask)
    @ApiOperation(value = "批量删除任务")
    public MixEnvelop<Boolean, Boolean> batchDelete(@ApiParam(name="ids",value = "id集合[]")
                                        @RequestParam(value = "ids",required = false)String ids){
        try{
            MixEnvelop<Boolean, Boolean> envelop = new MixEnvelop<>();
            JSONArray array = JSONArray.parseArray(ids);
            List<String> taskIds = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                taskIds.add(array.getString(i));
            }
            service.batchDelete(taskIds);
            envelop.setObj(true);
            return envelop;
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**//**
     * patient find health task
     *
     * @param patientId
     * @param page
     * @param size
     * @return
     *//*
    @GetMapping(value = HealthBankMapping.healthBank.findTask)
    @ApiOperation(value = "查看健康任务")
    public Envelop<TaskPatientDetailDO> getTaskByPatient(@ApiParam(name = "patientId",value = "居民Id")
                                                @RequestParam(value = "patientId",required = false)String patientId,
                                                  @ApiParam(name = "doctorId",value = "家庭医生Id")
                                                @RequestParam(value = "doctorId",required = false)String doctorId,
                                                  @ApiParam(name = "page", value = "第几页，从1开始")
                                                @RequestParam(value = "page", defaultValue = "1",required = false)Integer page,
                                                  @ApiParam(name = "size",defaultValue = "10",value = "，每页分页大小")
                                                @RequestParam(value = "size", required = false)Integer size){
        try{
            return service.findTaskByPatient(patientId,doctorId,page,size);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }

    @PostMapping(value = HealthBankMapping.healthBank.updateTask)
    @ApiOperation(value = "居民执行健康任务")
    public Envelop<Boolean> updateTask(@ApiParam(name = "taskInfo",value = "健康任务JSON")
                                                @RequestParam(value = "taskInfo",required = true)String taskInfo){
        try{
            TaskPatientDetailDO taskDetailDO = toEntity(taskInfo,TaskPatientDetailDO.class);
            return service.update(taskDetailDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return Envelop.getError(e.getMessage());
        }
    }*/

    @PostMapping(value = HealthBankMapping.healthBank.selectByDate)
    @ApiOperation(value = "某个时刻任务的积分详情")
    public MixEnvelop<TaskDO, TaskDO> selectByDate(@ApiParam(name="object",value = "id集合[]")
                                        @RequestParam(value = "object",required = false)String object){
        try{
            MixEnvelop<TaskDO, TaskDO> envelop = new MixEnvelop<>();
            JSONObject jsonObject = JSONObject.parseObject(object);
            return service.selectByDate(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
