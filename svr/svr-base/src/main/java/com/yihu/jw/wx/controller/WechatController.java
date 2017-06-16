package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.restmodel.wx.MWxWechat;
import com.yihu.jw.restmodel.wx.WxContants;
import com.yihu.jw.wx.model.WxWechat;
import com.yihu.jw.wx.service.WechatService;
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
@RequestMapping(WxContants.Wechat.api_common)
@Api(value = "微信相关操作", description = "微信相关操作")
public class WechatController extends EnvelopRestController {
    @Autowired
    private WechatService wechatService;

    @PostMapping(value = WxContants.Wechat.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信配置", notes = "创建微信配置")
    public Envelop createWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxWechat wechat = toEntity(jsonData, WxWechat.class);
            return Envelop.getSuccess(WxContants.Wechat.message_success_create, wechatService.createWechat(wechat));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = WxContants.Wechat.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信配置", notes = "修改微信配置")
    public Envelop updateWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxWechat wechat = toEntity(jsonData, WxWechat.class);
            return Envelop.getSuccess(WxContants.Wechat.message_success_update, wechatService.updateWxchat(wechat));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = WxContants.Wechat.api_delete)
    @ApiOperation(value = "删除微信配置", notes = "删除微信配置")
    public Envelop deleteWechat(
            @ApiParam(name = "codes", value = "codes")
            @RequestParam(value = "codes", required = true) String codes) {
        try {
            wechatService.deleteWechat(codes);
            return Envelop.getSuccess(WxContants.Wechat.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = WxContants.Wechat.api_getByCode)
    @ApiOperation(value = "根据code查找微信配置", notes = "根据code查找微信配置")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(WxContants.Wechat.message_success_find, wechatService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @RequestMapping(value = WxContants.Wechat.api_getWechats, method = RequestMethod.GET)
    @ApiOperation(value = "获取微信配置列表(分页)")
    public Envelop getWechats(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档")
            @RequestParam(value = "sorts", required = false) String sorts,
            @ApiParam(name = "size", value = "分页大小", defaultValue = "15")
            @RequestParam(value = "size", required = false) int size,
            @ApiParam(name = "page", value = "页码", defaultValue = "1")
            @RequestParam(value = "page", required = false) int page,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //得到list数据
        List<WxWechat> list = wechatService.search(fields, filters, sorts, page, size);
        //获取总数
        long count=wechatService.getCount(filters);
        //封装头信息
        pagedResponse(request, response, count, page, size);
        //封装返回格式
        List<MWxWechat> mWxWechats = convertToModels(list, new ArrayList<>(list.size()), MWxWechat.class, fields);

        return Envelop.getSuccessListWithPage(WxContants.Wechat.message_success_find_functions,mWxWechats, page, size,count);
    }


    @GetMapping(value = WxContants.Wechat.api_getWechatNoPage)
    @ApiOperation(value = "获取微信列表配置，不分页")
    public Envelop getWechatNoPage(
            @ApiParam(name = "fields", value = "返回的字段，为空返回全部字段", defaultValue = "id,code,name,saasId,appId,appSecret,baseUrl,remark")
            @RequestParam(value = "fields", required = false) String fields,
            @ApiParam(name = "filters", value = "过滤器，为空检索所有条件")
            @RequestParam(value = "filters", required = false) String filters,
            @ApiParam(name = "sorts", value = "排序，规则参见说明文档", defaultValue = "+name,+createTime")
            @RequestParam(value = "sorts", required = false) String sorts) throws Exception {
        //得到list数据
        List<WxWechat> list = wechatService.search(fields,filters,sorts);
        //封装返回格式
        List<MWxWechat> mWxWechats = convertToModels(list, new ArrayList<>(list.size()), MWxWechat.class, fields);
        return Envelop.getSuccessList(WxContants.Wechat.message_success_find_functions,mWxWechats);
    }

}
