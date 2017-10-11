package com.yihu.jw.version.controller;

import com.yihu.jw.base.service.base.FunctionService;
import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MBaseServerUrlVersion;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.model.BaseServerUrlVersion;
import com.yihu.jw.version.service.ServerUrlVersionService;
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
import java.util.Map;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping(BaseVersionContants.api_common)
@Api(value = "后台URL模块", description = "后台URL模块接口管理")
public class ServerUrlVersionController extends EnvelopRestController {
    @Autowired
    private ServerUrlVersionService serverUrlVersionService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private ServerVersionService serverVersionService;

    @PostMapping(value = BaseVersionContants.BaseServerUrlVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建后台URL版本", notes = "创建单个后台URL版本")
    public Envelop createBaseServerUrlVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerUrlVersion BaseServerUrlVersion = toEntity(jsonData, BaseServerUrlVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerUrlVersion.message_success_create, serverUrlVersionService.createBaseServerUrlVersion(BaseServerUrlVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseVersionContants.BaseServerUrlVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改后台URL版本", notes = "修改后台URL版本")
    public Envelop updateBaseServerUrlVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerUrlVersion BaseServerUrlVersion = toEntity(jsonData, BaseServerUrlVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerUrlVersion.message_success_update, serverUrlVersionService.updateBaseServerUrlVersion(BaseServerUrlVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseVersionContants.BaseServerUrlVersion.api_delete)
    @ApiOperation(value = "删除后台URL版本", notes = "删除后台URL版本")
    public Envelop deleteBaseServerUrlVersion(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes) {
        try {
            serverUrlVersionService.deleteBaseServerUrlVersion(codes);
            return Envelop.getSuccess(BaseVersionContants.BaseServerUrlVersion.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getByCode)
    @ApiOperation(value = "根据code查找后台URL版本", notes = "根据code查找后台URL版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseVersionContants.BaseServerUrlVersion.message_success_find, serverUrlVersionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取后台URL版本列表(分页)")
    public Envelop getBaseServerUrlVersions(
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
        List<BaseServerUrlVersion> list = serverUrlVersionService.search(fields, filters, sorts, page, size);
        Map<String, String> serverVersions = serverVersionService.getName();
        Map<String, String> functions= functionService.getName();
        for(BaseServerUrlVersion version:list){
            String serverName = serverVersions.get(version.getServerCode());
            String functionName = functions.get(version.getFunctionCode());
            version.setServerName(serverName);
            version.setFunctionName(functionName);
        }
        //获取总数
        long count=serverUrlVersionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MBaseServerUrlVersion> mBaseServerUrlVersions = convertToModels(list, new ArrayList<>(list.size()), MBaseServerUrlVersion.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionContants.BaseServerUrlVersion.message_success_find_BaseServerUrlVersions,mBaseServerUrlVersions, page, size,count);
    }

    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getListNoPage)
    @ApiOperation(value = "获取后台URL版本列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseServerUrlVersion> list = serverUrlVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<MBaseServerUrlVersion> mBaseServerUrlVersions = convertToModels(list, new ArrayList<>(list.size()), MBaseServerUrlVersion.class, fields);
        return Envelop.getSuccessList(BaseVersionContants.BaseServerUrlVersion.message_success_find_BaseServerUrlVersions,mBaseServerUrlVersions);
    }
}
