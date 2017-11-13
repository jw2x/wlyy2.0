package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.wx.WechatContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.wx.WechatMenuFeign;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@RestController
@RequestMapping( WechatContants.api_common)
@Api(description = "微信菜单配置")
public class WechatMenuController {

    private Logger logger= LoggerFactory.getLogger(WechatMenuController.class);

    @Autowired
    private WechatMenuFeign wechatMenuFegin;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = WechatContants.Menu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加微信菜单", notes = "添加微信菜单")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("创建微信菜单:jsonData="+jsonData);
        //{"id":null,"code":"","saasId":"1","name":"aaaawefr","token":"","encodingAesKey":"","encType":null,"status":0,"type":"1","appId":"","appSecret":"","baseUrl":"","createUser":"","createUserName":"","createTime":null,"updateUser":null,"updateUserName":null,"updateTime":null,"remark":""}
        return wechatMenuFegin.createWxMenu(jsonData);
    }


    @PutMapping(value = WechatContants.Menu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信菜单", notes = "修改微信菜单")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop updateWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("更新微信菜单:jsonData="+jsonData);
        return wechatMenuFegin.updateWxMenu(jsonData);
    }

    @DeleteMapping(value = WechatContants.Menu.api_delete)
    @ApiOperation(value = "删除微信菜单", notes = "删除微信菜单")
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
        return wechatMenuFegin.deleteWxMenu(codes,userCode,userName);
    }

    @GetMapping(value = WechatContants.Menu.api_getById)
     @ApiOperation(value = "根据code查找微信菜单", notes = "根据code查找微信菜单")
     @HystrixCommand(commandProperties = {
             @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
             @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
     public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
        return wechatMenuFegin.findById(id);
    }

    @RequestMapping(value = WechatContants.Menu.api_getWxMenus, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信菜单列表(分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWxMenus(
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
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("name")){
                filterStr+="name?"+jsonResult.get("name")+";";
            }
            if(jsonResult.has("saasId")){
                filterStr+="saasId="+jsonResult.get("saasId")+";";
            }
        }
        return wechatMenuFegin.getWxMenus(fields,filterStr,sorts,size,page);
    }


    @GetMapping(value = WechatContants.Menu.api_getWxMenuNoPage)
    @ApiOperation(value = "获取微信菜单列表，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWxMenuNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wechatMenuFegin.getWxMenuNoPage(fields,filters,sorts);
    }

    /**
     * 创建微信公众号菜单
     *
     * @return
     */
    @ApiOperation(value = "创建微信公众号菜单", notes = "创建微信公众号菜单")
    @RequestMapping(value = WechatContants.Menu.api_createMenu ,method = RequestMethod.GET)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWechatMenu(
            @ApiParam(name = "wechatCode", value = "", defaultValue = "")
            @RequestParam(value = "wechatCode", required = true)String wechatCode){
        return wechatMenuFegin.createWechatMenu(wechatCode);
    }

    @GetMapping(value = WechatContants.Menu.api_getParentMenu)
    @ApiOperation(value = "根据微信code查找父菜单", notes = "根据微信code查找父菜单")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getParentMenu(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @PathVariable(value = "wechatCode", required = true) String wechatCode
    ) throws JiWeiException {
        return wechatMenuFegin.getParentMenu(wechatCode);
    }

    @GetMapping(value = WechatContants.Menu.api_getChildMenus)
    @ApiOperation(value = "根据parentCode查找子菜单", notes = "根据parentCode查找子菜单")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getChildMenus(
            @ApiParam(name = "parentCode", value = "parentCode")
            @PathVariable(value = "parentCode", required = true) String parentCode
    ) throws JiWeiException {
        return wechatMenuFegin.getChildMenus(parentCode);
    }
}
