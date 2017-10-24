package com.yihu.jw.business.sms.controller;

import com.yihu.jw.business.sms.model.BaseSmsGateway;
import com.yihu.jw.business.sms.service.SmsGatewayService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.base.sms.MSmsGateway;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseSmsRequestMapping;
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

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseSmsRequestMapping.api_common)
@Api(value = "功能模块", description = "功能模块接口管理")
public class SmsGatewayController extends EnvelopRestController {
    @Autowired
    private SmsGatewayService smsGatewayService;

    @PostMapping(value = BaseSmsRequestMapping.SmsGateway.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建功能", notes = "创建单个功能")
    public Envelop createSmsGateway(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseSmsGateway smsGateway = toEntity(jsonData, BaseSmsGateway.class);
            return Envelop.getSuccess(BaseSmsRequestMapping.SmsGateway.message_success_create, smsGatewayService.createSmsGateway(smsGateway));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseSmsRequestMapping.SmsGateway.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改功能", notes = "修改功能")
    public Envelop updateSmsGateway(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseSmsGateway smsGateway = toEntity(jsonData, BaseSmsGateway.class);
            return Envelop.getSuccess(BaseSmsRequestMapping.SmsGateway.message_success_update, smsGatewayService.updateSmsGateway(smsGateway));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseSmsRequestMapping.SmsGateway.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除功能", notes = "删除功能")
    public Envelop deleteSmsGateway(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            smsGatewayService.deleteSmsGateway(code);
            return Envelop.getSuccess(BaseSmsRequestMapping.SmsGateway.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseSmsRequestMapping.SmsGateway.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找功能", notes = "根据code查找功能")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseSmsRequestMapping.SmsGateway.message_success_find, smsGatewayService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseSmsRequestMapping.SmsGateway.api_getSmsGateways, method = RequestMethod.GET)
    @ApiOperation(value = "获取功能列表(分页)")
    public Envelop getSmsGateways(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            //code like 1,name大于aa ,code 等于1 , defaultValue = "code?1;name>aa;code=1"
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<BaseSmsGateway> list = smsGatewayService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=smsGatewayService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MSmsGateway> mSmsGateways = convertToModels(list, new ArrayList<>(list.size()), MSmsGateway.class, fields);

        return Envelop.getSuccessListWithPage(BaseSmsRequestMapping.SmsGateway.message_success_find_SmsGateways,mSmsGateways, page, size,count);
    }


    @GetMapping(value = BaseSmsRequestMapping.SmsGateway.api_getSmsGatewaysNoPage)
    @ApiOperation(value = "获取功能列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseSmsGateway> list = smsGatewayService.search(fields,filters,sorts);
        //封装返回格式
        List<MSmsGateway> mSmsGateways = convertToModels(list, new ArrayList<>(list.size()), MSmsGateway.class, fields);
        return Envelop.getSuccessList(BaseSmsRequestMapping.SmsGateway.message_success_find_SmsGateways,mSmsGateways);
    }
}
