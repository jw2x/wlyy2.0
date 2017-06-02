package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.base.wx.WechatFegin;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(WechatContants.Config.api_common)
@Api(description = "微信配置")
public class WechatController{

    private Logger logger= LoggerFactory.getLogger(WechatController.class);
    @Autowired
    private WechatFegin wechatFegin;

    @Autowired
    private Tracer tracer;

    @ApiOperation(value = "创建微信配置")
    @PostMapping(value = WechatContants.Config.api_create)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) {
        tracer.getCurrentSpan().logEvent("开始调用微服务创建微信配置");
        Envelop wechat =wechatFegin.createWechat(jsonData);
        tracer.getCurrentSpan().logEvent("创建微信配置微服务结束");
        return wechat;
    }

    @ApiOperation(value = "更新微信配置")
    @PutMapping(value = WechatContants.Config.api_update)
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop updateWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        Envelop wechat =wechatFegin.updateWechat(jsonData);
        return wechat;
    }

    @DeleteMapping(value = WechatContants.Config.api_delete)
    @ApiOperation(value = "删除微信配置", notes = "删除微信配置")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop deleteWechat(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        Envelop wechat =wechatFegin.deleteWechat(code);
        return wechat;
    }

    @GetMapping(value = WechatContants.Config.api_getByCode)
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
       return wechatFegin.findByCode(code);
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
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Envelop envelop = wechatFegin.getWechats(fields,filters,sorts,size,page);
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
