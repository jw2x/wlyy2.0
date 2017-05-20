package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.model.WxWechat;
import com.yihu.jw.wx.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/5/11.
 */
@RestController
@RequestMapping(BaseContants.Wechat.api_common)
@Api(value = "微信相关操作", description = "微信相关操作")
public class WechatController extends EnvelopRestController {
    @Autowired
    private WechatService wechatService;

    @PostMapping(value = BaseContants.Wechat.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信", notes = "创建微信")
    public Envelop createWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxWechat wechat = toEntity(jsonData, WxWechat.class);
            return Envelop.getSuccess(BaseContants.Wechat.message_success_create, wechatService.createWechat(wechat));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.Wechat.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信", notes = "修改微信")
    public Envelop updateWechat(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxWechat Wechat = toEntity(jsonData, WxWechat.class);
            return Envelop.getSuccess(BaseContants.Wechat.message_success_update, wechatService.updateWxchat(Wechat));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = BaseContants.Wechat.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除微信", notes = "删除微信")
    public Envelop deleteWechat(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wechatService.deleteWechat(code);
            return Envelop.getSuccess(BaseContants.Wechat.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseContants.Wechat.api_getByCode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据code查找微信", notes = "根据code查找微信")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseContants.Wechat.message_success_find, wechatService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
