package com.yihu.jw.wlyy.Agreement.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wlyy.WlyyContants;
import com.yihu.jw.wlyy.Agreement.entity.WlyyAgreementKpiLog;
import com.yihu.jw.wlyy.Agreement.service.WlyyAgreementKpiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(WlyyContants.AgreementKpiLog.api_common)
@Api(value = "套餐指标执行日志相关操作", description = "套餐指标执行日志相关操作")
public class WlyyAgreementKpiLogController extends EnvelopRestController {

    @Autowired
    private WlyyAgreementKpiLogService wlyyAgreementKpiLogService;

    @PostMapping(value = WlyyContants.AgreementKpiLog.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建套餐指标日志", notes = "创建套餐指标日志")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAgreementKpiLog wlyyAgreementKpiLog = toEntity(jsonData, WlyyAgreementKpiLog.class);
            return Envelop.getSuccess(WlyyContants.AgreementKpiLog.message_success_create, wlyyAgreementKpiLogService.create(wlyyAgreementKpiLog));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value =WlyyContants.AgreementKpiLog.api_getByCode)
    @ApiOperation(value = "根据code查找套餐指标日志", notes = "根据code查找套餐指标日志")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WlyyContants.AgreementKpiLog.message_success_find, wlyyAgreementKpiLogService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value =WlyyContants.AgreementKpiLog.api_queryPage, method = RequestMethod.GET)
    @ApiOperation(value = "分页获取套餐指标日志")
    public Envelop queryPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,patientCode,signCode,kpiCode,agreementCode,kpiName")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+kpiName,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<WlyyAgreementKpiLog> list = wlyyAgreementKpiLogService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyyAgreementKpiLogService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyyAgreementKpiLog> wlyyAgreementKpiLog = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreementKpiLog.class, fields);

        return Envelop.getSuccessListWithPage(WlyyContants.AgreementKpiLog.message_success_find_functions,wlyyAgreementKpiLog, page, size,count);
    }


    @GetMapping(value =WlyyContants.AgreementKpiLog.api_getList)
    @ApiOperation(value = "获取套餐指标日志列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,patientCode,signCode,kpiCode,agreementCode,kpiName")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+kpiName,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyAgreementKpiLog> list = wlyyAgreementKpiLogService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyyAgreementKpiLog> wlyyAgreementKpiLog = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreementKpiLog.class, fields);
        return Envelop.getSuccessList(WlyyContants.AgreementKpiLog.message_success_find_functions,wlyyAgreementKpiLog);
    }

}
