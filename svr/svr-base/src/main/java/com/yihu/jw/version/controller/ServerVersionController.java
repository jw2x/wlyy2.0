package com.yihu.jw.version.controller;

import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MWlyyVersion;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.model.WlyyVersion;
import com.yihu.jw.version.service.WlyyVersionService;
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
@RequestMapping(BaseVersionContants.WlyyVersion.api_common)
@Api(value = "服务器版本模块", description = "服务器版本模块接口管理")
public class ServerVersionController extends EnvelopRestController {
    @Autowired
    private WlyyVersionService wlyyVersionService;

    @PostMapping(value = BaseVersionContants.WlyyVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建服务器版本", notes = "创建单个服务器版本")
    public Envelop createWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyVersion WlyyVersion = toEntity(jsonData, WlyyVersion.class);
            return Envelop.getSuccess(BaseVersionContants.WlyyVersion.message_success_create, wlyyVersionService.createWlyyVersion(WlyyVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseVersionContants.WlyyVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改服务器版本", notes = "修改服务器版本")
    public Envelop updateWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WlyyVersion WlyyVersion = toEntity(jsonData, WlyyVersion.class);
            return Envelop.getSuccess(BaseVersionContants.WlyyVersion.message_success_update, wlyyVersionService.updateWlyyVersion(WlyyVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseVersionContants.WlyyVersion.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除服务器版本", notes = "删除服务器版本")
    public Envelop deleteWlyyVersion(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wlyyVersionService.deleteWlyyVersion(code);
            return Envelop.getSuccess(BaseVersionContants.WlyyVersion.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找服务器版本", notes = "根据code查找服务器版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseVersionContants.WlyyVersion.message_success_find, wlyyVersionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseVersionContants.WlyyVersion.api_getWlyyVersion, method = RequestMethod.GET)
    @ApiOperation(value = "获取服务器版本列表(分页)")
    public Envelop getWlyyVersions(
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
        List<WlyyVersion> list = wlyyVersionService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wlyyVersionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWlyyVersion> mWlyyVersions = convertToModels(list, new ArrayList<>(list.size()), MWlyyVersion.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionContants.WlyyVersion.message_success_find_WlyyVersions,mWlyyVersions, page, size,count);
    }


    @GetMapping(value = BaseVersionContants.WlyyVersion.api_getWlyyVersionNoPage)
    @ApiOperation(value = "获取服务器版本列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WlyyVersion> list = wlyyVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<MWlyyVersion> mWlyyVersions = convertToModels(list, new ArrayList<>(list.size()), MWlyyVersion.class, fields);
        return Envelop.getSuccessList(BaseVersionContants.WlyyVersion.message_success_find_WlyyVersions,mWlyyVersions);
    }


}
