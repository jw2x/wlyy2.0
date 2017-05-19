package com.yihu.jw.base.controller;

import com.yihu.jw.base.model.Function;
import com.yihu.jw.base.service.FunctionService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.base.MFunction;
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
import java.util.Collection;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseContants.Function.api_common)
@Api(value = "功能模块", description = "功能模块接口管理")
public class FunctionController extends EnvelopRestController {
    @Autowired
    private FunctionService functionService;

    @PostMapping(value = BaseContants.Function.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建功能", notes = "创建单个功能")
    public Envelop createFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseContants.Function.message_success_create, functionService.createFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.Function.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改功能", notes = "修改功能")
    public Envelop updateFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseContants.Function.message_success_update, functionService.updateFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseContants.Function.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除功能", notes = "删除功能")
    public Envelop deleteFunction(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            functionService.deleteFunction(code);
            return Envelop.getSuccess(BaseContants.Function.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseContants.Function.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找功能", notes = "根据code查找功能")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseContants.Function.message_success_find, functionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseContants.Function.api_getFunctions, method = RequestMethod.GET)
    @ApiOperation(value = "获取功能列表(分页)")
    public Envelop getFunctions(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
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
        List<Function> list = functionService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=functionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MFunction> mFunctions = convertToModels(list, new ArrayList<>(list.size()), MFunction.class, fields);

        return Envelop.getSuccessListWithPage(BaseContants.Function.message_success_find_functions,mFunctions, page, size,count);
    }


    @GetMapping(value = BaseContants.Function.api_getFunctionsNoPage)
    @ApiOperation(value = "获取功能列表，不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<Function> list = functionService.search(fields,filters,sorts);
        //封装返回格式
        List<MFunction> mFunctions = convertToModels(list, new ArrayList<>(list.size()), MFunction.class, fields);
        return Envelop.getSuccessList(BaseContants.Function.message_success_find_functions,mFunctions);
    }
}
