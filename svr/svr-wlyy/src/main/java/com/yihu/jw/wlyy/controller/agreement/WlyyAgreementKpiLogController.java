package com.yihu.jw.wlyy.controller.agreement;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.EnvelopRestController;
import com.yihu.jw.rm.wlyy.WlyyRequestMapping;
import com.yihu.jw.wlyy.agreement.WlyyAgreementKpiLogDO;
import com.yihu.jw.wlyy.service.agreement.WlyyAgreementKpiLogService;
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
@RequestMapping(WlyyRequestMapping.api_wlyy_common)
@Api(value = "套餐指标执行日志相关操作", description = "套餐指标执行日志相关操作")
public class WlyyAgreementKpiLogController extends EnvelopRestController {

    @Autowired
    private WlyyAgreementKpiLogService wlyyAgreementKpiLogService;

    @PostMapping(value = WlyyRequestMapping.AgreementKpiLog.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建套餐指标日志", notes = "创建套餐指标日志")
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyAgreementKpiLogDO wlyyAgreementKpiLog = toEntity(jsonData, WlyyAgreementKpiLogDO.class);
            return Envelop.getSuccess(WlyyRequestMapping.AgreementKpiLog.message_success_create, wlyyAgreementKpiLogService.create(wlyyAgreementKpiLog));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WlyyRequestMapping.AgreementKpiLog.api_getById)
    @ApiOperation(value = "根据code查找套餐指标日志", notes = "根据code查找套餐指标日志")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(WlyyRequestMapping.AgreementKpiLog.message_success_find, wlyyAgreementKpiLogService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WlyyRequestMapping.AgreementKpiLog.api_queryPage, method = RequestMethod.GET)
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
        List<WlyyAgreementKpiLogDO> list = wlyyAgreementKpiLogService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyyAgreementKpiLogService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<WlyyAgreementKpiLogDO> wlyyAgreementKpiLog = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreementKpiLogDO.class, fields);

        return Envelop.getSuccessListWithPage(WlyyRequestMapping.AgreementKpiLog.message_success_find_functions,wlyyAgreementKpiLog, page, size,count);
    }


    @GetMapping(value = WlyyRequestMapping.AgreementKpiLog.api_getList)
    @ApiOperation(value = "获取套餐指标日志列表(不分页)")
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,patientCode,signCode,kpiCode,agreementCode,kpiName")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+kpiName,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyAgreementKpiLogDO> list = wlyyAgreementKpiLogService.search(fields,filters,sorts);
        //封装返回格式
        List<WlyyAgreementKpiLogDO> wlyyAgreementKpiLog = convertToModels(list, new ArrayList<>(list.size()), WlyyAgreementKpiLogDO.class, fields);
        return Envelop.getSuccessList(WlyyRequestMapping.AgreementKpiLog.message_success_find_functions,wlyyAgreementKpiLog);
    }

}
