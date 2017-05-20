package com.yihu.jw.wx.controller;

import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import com.yihu.jw.wx.model.WxAccessToken;
import com.yihu.jw.wx.service.WxAccessTokenService;
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
@RequestMapping(BaseContants.WxAccessToken.api_common)
@Api(value = "微信token模块", description = "微信token模块接口管理")
public class WxAccessTokenController extends EnvelopRestController {

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    /**
     * 根据wechatCode获取WxAccessToken
     * @param wechatCode
     * @return MWxAccessToken
     * @throws Exception
     */
    @RequestMapping(value = BaseContants.WxAccessToken.api_get, method = RequestMethod.GET)
    @ApiOperation(value = "根据wechatCode获取最新的WxAccessToken")
    public Envelop getWxAccessToken(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @PathVariable(value = "wechatCode") String wechatCode) throws Exception {
        try {
            WxAccessToken wxAccessToken = wxAccessTokenService.getWxAccessTokenByCode(wechatCode);
            return Envelop.getSuccess(BaseContants.WxAccessToken.message_success_get, wxAccessToken);
        }catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = BaseContants.WxAccessToken.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建WxAccessToken")
    public Envelop createWxAccessToken(
            @ApiParam(name = "wxAccessToken", value = "对象JSON结构体", allowMultiple = true)
            @RequestBody String wxAccessTokenJson) throws Exception {
        try {
            WxAccessToken wxAccessToken = toEntity(wxAccessTokenJson, WxAccessToken.class);
            wxAccessToken = wxAccessTokenService.createWxAccessToken(wxAccessToken);
            return Envelop.getSuccess(BaseContants.WxAccessToken.message_success_create, wxAccessToken);
        }catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
