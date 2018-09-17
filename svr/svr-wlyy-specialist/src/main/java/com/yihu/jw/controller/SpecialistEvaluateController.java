package com.yihu.jw.controller;/**
 * Created by nature of king on 2018/8/27.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yihu.jw.entity.specialist.SpecialistEvaluateDO;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.specialist.SpecialistMapping;
import com.yihu.jw.service.SpecialistEvaluateService;
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
 * @create 2018-08-27 14:17
 * @desc 评论
 **/
@RestController
@RequestMapping(SpecialistMapping.api_specialist_common)
@Api(tags = "评价相关操作", description = "评价相关操作")
public class SpecialistEvaluateController extends EnvelopRestEndpoint {

    @Autowired
    private Tracer tracer;
    @Autowired
    private SpecialistEvaluateService specialistEvaluateService;


    /**
     * 添加评价
     *
     * @param evaluate
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.createEvaluate)
    @ApiOperation(value = "添加评论")
    public MixEnvelop<SpecialistEvaluateDO,SpecialistEvaluateDO> create(@ApiParam(name = "evaluate", value = "评价")
                                                                           @RequestParam(value = "evaluate")String evaluate){
        try {
            JSONObject object = JSONObject.parseObject(evaluate);
            return specialistEvaluateService.createEvaluate(object);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 获取评论
     *
     * @param evaluate
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.getEvaluate)
    @ApiOperation(value = "获取评论")
    public MixEnvelop<JSONObject,JSONObject> select(@ApiParam(name = "evaluate", value = "评价")
                                                                        @RequestParam(value = "evaluate")String evaluate){
        try {
            SpecialistEvaluateDO specialistEvaluateDO = toEntity(evaluate, SpecialistEvaluateDO.class);
            return specialistEvaluateService.selectByCondition(specialistEvaluateDO);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 更新评价
     *
     * @param evaluate
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.updateEvaluate)
    @ApiOperation(value = "更新评价")
    public MixEnvelop<Boolean,Boolean> update(@ApiParam(name = "evaluate", value = "评价")
                                                                        @RequestParam(value = "evaluate")String evaluate){
        try {
            JSONArray array = JSONArray.parseArray(evaluate);
            List<SpecialistEvaluateDO> specialistEvaluateDOS = new ArrayList<>();
            for (int i = 0;i<array.size();i++){
                SpecialistEvaluateDO specialistEvaluateDO = toEntity(array.getString(i),SpecialistEvaluateDO.class);
                specialistEvaluateDOS.add(specialistEvaluateDO);
            }
            return specialistEvaluateService.update(specialistEvaluateDOS);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }


    /**
     * 医生获取评分
     *
     * @param doctor
     * @return
     */
    @PostMapping(value = SpecialistMapping.serviceItem.selectByDoctor)
    @ApiOperation(value = "医生获取评分")
    public MixEnvelop<JSONObject,JSONObject> selectByDoctor(@ApiParam(name = "doctor", value = "评价")
                                                    @RequestParam(value = "doctor")String doctor){
        try {
            return specialistEvaluateService.selectByDoctor(doctor);
        }catch (Exception e){
            e.printStackTrace();
            tracer.getCurrentSpan().logEvent(e.getMessage());
            return MixEnvelop.getError(e.getMessage());
        }
    }
}
