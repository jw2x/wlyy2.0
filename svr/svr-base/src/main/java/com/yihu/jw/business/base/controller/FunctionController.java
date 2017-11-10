package com.yihu.jw.business.base.controller;

import com.yihu.jw.base.base.Function;
import com.yihu.jw.business.base.service.FunctionService;
import com.yihu.jw.business.base.service.ModuleFunService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.base.base.MFunction;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(BaseRequestMapping.api_common)
@Api(value = "功能模块", description = "功能模块接口管理")
public class FunctionController extends EnvelopRestController {
    @Autowired
    private FunctionService functionService;
    @Autowired
    private ModuleFunService moduleFunService;

    @PostMapping(value = BaseRequestMapping.Function.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建功能", notes = "创建单个功能")
    public Envelop createFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_create, functionService.createFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseRequestMapping.Function.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改功能", notes = "修改功能")
    public Envelop updateFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_update, functionService.updateFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
    @DeleteMapping(value = BaseRequestMapping.Function.api_delete)
    @ApiOperation(value = "删除功能", notes = "删除功能")
    public Envelop deleteFunction(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes) {
        try {
            functionService.deleteFunction(codes);
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseRequestMapping.Function.api_getByCode)
    @ApiOperation(value = "根据code查找功能", notes = "根据code查找功能")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_find, functionService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @RequestMapping(value = BaseRequestMapping.Function.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取父功能列表(分页)")
    public Envelop getFunctions(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,url,parentCode,status,remark")
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

        if(StringUtils.isBlank(sorts)){
            sorts = "-updateTime";
        }
       if(StringUtils.isBlank(filters)){
            filters = "parentCode=0;";
        }else{
            filters="parentCode=0;"+filters;
        }

        //得到list数据
        List<Function> list = functionService.search(fields, filters, sorts, page, size);
        if(list!=null){
            for(Function func:list){//循环遍历,设置是否有子节点
                List<Function> children = functionService.getChildren(func.getId());
                func.setChildren(children);
            }
        }

        //获取总数
        long count=functionService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MFunction> mFunctions = convertToModels(list, new ArrayList<>(list.size()), MFunction.class, fields);

        return Envelop.getSuccessListWithPage(BaseRequestMapping.Function.message_success_find_functions,mFunctions, page, size,count);
    }


    @GetMapping(value = BaseRequestMapping.Function.api_getListNoPage)
    @ApiOperation(value = "获取功能列表，并且封装成jstree,不分页")
    public Envelop getAppsNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<Function> list = functionService.search(fields,filters,sorts);
        List<Object> functions = new ArrayList<>();
        if(list!=null){
            for(Function func:list){
                String code = func.getId();
                func = functionService.getAllChildren(code);
                functions.add(func);
            }
        }
        //封装返回格式
        List<MFunction> mFunctions = convertToModels(functions, new ArrayList<>(functions.size()), MFunction.class, fields);
        return Envelop.getSuccessList(BaseRequestMapping.Function.message_success_find_functions,mFunctions);
    }

    @PutMapping(value = BaseRequestMapping.Function.api_assignFunction, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "给对应的模块分配功能")
    public Envelop assignModule(
            @ApiParam(name = "module_code", value = "module_code", defaultValue = "")
            @RequestParam String moduleCode,
            @ApiParam(name = "functionCodes", value = "功能的code，可以传多个，逗号分割", defaultValue = "")
            @RequestParam String functionCodes) {
        try {
            functionService.assignFunction(moduleCode,functionCodes);
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_assign_function);
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseRequestMapping.Function.api_getModuleFunctions, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询saas的模块")
    public Envelop getModuleFunctions(
            @ApiParam(name = "saas_code", value = "saas_code", defaultValue = "")
            @RequestParam String saasCode) {
        try {
            return Envelop.getSuccess(BaseRequestMapping.Function.message_success_find_functions_module,functionService.getModuleFunctions(saasCode));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value =BaseRequestMapping.Function.api_getChildren )
    @ApiOperation(value="查找子节点")
    public Envelop getChildren(@PathVariable String code){
        List<Function> children = functionService.getChildren(code);
        return Envelop.getSuccess("查询成功",children);
    }
}
