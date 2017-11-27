package com.yihu.jw.business.sms.controller;

import com.yihu.jw.base.sms.BaseSmsDO;
import com.yihu.jw.business.sms.service.SmsService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.base.sms.MSms;
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
@Api(value = "短信模块", description = "短信模块接口管理")
public class SmsController extends EnvelopRestController {
    @Autowired
    private SmsService smsService;

    @PostMapping(value = BaseSmsRequestMapping.Sms.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建短信", notes = "创建单个短信")
    public Envelop createSms(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseSmsDO sms = toEntity(jsonData, BaseSmsDO.class);
            return Envelop.getSuccess(BaseSmsRequestMapping.Sms.message_success_create, smsService.createSms(sms));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseSmsRequestMapping.Sms.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改短信", notes = "修改短信")
    public Envelop updateSms(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseSmsDO sms = toEntity(jsonData, BaseSmsDO.class);
            return Envelop.getSuccess(BaseSmsRequestMapping.Sms.message_success_update, smsService.updateSms(sms));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseSmsRequestMapping.Sms.api_getSmss, method = RequestMethod.GET)
    @ApiOperation(value = "获取短信列表(分页)")
    public Envelop getSmss(
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
        List<BaseSmsDO> list = smsService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=smsService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MSms> mSmss = convertToModels(list, new ArrayList<>(list.size()), MSms.class, fields);

        return Envelop.getSuccessListWithPage(BaseSmsRequestMapping.Sms.message_success_find_smss,mSmss, page, size,count);
    }


    @GetMapping(value = BaseSmsRequestMapping.Sms.api_getSmssNoPage)
    @ApiOperation(value = "获取短信列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseSmsDO> list = smsService.search(fields,filters,sorts);
        //封装返回格式
        List<MSms> mSmss = convertToModels(list, new ArrayList<>(list.size()), MSms.class, fields);
        return Envelop.getSuccessList(BaseSmsRequestMapping.Sms.message_success_find_smss,mSmss);
    }
}
