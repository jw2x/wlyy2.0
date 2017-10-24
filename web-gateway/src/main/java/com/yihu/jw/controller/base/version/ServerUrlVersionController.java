package com.yihu.jw.controller.base.version;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.base.BaseVersionContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.version.ServerUrlVersionFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.version.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/6/20.
 */
@RestController
@RequestMapping("{version}"+ BaseVersionContants.api_common)
@Api(value = "服务器URL版本模块", description = "服务器URL版本模块接口管理")
public class ServerUrlVersionController {

    @Autowired
    private ServerUrlVersionFegin serverUrlVersionFegin;

    @Autowired
    private Tracer tracer;

    @ApiVersion(1)
    @ApiOperation(value = "创建服务器URL版本")
    @PostMapping(value = BaseVersionContants.BaseServerUrlVersion.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop create(
            @ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("创建服务器URL版本:jsonData="+jsonData);
        return serverUrlVersionFegin.create(jsonData);
    }

    @ApiVersion(1)
    @ApiOperation(value = "更新服务器URL版本")
    @PutMapping(value =  BaseVersionContants.BaseServerUrlVersion.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop update(
            @ApiParam(name = "jsonData", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("更新服务器URL版本:jsonData="+jsonData);
        return serverUrlVersionFegin.update(jsonData);
    }

    @ApiVersion(1)
    @DeleteMapping(value =BaseVersionContants.BaseServerUrlVersion.api_delete)
    @ApiOperation(value = "删除服务器URL版本", notes = "删除服务器URL版本")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop delete(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) throws JiWeiException {
        return serverUrlVersionFegin.delete(codes,userCode,userName);
    }

    @ApiVersion(1)
    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getByCode)
    @ApiOperation(value = "根据code查找服务器URL版本", notes = "根据code查找服务器URL版本")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) throws JiWeiException {
        return serverUrlVersionFegin.findByCode(code);
    }

    @ApiVersion(1)
    @RequestMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getList, method = RequestMethod.GET)
    @ApiOperation(value = "获取服务器URL版本列表(分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getList(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            filters = filters.replaceAll("=", ":");
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("name")){
                filterStr+="name?"+jsonResult.get("name")+";";
            }
        }
        return serverUrlVersionFegin.getList(fields, filterStr, sorts, size, page);
    }

    @ApiVersion(1)
    @GetMapping(value = BaseVersionContants.BaseServerUrlVersion.api_getListNoPage)
    @ApiOperation(value = "获取服务器URL版本，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getListNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            filters = filters.replaceAll("=", ":");
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("serverCode")){
                filterStr+="serverCode="+jsonResult.get("serverCode")+";";
            }
        }
        return serverUrlVersionFegin.getListNoPage(fields, filterStr, sorts);
    }
}
