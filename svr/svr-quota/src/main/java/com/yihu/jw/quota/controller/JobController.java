package com.yihu.jw.quota.controller;

import com.yihu.jw.quota.service.job.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 任务启动
 *
 * @author chenweida
 */
@RestController
@RequestMapping(value = "/job", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "后台-任务控制")
public class JobController extends BaseController {
    @Autowired
    private JobService jobService;

    /**
     * 启动任务
     *
     * @param id id
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
}
