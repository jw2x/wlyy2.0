package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.MWxGraphicMessage;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.model.WxGraphicMessage;
import com.yihu.jw.wx.service.WxGraphicMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(WxContants.WxGraphicMessage.api_common)
@Api(value = "微信图文相关操作", description = "微信图文相关操作")
public class WxGraphicMessageController extends EnvelopRestController {
    @Autowired
    private WxGraphicMessageService wxGraphicMessageService;

    @PostMapping(value = WxContants.WxGraphicMessage.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信图文消息", notes = "创建微信图文消息")
    public Envelop createWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxGraphicMessage WxGraphicMessage = toEntity(jsonData, WxGraphicMessage.class);
            return Envelop.getSuccess(WxContants.WxGraphicMessage.message_success_create, wxGraphicMessageService.createWxGraphicMessage(WxGraphicMessage));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WxContants.WxGraphicMessage.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信图文消息", notes = "修改微信图文消息")
    public Envelop updateWxGraphicMessage(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxGraphicMessage WxGraphicMessage = toEntity(jsonData, WxGraphicMessage.class);
            return Envelop.getSuccess(WxContants.WxGraphicMessage.message_success_update, wxGraphicMessageService.updateWxchat(WxGraphicMessage));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WxContants.WxGraphicMessage.api_delete)
    @ApiOperation(value = "删除微信图文消息", notes = "删除微信图文消息")
    public Envelop deleteWxGraphicMessage(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wxGraphicMessageService.deleteWxGraphicMessage(code);
            return Envelop.getSuccess(WxContants.WxGraphicMessage.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WxContants.WxGraphicMessage.api_getByCode)
    @ApiOperation(value = "根据code查找微信图文消息", notes = "根据code查找微信图文消息")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WxContants.WxGraphicMessage.message_success_find, wxGraphicMessageService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WxContants.WxGraphicMessage.api_getWxGraphicMessages, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信图文消息列表(分页)")
    public Envelop getWxGraphicMessages(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,wechatCode,value,keyword,remark,status")
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
        //得到list数据
        List<WxGraphicMessage> list = wxGraphicMessageService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wxGraphicMessageService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxGraphicMessage> mWxGraphicMessages = convertToModels(list, new ArrayList<>(list.size()), MWxGraphicMessage.class, fields);

        return Envelop.getSuccessListWithPage(WxContants.WxGraphicMessage.message_success_find_functions,mWxGraphicMessages, page, size,count);
    }


    @GetMapping(value = WxContants.WxGraphicMessage.api_getWxGraphicMessageNoPage)
    @ApiOperation(value = "获取图文消息列表，不分页")
    public Envelop getWxGraphicMessageNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,wechatCode,value,keyword,remark,status")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WxGraphicMessage> list = wxGraphicMessageService.search(fields,filters,sorts);
        //封装返回格式
        List<MWxGraphicMessage> mWxGraphicMessages = convertToModels(list, new ArrayList<>(list.size()), MWxGraphicMessage.class, fields);
        return Envelop.getSuccessList(WxContants.WxGraphicMessage.message_success_find_functions,mWxGraphicMessages);
    }

}
