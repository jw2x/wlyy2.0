package com.yihu.jw.business.wx.controller;

import com.yihu.jw.base.wx.WxGraphicMessage;
import com.yihu.jw.business.wx.service.WxGraphicMessageService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.base.wx.MWxGraphicMessage;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *   2017/5/11.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
@Api(value = "微信图文相关操作", description = "微信图文相关操作")
public class WxGraphicMessageController extends EnvelopRestController {
    @Autowired
    private WxGraphicMessageService wxGraphicMessageService;

    @PostMapping(value = WechatRequestMapping.WxGraphicMessage.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信图文消息", notes = "创建微信图文消息")
    public Envelop createWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxGraphicMessage WxGraphicMessage = toEntity(jsonData, WxGraphicMessage.class);
            return Envelop.getSuccess(WechatRequestMapping.WxGraphicMessage.message_success_create, wxGraphicMessageService.createWxGraphicMessage(WxGraphicMessage));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @PutMapping(value = WechatRequestMapping.WxGraphicMessage.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信图文消息", notes = "修改微信图文消息")
    public Envelop updateWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxGraphicMessage WxGraphicMessage = toEntity(jsonData, WxGraphicMessage.class);
            return Envelop.getSuccess(WechatRequestMapping.WxGraphicMessage.message_success_update, wxGraphicMessageService.updateWxGraphicMessage(WxGraphicMessage));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WechatRequestMapping.WxGraphicMessage.api_delete)
    @ApiOperation(value = "删除微信图文消息", notes = "删除微信图文消息")
    public Envelop deleteWxGraphicMessage(
            @ApiParam(name = "codes", value = "codes")
            @RequestParam(value = "codes", required = true) String codes,
            @ApiParam(name = "userCode", value = "userCode")
            @RequestParam(value = "userCode", required = true) String userCode,
            @ApiParam(name = "userName", value = "userName")
            @RequestParam(value = "userName", required = true) String userName
    ) {
        try {
            wxGraphicMessageService.deleteWxGraphicMessage(codes, userCode, userName);
            return Envelop.getSuccess(WechatRequestMapping.WxGraphicMessage.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WechatRequestMapping.WxGraphicMessage.api_getById)
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCode(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) String id
    ) {
        try {
            return Envelop.getSuccess(WechatRequestMapping.WxGraphicMessage.message_success_find, wxGraphicMessageService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessages, method = RequestMethod.GET)
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
        if(StringUtils.isBlank(sorts)){
            sorts = "-updateTime";
        }
        //得到list数据
        List<WxGraphicMessage> list = wxGraphicMessageService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wxGraphicMessageService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxGraphicMessage> mWxGraphicMessages = convertToModels(list, new ArrayList<>(list.size()), MWxGraphicMessage.class, fields);

        return Envelop.getSuccessListWithPage(WechatRequestMapping.WxGraphicMessage.message_success_find_functions,mWxGraphicMessages, page, size,count);
    }


    @GetMapping(value = WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessageNoPage)
    @ApiOperation(value = "获取图文消息列表，不分页")
    public Envelop getWxGraphicMessageNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,title,description,url,pic_url,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+title,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WxGraphicMessage> list = wxGraphicMessageService.search(fields,filters,sorts);
        //封装返回格式
        List<MWxGraphicMessage> mWxGraphicMessages = convertToModels(list, new ArrayList<>(list.size()), MWxGraphicMessage.class, fields);
        return Envelop.getSuccessList(WechatRequestMapping.WxGraphicMessage.message_success_find_functions,mWxGraphicMessages);
    }

    //@GetMapping(value = WlyyContants.WxGraphicMessage.api_sendGraphicMessages)
    //@ApiOperation(value = "发送图文消息")
    //public Envelop sendGraphicMessages(
    //        @ApiParam(name = "codes", value = "根据code发送微信图文消息,多个code用,分割")
    //        @RequestParam(value = "codes", required = true) String codes,HttpServletRequest request) throws Exception {
    //    String messages = wxGraphicMessageService.sendGraphicMessages(codes, request);
    //    return Envelop.getSuccess("成功",messages);
    //}

    @GetMapping(value = WechatRequestMapping.WxGraphicMessage.api_sendGraphicMessages)
    @ApiOperation(value = "发送图文消息")
    @ResponseBody
    public String sendGraphicMessages(
            @ApiParam(name = "ids", value = "根据code发送微信图文消息,多个code用,分割")
            @RequestParam(value = "ids", required = true) String ids,
            @ApiParam(name = "fromUserName", value = "用户openid")
            @RequestParam(value = "fromUserName", required = true) String fromUserName,
            @ApiParam(name = "toUserName", value = "公众号")
            @RequestParam(value = "toUserName", required = true) String toUserName
            ) throws Exception {
        String messages = wxGraphicMessageService.sendGraphicMessages(ids, fromUserName,toUserName);
        return messages;
    }


}
