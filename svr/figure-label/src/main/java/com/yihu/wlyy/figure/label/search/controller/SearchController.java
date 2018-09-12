package com.yihu.wlyy.figure.label.search.controller;

import com.yihu.wlyy.figure.label.controller.JobController;
import com.yihu.wlyy.figure.label.search.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static sun.management.Agent.error;

/**
 * @author litaohong on 2018/5/17
 * @project patient-co-management
 */
@RestController
@RequestMapping(value = "/label", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "标签查询控制器")
public class SearchController {
    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private SearchService searchService;

    /**
     * @param idcard
     * @return
     */
    @ApiOperation(value = "根据idcard查询基本信息标签")
    @RequestMapping(value = "searchBaseInfo", method = RequestMethod.GET)
    public String searchBaseInfo(
            @ApiParam(name = "idcard", value = "身份证号", required = true)@RequestParam(value = "idcard", required = true) String idcard) {
        try {
            return searchService.getBaseInfo(idcard).toJSONString();
        } catch (Exception e) {
            error(e);
            return "error";
        }
    }


    /**
     * @param idcard
     * @return
     */
    @ApiOperation(value = "根据idcard查询病种信息标签")
    @RequestMapping(value = "searchDieaseInfo", method = RequestMethod.GET)
    public String searchDieaseInfo(
            @ApiParam(name = "idcard", value = "身份证号", required = true)@RequestParam(value = "idcard", required = true) String idcard) {
        try {
            return searchService.getDiseaseInfo(idcard).toJSONString();
        } catch (Exception e) {
            error(e);
            return "error";
        }
    }

    /**
     * @param idcard
     * @return
     */
    @ApiOperation(value = "根据idcard查询代预约情况")
    @RequestMapping(value = "searchAppointmentInfo", method = RequestMethod.GET)
    public String searchAppointmentInfo(
            @ApiParam(name = "idcard", value = "身份证号", required = true)@RequestParam(value = "idcard", required = true) String idcard) {
        try {
            return searchService.getAppointmentInfo(idcard).toJSONString();
        } catch (Exception e) {
            error(e);
            return "error";
        }
    }

    /**
     * @param idcard
     * @return
     */
    @ApiOperation(value = "根据idcard查询ehr基本档案信息")
    @RequestMapping(value = "searchehrbaseinfo", method = RequestMethod.GET)
    public String searchEHRRecordInfo(
            @ApiParam(name = "idcard", value = "身份证号", required = true)@RequestParam(value = "idcard", required = true) String idcard) {
        try {
            return searchService.getEHRRecordInfo(idcard);
        } catch (Exception e) {
            error(e);
            return "error";
        }
    }


}
