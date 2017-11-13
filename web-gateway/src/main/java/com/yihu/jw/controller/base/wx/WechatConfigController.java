package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.wx.WechatContants;
import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.wx.WechatFeign;
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


@RestController
@RequestMapping(WechatContants.api_common)
@Api(description = "微信配置")
public class WechatConfigController {

    private Logger logger= LoggerFactory.getLogger(WechatConfigController.class);
    @Autowired
    private WechatFeign wechatFegin;

    @Autowired
    private Tracer tracer;

    @ApiOperation(value = "创建微信配置")
    @PostMapping(value = WechatContants.Config.api_create,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("创建微信配置:jsonData="+jsonData);
        //{"id":null,"code":"","saasId":"1","name":"aaaawefr","token":"","encodingAesKey":"","encType":null,"status":0,"type":"1","appId":"","appSecret":"","baseUrl":"","createUser":"","createUserName":"","createTime":null,"updateUser":null,"updateUserName":null,"updateTime":null,"remark":""}
        Envelop wechat =wechatFegin.createWechat(jsonData);
        tracer.getCurrentSpan().logEvent("创建微信配置微服务结束");
        return wechat;
    }

    @ApiOperation(value = "更新微信配置")
    @PutMapping(value = WechatContants.Config.api_update,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop updateWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("更新微信配置:jsonData="+jsonData);
        Envelop wechat =wechatFegin.updateWechat(jsonData);
        return wechat;
    }

    @DeleteMapping(value = WechatContants.Config.api_delete)
    @ApiOperation(value = "删除微信配置", notes = "删除微信配置")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop deleteWechat(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) throws JiWeiException {
        Envelop wechat =wechatFegin.deleteWechat(codes,userCode,userName);
        return wechat;
    }

    @GetMapping(value = WechatContants.Config.api_getById)
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @PathVariable(value = "id", required = true) String id
    ) throws JiWeiException {
       return wechatFegin.findById(id);
    }

    @RequestMapping(value = WechatContants.Config.api_getWechats, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信配置列表(分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWechats(
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
        Envelop envelop = wechatFegin.getWechats(fields,filterStr,sorts,size,page);
        return envelop;
    }

    @GetMapping(value = WechatContants.Config.api_getWechatNoPage)
    @ApiOperation(value = "获取微信列表配置，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWechatNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return wechatFegin.getWechatNoPage(fields,filters,sorts);
    }

}
