package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.base.wx.WechatTemplateFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import com.yihu.jw.restmodel.wx.WechatContants;
import com.yihu.jw.version.ApiVersion;
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
@RequestMapping("{version}"+ WechatContants.api_common)
@Api(description = "微信模板消息相关")
public class WechatTemplateControlle {

    private Logger logger= LoggerFactory.getLogger(WechatTemplateControlle.class);

    @Autowired
    private WechatTemplateFegin wechatTemplateFegin;

    @Autowired
    private Tracer tracer;

    @ApiVersion(1)
    @PostMapping(value = WechatContants.WxTemplate.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信模版", notes = "创建微信模版")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop createWxTemplate(
            @ApiParam(name = "json_data", value = "微信模版json字符串")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("创建微信模板:jsonData="+jsonData);
        //{"id":null,"code":"","saasId":"1","name":"aaaawefr","token":"","encodingAesKey":"","encType":null,"status":0,"type":"1","appId":"","appSecret":"","baseUrl":"","createUser":"","createUserName":"","createTime":null,"updateUser":null,"updateUserName":null,"updateTime":null,"remark":""}
        return wechatTemplateFegin.createWxTemplate(jsonData);
    }

    @ApiVersion(1)
    @PutMapping(value = WechatContants.WxTemplate.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信模版", notes = "修改微信模版")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop updateWxTemplate(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws JiWeiException {
        tracer.getCurrentSpan().logEvent("更新模板配置:jsonData="+jsonData);
        return wechatTemplateFegin.updateWxTemplate(jsonData);
    }


    @ApiVersion(1)
    @DeleteMapping(value = WechatContants.WxTemplate.api_delete)
    @ApiOperation(value = "删除微信模版", notes = "删除微信模版")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop deleteWxTemplate(
            @ApiParam(name = "codes", value = "codes")
            @PathVariable(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName) throws JiWeiException {
        return wechatTemplateFegin.deleteWxTemplate(codes,userCode,userName);
    }

    @ApiVersion(1)
    @GetMapping(value = WechatContants.WxTemplate.api_getByCode)
    @ApiOperation(value = "根据code查找微信模版", notes = "根据code查找微信模版")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @PathVariable(value = "code", required = true) String code
    ) throws JiWeiException {
        return wechatTemplateFegin.findByCode(code);
    }

    @ApiVersion(1)
    @RequestMapping(value = WechatContants.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信模版列表(分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getTemplates(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
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
        return wechatTemplateFegin.getWechats(fields,filterStr,sorts,size,page);
    }

    @ApiVersion(1)
    @GetMapping(value = WechatContants.WxTemplate.api_getWxTemplatesNoPage)
    @ApiOperation(value = "获取微信模版列表(不分页)")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getTemplateNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        String filterStr = "";
        if(StringUtils.isNotBlank(filters)){
            JSONObject jsonResult = new JSONObject(filters);
            if(jsonResult.has("wechatCode")){
                filterStr+="wechatCode="+jsonResult.get("wechatCode")+";";
            }
        }
        return wechatTemplateFegin.getWechatNoPage(fields,filterStr,sorts);
    }

    @ApiVersion(1)
    @GetMapping(value = WechatContants.WxTemplate.api_sendTemplateMessage)
    @ApiOperation(value = "发送微信模板消息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    @ResponseBody
    public Envelop sendTemplateMessage(
            @ApiParam(name="openid",value="微信用户的openid")
            @RequestParam String openid,
            @ApiParam(name="templateCode",value = "模板code")
            @RequestParam String templateCode,
            @ApiParam(name="url",value="模板跳转链接")
            @RequestParam(required = false) String url,
            @ApiParam(name="appid",value="所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）")
            @RequestParam(required = false) String appid,
            @ApiParam(name="pagepath",value="所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）")
            @RequestParam(required = false) String pagepath,
            @ApiParam(name="data",value="json字符串")
            @RequestParam String data
    ){
        return wechatTemplateFegin.sendTemplateMessage(openid,templateCode,url,appid,pagepath,data);
    }
}
