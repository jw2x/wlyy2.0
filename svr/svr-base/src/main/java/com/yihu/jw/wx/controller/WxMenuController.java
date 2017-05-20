package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.model.WxMenu;
import com.yihu.jw.wx.service.WxMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
@RestController
@RequestMapping(BaseContants.WxMenu.api_common)
@Api(value = "微信菜单相关操作", description = "微信菜单相关操作")
public class WxMenuController extends EnvelopRestController {

    @Autowired
    private WxMenuService wxMenuService;

    @PostMapping(value = BaseContants.WxMenu.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信菜单", notes = "创建微信菜单")
    public Envelop createWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenu wxMenu = toEntity(jsonData, WxMenu.class);
            return Envelop.getSuccess(BaseContants.WxMenu.message_success_create, wxMenuService.createWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.WxMenu.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信菜单", notes = "修改微信菜单")
    public Envelop updateWxMenu(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxMenu wxMenu = toEntity(jsonData, WxMenu.class);
            return Envelop.getSuccess(BaseContants.WxMenu.message_success_update, wxMenuService.updateWxMenu(wxMenu));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = BaseContants.WxMenu.api_delete)
    @ApiOperation(value = "删除微信菜单", notes = "删除微信菜单")
    public Envelop deleteWxMenu(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code) {
        try {
            wxMenuService.deleteWxMenu(code);
            return Envelop.getSuccess(BaseContants.WxMenu.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseContants.WxMenu.api_getByCode)
    @ApiOperation(value = "根据code查找微信菜单", notes = "根据code查找微信菜单")
    public Envelop findByCode(
            @ApiParam(name = "code", value = "code")
            @RequestParam(value = "code", required = true) String code
    ) {
        try {
            return Envelop.getSuccess(BaseContants.WxMenu.message_success_find, wxMenuService.findByCode(code));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


}
