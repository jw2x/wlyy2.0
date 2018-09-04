package com.yihu.wlyy.statistics.controller;

import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.wlyy.statistics.service.JobService;
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
public class JobController extends EnvelopRestEndpoint {
    @Autowired
    private  JobService jobService;


    /**
     * 启动任务
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "根据ID立即单个任务")
    @RequestMapping(value = "startNowById", method = RequestMethod.GET)
    public Envelop startNowById(
            @ApiParam(name = "id", value = "任务ID", required = true)
            @RequestParam(value = "id", required = true) String id) throws Exception {
        jobService.startNowById(id);
        return success("启动成功！");
    }

    /**
     * 生成过去几天的数据
     *
     * @param day
     * @return
     */
    @ApiOperation(value = "生成过去几天的数据")
    @RequestMapping(value = "productDataByDay", method = RequestMethod.GET)
    public Envelop productDataByDay(
            @ApiParam(name = "day", value = "距离今天的天数（如果是要生成昨天的数据，day=1）")
            @RequestParam(value = "day", required = true) int day) throws Exception {
        jobService.productDataByDay(day);
        return success("启动成功！");
    }

    /**
     * 生成过去某一天的全部的数据
     *
     * @param day
     * @return
     */
    @ApiOperation(value = "生成过去某一天的全部的数据")
    @RequestMapping(value = "productDataByOneDay", method = RequestMethod.GET)
    public Envelop productDataByOneDay(
            @ApiParam(name = "day", value = "yyyy-MM-dd")
            @RequestParam(value = "day", required = true)String day) throws Exception {
        jobService.productDataByOneDay(day);
        return success("启动成功！");
    }
    /**
     * 生成过去某一天到某一天的全部的数据
     *
     * @param start
     * @param end
     * @return
     */
    @ApiOperation(value = "生成过去某一天到某一天的全部的数据(包含头尾)")
    @RequestMapping(value = "productDataByDayToDay", method = RequestMethod.GET)
    public Envelop productDataByDayToDay(
            @ApiParam(name = "start", value = "yyyy-MM-dd", required = true)
            @RequestParam(value = "start", required = true)String start,
            @ApiParam(name = "end", value = "yyyy-MM-dd", required = true)
            @RequestParam(value = "end", required = true)String end) throws Exception {
        jobService.productDataByDayToDay(start,end);
        return success("启动成功！");
    }
    /**
     * 生成过去某一天到某一天的某个指标的数据
     *
     * @param start
     * @param end
     * @return
     */
    @ApiOperation(value = "生成过去某一天到某一天的某个指标的数据(包含头尾)")
    @RequestMapping(value = "productDataByDayToDayAndId", method = RequestMethod.GET)
    public Envelop productDataByDayToDayAndId(
            @ApiParam(name = "start", value = "yyyy-MM-dd")
            @RequestParam(value = "start", required = true)String start,
            @ApiParam(name = "end", value = "yyyy-MM-dd")
            @RequestParam(value = "end", required = true)String end,
            @ApiParam(name = "ids", value = "任务ids多个逗号分割")
            @RequestParam(value = "ids", required = true)String ids) throws Exception {
        jobService.productDataByDayToDayAndId(start,end,ids);
        return success("启动成功！");
    }
    /**
     * 生成过去某一天的某一个指标的数据
     *
     * @param day
     * @return
     */
    @ApiOperation(value = "生成过去某一天的全部的数据")
    @RequestMapping(value = "productDataByOneDayWithId", method = RequestMethod.GET)
    public Envelop productDataByOneDayWithId(
            @ApiParam(name = "day", value = "yyyy-MM-dd")
            @RequestParam(value = "day", required = true)String day,
            @ApiParam(name = "id", value = "任务id")
            @RequestParam(value = "id", required = true)String id) throws Exception {
        jobService.productDataByOneDayWithId(day, id);
        return success("启动成功！");
    }
    /**
     * 生成过去到现在的全部的数据
     *
     * @param day
     * @return
     */
    @ApiOperation(value = "生成过去到现在的全部的数据")
    @RequestMapping(value = "productDataByDayAndId", method = RequestMethod.GET)
    public Envelop productDataByDayAndId(
            @ApiParam(name = "day", value = "距离今天的天数（如果是要生成昨天的数据，day=1）")
            @RequestParam(value = "day", required = true) int day,
            @ApiParam(name="id",required=true)
            @RequestParam(value = "id", required = true) String id) throws Exception {
        jobService.productDataByDayAndId(day, id);
        return success("启动成功！");
    }

    /**
     * 启动任务
     *
     * @param id id
     * @return
    */
    @ApiOperation(value = "启动单个任务")
    @RequestMapping(value = "startById", method = RequestMethod.GET)
    public Envelop startById(
            @ApiParam(name="id",value="任务id",required=true)
            @RequestParam(value = "id", required = true) String id) throws Exception {
        jobService.startById(id);
        return success("启动成功！");
    }

