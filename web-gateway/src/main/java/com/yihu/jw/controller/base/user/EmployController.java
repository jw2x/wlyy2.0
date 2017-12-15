package com.yihu.jw.controller.base.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.user.BaseUserContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.user.EmployFeign;
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
 * Created by LiTaohong on 2017/11/30.
 */

@RestController
@RequestMapping(BaseUserContants.api_employ)
@Api(value = "用戶管理", description = "用戶管理")
public class EmployController extends EnvelopRestController {

    @Autowired
    private EmployFeign fegin;

    @Autowired
    private Tracer tracer;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PostMapping(value = BaseUserContants.Employee.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建用戶", notes = "创建用戶")
    public Envelop createFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PutMapping(value = BaseUserContants.Employee.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新用戶", notes = "更新用戶")
    public Envelop updateFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.update(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @DeleteMapping(value = BaseUserContants.Employee.api_delete)
    @ApiOperation(value = "删除用戶", notes = "删除用戶")
    public Envelop deleteBaseRole(
            @ApiParam(name = "id", value = "uuid")
            @RequestParam(value = "id", required = true) String id
            ) throws JiWeiException {
        return fegin.delete(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseUserContants.Employee.api_getById)
    @ApiOperation(value = "根据Id查找", notes = "根据uuid查找")
    public Envelop findById(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
        return fegin.findById(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @RequestMapping(value = BaseUserContants.Employee.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取功能列表(分页)")
    public Envelop getEmployList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,saasId,sex,phone,email,jxzc,lczcn,xlzc,xzzc,createUser,remark")
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
    @PostMapping(value = BaseUserContants.EmployeeRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增用戶角色", notes = "新增用戶角色")
    public Envelop createEmployRoles(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PutMapping(value = BaseUserContants.EmployeeRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改用戶角色", notes = "修改用戶角色")
    public Envelop updateEmployRoles(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @DeleteMapping(value = BaseUserContants.EmployeeRole.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除用戶角色", notes = "删除用戶角色")
    public Envelop deleteEmployRoles(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseUserContants.EmployeeRole.api_getListNoPage)
    @ApiOperation(value = "获取用戶角色列表，不分页")
    public Envelop getRolesListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,saasId,sex,phone,email,jxzc,lczcn,xlzc,xzzc,createUser,remark") @RequestParam(value = "fields", required = false) String fields) throws Exception {
        return fegin.getRoleList(fields);
    }

}
