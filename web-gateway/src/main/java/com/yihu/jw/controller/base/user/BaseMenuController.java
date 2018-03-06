package com.yihu.jw.controller.base.user;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.common.base.user.BaseUserContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.user.BaseMenuFeign;
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
 * Created by LiTaohong on 2017/11/28.
 */

@RestController
@RequestMapping(BaseUserContants.api_baseMenu)
@Api(value = "基础菜单管理", description = "基础菜单管理")
public class BaseMenuController extends EnvelopRestController {

    @Autowired
    private BaseMenuFeign fegin;
    @Autowired
    private Tracer tracer;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PostMapping(value = BaseUserContants.BaseMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建菜单", notes = "创建菜单")
    public Envelop createBaseMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.create(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @PutMapping(value = BaseUserContants.BaseMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "更新菜单", notes = "更新菜单")
    public Envelop updateBaseMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        return fegin.update(jsonData);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @DeleteMapping(value = BaseUserContants.BaseMenu.api_delete)
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    public Envelop deleteBaseMenu(
            @ApiParam(name = "id", value = "uuid")
            @RequestParam(value = "id", required = true) String id
    ) throws JiWeiException {
        return fegin.delete(id);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @GetMapping(value = BaseUserContants.BaseMenu.api_getById)
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
    @GetMapping(value = BaseUserContants.BaseMenu.api_children)
    @ApiOperation(value = "获取子菜单", notes = "根据父菜单id查找")
    public Envelop getChildren(@ApiParam(name = "saasId", value = "saasId") @RequestParam(value = "saasId", required = true) String saasId,
                               @ApiParam(name = "parentId", value = "parentId") @RequestParam(value = "parentId", required = true) String parentId) throws JiWeiException {
        return fegin.getChildren(saasId,parentId);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @RequestMapping(value = BaseUserContants.BaseMenu.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单列表(分页)")
    public Envelop getBaseMenus(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,saasId,createUser,remark")
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
    @GetMapping(value = BaseUserContants.BaseMenu.api_getListNoPage)
    @ApiOperation(value = "获取菜单列表，不分页")
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,name,saasId,createUser,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("saasId")){
                filterStr+="saasId="+jsonResult.get("saasId")+";";
            }
        }
        return fegin.getListNoPage(fields, filterStr, sorts);
    }


}
