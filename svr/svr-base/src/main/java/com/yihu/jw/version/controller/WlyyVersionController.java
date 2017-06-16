package com.yihu.jw.version.controller;

import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MBaseServerVersion;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.model.BaseServerVersion;
import com.yihu.jw.version.service.ServerVersionService;
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
 * Created by chenweida on 2017/6/16.
 */

@RestController
@RequestMapping(BaseVersionContants.BaseServerVersion.api_common)
@Api(value = "i健康APP版本模块", description = "i健康APP版本模块接口管理")
public class WlyyVersionController  extends EnvelopRestController {
    @Autowired
    private ServerVersionService BaseServerVersionService;

    @PostMapping(value = BaseVersionContants.BaseServerVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建i健康APP版本", notes = "创建单个i健康APP版本")
    public Envelop createBaseServerVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerVersion BaseServerVersion = toEntity(jsonData, BaseServerVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_create, BaseServerVersionService.createBaseServerVersion(BaseServerVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseVersionContants.BaseServerVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改i健康APP版本", notes = "修改i健康APP版本")
    public Envelop updateBaseServerVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerVersion BaseServerVersion = toEntity(jsonData, BaseServerVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_update, BaseServerVersionService.updateBaseServerVersion(BaseServerVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseVersionContants.BaseServerVersion.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除i健康APP版本", notes = "删除i健康APP版本")
    public Envelop deleteBaseServerVersion(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            BaseServerVersionService.deleteBaseServerVersion(code);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionContants.BaseServerVersion.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找i健康APP版本", notes = "根据code查找i健康APP版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_find, BaseServerVersionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseVersionContants.BaseServerVersion.api_getBaseServerVersion, method = RequestMethod.GET)
    @ApiOperation(value = "获取i健康APP版本列表(分页)")
    public Envelop getBaseServerVersions(
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
        List<BaseServerVersion> list = BaseServerVersionService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=BaseServerVersionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MBaseServerVersion> mBaseServerVersions = convertToModels(list, new ArrayList<>(list.size()), MBaseServerVersion.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionContants.BaseServerVersion.message_success_find_BaseServerVersions,mBaseServerVersions, page, size,count);
    }


    @GetMapping(value = BaseVersionContants.BaseServerVersion.api_getBaseServerVersionNoPage)
    @ApiOperation(value = "获取i健康APP版本列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseServerVersion> list = BaseServerVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<MBaseServerVersion> mBaseServerVersions = convertToModels(list, new ArrayList<>(list.size()), MBaseServerVersion.class, fields);
        return Envelop.getSuccessList(BaseVersionContants.BaseServerVersion.message_success_find_BaseServerVersions,mBaseServerVersions);
    }

}
