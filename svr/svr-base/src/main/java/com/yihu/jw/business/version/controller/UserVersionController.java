package com.yihu.jw.business.version.controller;

import com.yihu.jw.base.version.BaseUserVersionDO;
import com.yihu.jw.business.version.service.BaseUserVersionService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/11/10.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(description = "灰度发布,用户版本信息")
public class UserVersionController extends EnvelopRestController {
    @Autowired
    private BaseUserVersionService baseEmployeeVersionService;


    @PostMapping(value = BaseVersionRequestMapping.UserVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "给用户添加灰度发布版本信息", notes = "给用户添加灰度发布版本信息")
    public Envelop createUserVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseUserVersionDO baseUserVersion = toEntity(jsonData, BaseUserVersionDO.class);
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_create, baseEmployeeVersionService.createUserVersion(baseUserVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseVersionRequestMapping.UserVersion.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除用户灰度发布版本信息", notes = "删除用户灰度发布版本信息")
    public Envelop deleteUsersVersion(
            @ApiParam(name = "ids", value = "ids")
            @RequestParam(value = "ids", required = true) String ids) {
        try {
            baseEmployeeVersionService.deleteUserVersion(ids);
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_delete);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionRequestMapping.UserVersion.api_getByUserId, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据用户和saadId获取用户的灰度发布版本信息", notes = "根据用户获取用户的灰度发布版本信息")
    public Envelop getUserVersionByUserId(
            @ApiParam(name = "userId", value = "userId")
            @PathParam(value = "userId") String userId) {
        try {
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_find, baseEmployeeVersionService.getUserVersionByUserId(userId));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @GetMapping(value = BaseVersionRequestMapping.UserVersion.api_getById, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据ID获取用户的灰度发布版本信息", notes = "根据ID获取用户的灰度发布版本信息")
    public Envelop getUserVersion(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id) {
        try {
            return Envelop.getSuccess(BaseVersionRequestMapping.UserVersion.message_success_find, baseEmployeeVersionService.getUserVersion(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @RequestMapping(value = BaseVersionRequestMapping.UserVersion.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取用户的灰度发布版本信息(分页)", notes = "根据用户获取用户的灰度发布版本信息")
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
        List<BaseUserVersionDO> list = baseEmployeeVersionService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=baseEmployeeVersionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<BaseUserVersionDO> baseUserVersions = convertToModels(list, new ArrayList<>(list.size()), BaseUserVersionDO.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionRequestMapping.UserVersion.message_success_find,baseUserVersions, page, size,count);
    }


    @GetMapping(value =BaseVersionRequestMapping.UserVersion.api_getListNoPage)
    @ApiOperation(value = "获取用户的灰度发布版本信息，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseUserVersionDO> list = baseEmployeeVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<BaseUserVersionDO> baseUserVersions = convertToModels(list, new ArrayList<>(list.size()), BaseUserVersionDO.class, fields);
        return Envelop.getSuccessList(BaseVersionRequestMapping.UserVersion.message_success_find,baseUserVersions);
    }
}
