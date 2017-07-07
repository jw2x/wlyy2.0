package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.MWxTemplate;
import com.yihu.jw.restmodel.wx.WechatContants;
import com.yihu.jw.wx.WechatResponse;
import com.yihu.jw.wx.model.Miniprogram;
import com.yihu.jw.wx.model.WxTemplate;
import com.yihu.jw.wx.service.WechatService;
import com.yihu.jw.wx.service.WxTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@RestController
@RequestMapping(WechatContants.api_common)
@Api(value = "微信模版相关操作", description = "微信模版相关操作")
public class WxTemplateController extends EnvelopRestController {
    @Autowired
    private WxTemplateService wxTemplateService;

    @Autowired
    private WechatService wechatService;

    @PostMapping(value = WechatContants.WxTemplate.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信模版", notes = "创建微信模版")
    public Envelop createWxTemplate(
            @ApiParam(name = "json_data", value = "微信模版json字符串")
            @RequestBody String jsonData) {
        try {
            WxTemplate WxTemplate = toEntity(jsonData, WxTemplate.class);
            return Envelop.getSuccess(WechatContants.WxTemplate.message_success_create, wxTemplateService.createWxTemplate(WxTemplate));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WechatContants.WxTemplate.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信模版", notes = "修改微信模版")
    public Envelop updateWxTemplate(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxTemplate WxTemplate = toEntity(jsonData, WxTemplate.class);
            return Envelop.getSuccess(WechatContants.WxTemplate.message_success_update, wxTemplateService.updateWxTemplate(WxTemplate));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @DeleteMapping(value = WechatContants.WxTemplate.api_delete)
    @ApiOperation(value = "删除微信模版", notes = "删除微信模版")
    public Envelop deleteWxTemplate(
            @ApiParam(name = "codes", value = "codes")
            @RequestParam(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName
    ) {
        try {
            wxTemplateService.deleteWxTemplate(codes, userCode, userName);
            return Envelop.getSuccess(WechatContants.WxTemplate.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WechatContants.WxTemplate.api_getByCode)
    @ApiOperation(value = "根据code查找微信模版", notes = "根据code查找微信模版")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WechatContants.WxTemplate.message_success_find, wxTemplateService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WechatContants.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信模版列表(分页)")
    public Envelop getWechats(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
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
        if(StringUtils.isBlank(sorts)){
            sorts = "-updateTime";
        }
        //得到list数据
        List<WxTemplate> list = wxTemplateService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wxTemplateService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxTemplate> templates = convertToModels(list, new ArrayList<>(list.size()), MWxTemplate.class, fields);
        Map<String, String> map = wechatService.getAllWechatConfig();
        for(MWxTemplate template:templates){
            template.setWechatName(map.get(template.getWechatCode()));
        }

        return Envelop.getSuccessListWithPage(WechatContants.WxTemplate.message_success_find_functions,templates, page, size,count);
    }


    @GetMapping(value = WechatContants.WxTemplate.api_getWxTemplatesNoPage)
    @ApiOperation(value = "获取微信模版列表(不分页)")
    public Envelop getWechatNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,wechatCode,templateId,content,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WxTemplate> list = wxTemplateService.search(fields,filters,sorts);
        //封装返回格式
        List<MWxTemplate> mMWxTemplates = convertToModels(list, new ArrayList<>(list.size()), MWxTemplate.class, fields);
        Map<String, String> map = wechatService.getAllWechatConfig();
        for(MWxTemplate template:mMWxTemplates){
            template.setWechatName(map.get(template.getWechatCode()));
        }
        return Envelop.getSuccessList(WechatContants.WxTemplate.message_success_find_functions,mMWxTemplates);
    }

    @GetMapping(value = WechatContants.WxTemplate.api_sendTemplateMessage)
    @ApiOperation(value = "发送微信模板消息")
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
        try {
            Miniprogram miniprogram = null;
            if(StringUtils.isNotBlank(appid)&&StringUtils.isNotBlank(pagepath)){
                miniprogram = new Miniprogram();
                miniprogram.setAppid(appid);
                miniprogram.setPagepath(pagepath);
            }
            JSONObject jsonObject = wxTemplateService.sendTemplateMessage(openid, templateCode, url, data, miniprogram);
            String errcode = jsonObject.get("errcode").toString();
            WechatResponse wechatResponse = new WechatResponse(Integer.valueOf(errcode));
            String msg = wechatResponse.getMsg();
            return Envelop.getSuccess("成功",msg);
        }catch (Exception exception) {
            return Envelop.getSuccess("error", exception);
        }
    }
}
