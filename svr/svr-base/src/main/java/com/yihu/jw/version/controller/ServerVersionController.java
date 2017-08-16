package com.yihu.jw.version.controller;

import com.yihu.jw.restmodel.base.version.BaseVersionContants;
import com.yihu.jw.restmodel.base.version.MBaseServerVersion;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.version.model.BaseServerUrlVersion;
import com.yihu.jw.version.model.BaseServerVersion;
import com.yihu.jw.version.model.BaseUserUrlVersion;
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

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseVersionContants.api_common)
@Api(value = "服务器版本模块", description = "服务器版本模块接口管理")
public class ServerVersionController extends EnvelopRestController {
    @Autowired
    private ServerVersionService serverVersionService;
    @Autowired
    private ServerUrlVersionService serverUrlVersionService;

    @PostMapping(value = BaseVersionContants.BaseServerVersion.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建服务器版本", notes = "创建单个服务器版本")
    public Envelop createWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerVersion serverVersion = toEntity(jsonData, BaseServerVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_create, serverVersionService.createBaseServerVersion(serverVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseVersionContants.BaseServerVersion.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改服务器版本", notes = "修改服务器版本")
    public Envelop updateWlyyVersion(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            BaseServerVersion serverVersion = toEntity(jsonData, BaseServerVersion.class);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_update, serverVersionService.updateBaseServerVersion(serverVersion));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = BaseVersionContants.BaseServerVersion.api_delete)
      @ApiOperation(value = "删除服务器版本", notes = "删除服务器版本")
      public Envelop deleteWlyyVersion(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) {
        try {
            serverVersionService.deleteBaseServerVersion(codes,userCode,userName);
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseVersionContants.BaseServerVersion.api_getByCode)
    @ApiOperation(value = "根据code查找服务器版本", notes = "根据code查找服务器版本")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseVersionContants.BaseServerVersion.message_success_find, serverVersionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseVersionContants.BaseServerVersion.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取服务器版本列表(分页)")
    public Envelop getWlyyVersions(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,versionInt,remark")
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
        List<BaseServerVersion> list = serverVersionService.search(fields, filters, sorts, page, size);

        //查找是否存在功能版本
        for(BaseServerVersion baseServerVersion:list){
            List<BaseServerUrlVersion> serverUrlVersions = serverUrlVersionService.findByServer(baseServerVersion.getCode());
            if(serverUrlVersions.size()>0){
                baseServerVersion.setState("closed");
            }else{
                baseServerVersion.setState("open");
            }
        }

        //获取总数
        long count=serverVersionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MBaseServerVersion> mWlyyVersions = convertToModels(list, new ArrayList<>(list.size()), MBaseServerVersion.class, fields);

        return Envelop.getSuccessListWithPage(BaseVersionContants.BaseServerVersion.message_success_find_BaseServerVersions,mWlyyVersions, page, size,count);
    }


    @GetMapping(value = BaseVersionContants.BaseServerVersion.api_getListNoPage)
    @ApiOperation(value = "获取服务器版本列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseUserUrlVersion> list = serverVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<BaseUserUrlVersion> userVersion = convertToModels(list, new ArrayList<>(list.size()), BaseUserUrlVersion.class, fields);
        return Envelop.getSuccessList(BaseVersionContants.BaseServerVersion.message_success_find,userVersion);
    }
}
