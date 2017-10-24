package com.yihu.jw.business.version.controller;

import com.yihu.jw.business.version.model.BaseUserUrlVersion;
import com.yihu.jw.business.version.service.UserUrlVersionService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseVersionRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
@RestController
@RequestMapping(BaseVersionRequestMapping.api_common)
@Api(value = "后台用户版本模块", description = "后台用户版本管理模块")
public class UserUrlVersionController extends EnvelopRestController {

    @Autowired
    private UserUrlVersionService userUrlVersionService;

    @GetMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_getListNoPage)
    @ApiOperation(value = "获取后台用户版本列表，不分页")
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<BaseUserUrlVersion> list = userUrlVersionService.search(fields,filters,sorts);
        //封装返回格式
        List<BaseUserUrlVersion> versionList = convertToModels(list, new ArrayList<>(list.size()), BaseUserUrlVersion.class, fields);
        return Envelop.getSuccessList(BaseVersionRequestMapping.BaseServerVersion.message_success_find_BaseServerVersions,versionList);
    }

    @GetMapping(value = BaseVersionRequestMapping.UserUrlVersion.api_changeUserVersion)
    @ApiOperation(value = "更改后台用户版本")
    public Envelop changeUserVersion(
            @ApiParam(name = "serverCode",value="后台版本code")
            @RequestParam(value = "serverCode") String serverCode,
            @ApiParam(name = "userCodes", value = "更改的用户codes")
            @RequestParam(value = "userCodes") String userCodes,
            @ApiParam(name = "userCode", value = "修改人code")
            @RequestParam(value = "userCode") String userCode,
            @ApiParam(name = "userName", value = "修改人")
            @RequestParam(value = "userName") String userName,
            @ApiParam(name = "saasId")
            @RequestParam(value = "saasId") String saasId
    ) {

        userUrlVersionService.changeUserVersion(serverCode, userCodes, userCode,userName,saasId);
        return Envelop.getSuccess(BaseVersionRequestMapping.UserUrlVersion.message_success_update,null);
    }

}
