package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.model.WxMenu;
import com.yihu.jw.wx.model.WxTemplate;
import com.yihu.jw.wx.service.WxTemplateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/19 0019.
 */
public class WxTemplateController extends EnvelopRestController {
    @Autowired
    private WxTemplateService wxTemplateService;

    @PostMapping(value = BaseContants.WxTemplate.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建微信模版", notes = "创建微信模版")
    public Envelop createWxTemplate(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxTemplate WxTemplate = toEntity(jsonData, WxTemplate.class);
            return Envelop.getSuccess(BaseContants.WxTemplate.message_success_create, wxTemplateService.createWxMenu(WxTemplate));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.WxTemplate.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改微信模版", notes = "修改微信模版")
    public Envelop updateWxTemplate(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            WxTemplate WxTemplate = toEntity(jsonData, WxTemplate.class);
            return Envelop.getSuccess(BaseContants.WxTemplate.message_success_update, WxTemplateService.updateWxTemplate(WxTemplate));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }


    @DeleteMapping(value = BaseContants.WxTemplate.api_delete, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "删除微信模版", notes = "删除微信模版")
    public Envelop deleteWxTemplate(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) Integer id) {
        try {
            WxTemplateService.deleteWxTemplate(id);
            return Envelop.getSuccess(BaseContants.WxTemplate.message_success_delete );
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @GetMapping(value = BaseContants.WxTemplate.api_getById, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id查找微信菜单", notes = "根据id查找微信菜单")
    public Envelop findById(
            @ApiParam(name = "id", value = "id")
            @RequestParam(value = "id", required = true) Integer id
    ) {
        try {
            return Envelop.getSuccess(BaseContants.WxTemplate.message_success_find, WxTemplateService.findById(id));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
