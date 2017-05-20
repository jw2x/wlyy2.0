package com.yihu.jw.base.controller;

import com.yihu.jw.base.model.Module;
import com.yihu.jw.base.service.ModuleService;
import com.yihu.jw.base.service.ModuleService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.base.MModule;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
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
@RequestMapping(BaseContants.Module.api_common)
@Api(value = "模块模块", description = "模块接口管理")
public class ModuleController extends EnvelopRestController {
    @Autowired
    private ModuleService moduleService;

    @PostMapping(value = BaseContants.Module.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建模块", notes = "创建单个模块")
    public Envelop createModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Module module = toEntity(jsonData, Module.class);
            return Envelop.getSuccess(BaseContants.Module.message_success_create, moduleService.createModule(module));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.Module.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改模块", notes = "修改模块")
    public Envelop updateModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Module module = toEntity(jsonData, Module.class);
            return Envelop.getSuccess(BaseContants.Module.message_success_update, moduleService.updateModule(module));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseContants.Module.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除模块", notes = "删除模块")
    public Envelop deleteModule(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            moduleService.deleteModule(code);
            return Envelop.getSuccess(BaseContants.Module.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseContants.Module.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找模块", notes = "根据code查找模块")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseContants.Module.message_success_find, moduleService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseContants.Module.api_getModules, method = RequestMethod.GET)
    @ApiOperation(value = "获取模块列表(分页)")
    public Envelop getModules(
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
        List<Module> list = moduleService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=moduleService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MModule> mModules = convertToModels(list, new ArrayList<>(list.size()), MModule.class, fields);

        return Envelop.getSuccessListWithPage(BaseContants.Module.message_success_find_Modules,mModules, page, size,count);
    }


    @GetMapping(value = BaseContants.Module.api_getModulesNoPage)
    @ApiOperation(value = "获取模块列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<Module> list = moduleService.search(fields,filters,sorts);
        //封装返回格式
        List<MModule> mModules = convertToModels(list, new ArrayList<>(list.size()), MModule.class, fields);
        return Envelop.getSuccessList(BaseContants.Module.message_success_find_Modules,mModules);
    }
}
