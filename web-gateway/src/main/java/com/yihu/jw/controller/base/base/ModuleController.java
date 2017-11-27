package com.yihu.jw.controller.base.base;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.base.BaseContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.base.ModuleFeign;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenweida on 2017/6/16.
 */

@RestController
@RequestMapping( BaseContants.api_module)
@Api(value = "模块管理", description = "模块相关接口管理")
public class ModuleController extends EnvelopRestController {

    @Autowired
    private ModuleFeign fegin;
    @Autowired
    private Tracer tracer;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PostMapping(value = BaseContants.Module.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建功能", notes = "创建功能")
    public Envelop createModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PutMapping(value = BaseContants.Module.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新功能", notes = "更新功能")
    public Envelop updateModule(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.update(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @DeleteMapping(value = BaseContants.Module.api_delete)
    @ApiOperation(value = "删除功能", notes = "删除功能")
    public Envelop deleteModule(
            @ApiParam(name = "ids", value = "ids")
            @PathVariable(value = "ids", required = true) String ids,
            @ApiParam(name = "userId", value = "userId")
            @RequestParam(value = "userId", required = true) String userId,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) throws JiWeiException {
        return fegin.delete(ids,userId,userName);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseContants.Module.api_getById)
    @ApiOperation(value = "根据id查找", notes = "根据id查找")
    public Envelop findById(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
        return fegin.findById(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseContants.Module.api_getChildren)
    @ApiOperation(value = "根据code查找子节点", notes = "根据code查找子节点")
    public Envelop getChildren(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
        return fegin.getChildren(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @RequestMapping(value = BaseContants.Module.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取功能列表(分页)")
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
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("name")){
                filterStr+="name?"+jsonResult.get("name")+";";
            }
        }
        return fegin.getList(fields, filterStr, sorts, size, page);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseContants.Module.api_getListNoPage)
    @ApiOperation(value = "获取功能列表，不分页")
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "code,name,saasId,parentCode,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            filters = filters.replaceAll("=", ":");
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("saasId")){
                filterStr+="saasId="+jsonResult.get("saasId")+";";
            }
        }
        return fegin.getListNoPage(fields, filterStr, sorts);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseContants.ModuleFun.api_getExistFun)
    @ApiOperation(value = "根据模块code查找已有的功能")
    public Envelop getExistFun(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
        return fegin.getExistFunc(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PutMapping(value = BaseContants.ModuleFun.api_changeFun, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新功能", notes = "更新功能")
    public Envelop changeFun(
            @ApiParam(name = "jsonData")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.changeFun(jsonData);
    }

}
