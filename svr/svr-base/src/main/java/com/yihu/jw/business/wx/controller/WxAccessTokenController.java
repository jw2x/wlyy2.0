package com.yihu.jw.business.wx.controller;

import com.yihu.jw.business.wx.model.WxAccessToken;
import com.yihu.jw.business.wx.service.WxAccessTokenService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cyx on 2017/5/11.
 */
@RestController
@RequestMapping(WechatRequestMapping.api_common)
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
    @GetMapping(value = WechatRequestMapping.WxAccessToken.api_get)
    @ApiOperation(value = "根据wechatCode获取最新的WxAccessToken")
    public Envelop getWxAccessToken(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @RequestParam(value = "wechatCode") String wechatCode) {
        try {
            WxAccessToken wxAccessToken = wxAccessTokenService.getWxAccessTokenByCode(wechatCode);
            return Envelop.getSuccess(WechatRequestMapping.WxAccessToken.message_success_get, wxAccessToken);
        }catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
