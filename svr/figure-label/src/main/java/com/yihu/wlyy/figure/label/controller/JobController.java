package com.yihu.wlyy.figure.label.controller;

import com.yihu.wlyy.figure.label.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lith on 2018.03.14
 * 定时任务控制器
 */
@RestController
@RequestMapping(value = "/job", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "定时任务控制器")
public class JobController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobService jobService;


    /**
     * 启动任务
     * @param id
     * @return
     */
    @ApiOperation(value = "根据ID立即单个任务")
    @RequestMapping(value = "startNowById", method = RequestMethod.GET)
    public String startNowById(
            @ApiParam(name = "id", value = "任务ID", required = true)@RequestParam(value = "id", required = true) String id) {
        try {
            jobService.startNowById(id);
            return success("启动成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }


    /**
     * 启动任务
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "根据id启动任务，逗号分隔")
    @RequestMapping(value = "startById", method = RequestMethod.GET)
    public String startById(
            @ApiParam(name="id",value="任务id",required=true)@RequestParam(value = "id", required = true) String id) {
        try {
            jobService.startById(id);
            return success("启动成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }

    /**
     * 停止任务
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "根据id停止任务，逗号分隔")
    @RequestMapping(value = "stopById", method = RequestMethod.GET)
    public String stopById(@ApiParam(name="id",value="任务id",required=true)@RequestParam(value = "id", required = true)String id) {
        try {
            jobService.stopById(id);
            return success("停止成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }

    /**
     * 停止所有任务
     *
     * @return
     */
    @ApiOperation(value = "停止所有任务")
    @RequestMapping(value = "stopAll", method = RequestMethod.GET)
    public String stopAll() {
        try {
            jobService.stopAll();
            return success("停止成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }

    /**
     * 启动所有任务
     *
     * @return
     */
    @ApiOperation(value = "启动所有任务")
    @RequestMapping(value = "startAll", method = RequestMethod.GET)
    public String startAll() {
        try {
            jobService.startAll();
            return success("启动成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }
    @ApiOperation(value = "清除緩存")
    @RequestMapping(value = "cleanCache", method = RequestMethod.GET)
    public String cleanCache() {
        try {
            return success("启动成功！");
        } catch (Exception e) {
            error(e);
            return invalidUserException(e, -1, "启动失败:" + e.getMessage());
        }
    }

}
