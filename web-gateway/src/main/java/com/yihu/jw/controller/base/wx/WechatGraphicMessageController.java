package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.base.wx.WechatContants;
import com.yihu.jw.fegin.base.wx.GraphicMessageFegin;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@RestController
@RequestMapping(WechatContants.GraphicMessage.api_common)
@Api(description = "微信图文相关")
public class WechatGraphicMessageController {
    private Logger logger= LoggerFactory.getLogger(WechatGraphicMessageController.class);

    @Autowired
    private GraphicMessageFegin graphicMessageFegin;

    @Autowired
    private Tracer tracer;

    @PostMapping(value = WechatContants.GraphicMessage.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信图文消息", notes = "创建微信图文消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return graphicMessageFegin.createWxGraphicMessage(jsonData);
    }


    @PutMapping(value = WechatContants.GraphicMessage.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信图文消息", notes = "修改微信图文消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop updateWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return graphicMessageFegin.updateWxGraphicMessage(jsonData);
    }


    @DeleteMapping(value = WechatContants.GraphicMessage.api_delete)
    @ApiOperation(value = "删除微信图文消息", notes = "删除微信图文消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop deleteWxGraphicMessage(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        return graphicMessageFegin.deleteWxGraphicMessage(code);
    }

    @GetMapping(value = WechatContants.GraphicMessage.api_getByCode)
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        return graphicMessageFegin.findByCode(code);
    }

    @RequestMapping(value = WechatContants.GraphicMessage.api_getWxGraphicMessages, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信图文消息列表(分页)")
    public Envelop getWxGraphicMessages(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,description,url,pic_url,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return graphicMessageFegin.getWxGraphicMessages(fields,filters,sorts,size,page);
    }


    @GetMapping(value = WechatContants.GraphicMessage.api_getWxGraphicMessageNoPage)
    @ApiOperation(value = "获取图文消息列表，不分页")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWxGraphicMessageNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,description,url,pic_url,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        return graphicMessageFegin.getWxGraphicMessageNoPage(fields,filters,sorts);
    }

    @GetMapping(value = WechatContants.GraphicMessage.api_sendGraphicMessages)
    @ApiOperation(value = "发送图文消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @ResponseBody
    public String sendGraphicMessages(
            @ApiParam(name = "codes", value = "根据code发送微信图文消息,多个code用,分割")
            @RequestParam(value = "codes", required = true) String codes,
            @ApiParam(name = "fromUserName", value = "用户openid")
            @RequestParam(value = "fromUserName", required = true) String fromUserName,
            @ApiParam(name = "toUserName", value = "公众号")
            @RequestParam(value = "toUserName", required = true) String toUserName
    ) throws Exception {
        return graphicMessageFegin.sendGraphicMessages(codes,fromUserName,toUserName);
    }
}