    /**
     * 停止任务
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "停止单个任务")
    @RequestMapping(value = "stopById", method = RequestMethod.GET)
    public Envelop stopById(
            @ApiParam(name="id",value="任务id",required=true)
            @RequestParam(value = "id", required = true)String id) throws Exception {
        jobService.stopById(id);
        return success("停止成功！");
    }

    /**
     * 停止所有任务
     *
     * @return
     */
    @ApiOperation(value = "停止所有任务")
    @RequestMapping(value = "stopAll", method = RequestMethod.GET)
    public Envelop stopAll() throws Exception {
        jobService.stopAll();
        return success("停止成功！");
    }

    /**
     * 启动所有任务
     *
     * @return
     */
    @ApiOperation(value = "启动所有任务")
    @RequestMapping(value = "startAll", method = RequestMethod.GET)
    public Envelop startAll() throws Exception {
        jobService.startAll();
        return success("启动成功！");
    }

    /**
     * 启动判断的任务
     *
     * @return
     */
    @ApiOperation(value = "启动清除缓存的任务")
    @RequestMapping(value = "startCleanCacheJob", method = RequestMethod.GET)
    public Envelop startCleanCacheJob() throws Exception {
        jobService.startCleanCacheJob();
        return success("启动成功！");
    }

    /**
     * 停止判断的任务
     *
     * @return
     */
    @ApiOperation(value = "停止清除缓存的任务")
    @RequestMapping(value = "stopCleanCacheJob", method = RequestMethod.GET)
    public Envelop stopCleanCacheJob() throws Exception {
        jobService.stopCleanCacheJob();
        return success("停止成功！");
    }

    //================================================没有休眠时间=============================================================
    /**
     * 生成过去某一天到某一天的某个指标的数据
     *
     * @param start
     * @param end
     * @return
     */
    @ApiOperation(value = "生成过去某一天到某一天的某个指标的数据(包含头尾)没有休眠时间")
    @RequestMapping(value = "productDataByDayToDayAndIdNoSleep", method = RequestMethod.GET)
    public Envelop productDataByDayToDayAndIdNoSleep(
            @ApiParam(name = "start", value = "yyyy-MM-dd")
            @RequestParam(value = "start", required = true)String start,
            @ApiParam(name = "end", value = "yyyy-MM-dd")
            @RequestParam(value = "end", required = true)String end,
            @ApiParam(name = "id", value = "任务id")
            @RequestParam(value = "id", required = true)String id,
            @ApiParam(name = "sleepTime", value = "任务间隔时间（秒）")
            @RequestParam(value = "sleepTime", required = true)Long sleepTime) throws Exception {
        jobService.productDataByDayToDayAndIdNoSleep(start,end,id,sleepTime);
        return success("启动成功！");
    }

    /**
     * 生成过去某一天的全部的数据
     *
     * @param day
     * @return
     */
    @ApiOperation(value = "生成过去某一天的全部的数据")
    @RequestMapping(value = "productDataByOneDayNoSleep", method = RequestMethod.GET)
    public Envelop productDataByOneDayNoSleep(
            @ApiParam(name = "day", value = "yyyy-MM-dd")
            @RequestParam(value = "day", required = true)String day,
            @ApiParam(name = "sleepTime", value = "任务间隔时间（秒）")
            @RequestParam(value = "sleepTime", required = true)Long sleepTime) throws Exception {
        jobService.productDataByOneDayNoSleep(day,sleepTime);
        return success("启动成功！");
    }

    /************************************************************************************/

    /**
     * 生成过去某一天到某一天的全部的数据
     *
     * @param start
     * @param end
     * @return
     */
    @ApiOperation(value = "生成过去某一天到某一天的全部的数据(包含头尾)按周或月统计")
    @RequestMapping(value = "productDataByDayToDay2", method = RequestMethod.GET)
    public Envelop productDataByDayToDay2(
            @ApiParam(name = "start", value = "yyyy-MM-dd", required = true)
            @RequestParam(value = "start", required = true)String start,
            @ApiParam(name = "end", value = "yyyy-MM-dd", required = true)
            @RequestParam(value = "end", required = true)String end,
            @ApiParam(name = "incrementInterval", value = "2周，3月", required = true)
            @RequestParam(value = "incrementInterval", required = true)Integer incrementInterval,
            @ApiParam(name = "id", value = "指标id,逗号分隔（为空表示全部）", required = false)
            @RequestParam(value = "id", required = false)String id) throws Exception {
        if(incrementInterval==2){
            jobService.productWeekByDayToDay(start,end,id);
        }else if(incrementInterval==3){

            jobService.productMonthByDayToDay(start,end,id);
        }else{
            return success("启动失败！");
        }
        return success("启动成功！");
    }
}
