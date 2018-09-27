package com.yihu.jw.base.endpoint.wx;

import com.yihu.jw.base.service.wx.WeChatQrcodeService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import com.yihu.jw.rm.base.WechatRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Trick on 2018/9/7.
 */
@RestController
@RequestMapping(BaseRequestMapping.WeChat.wechat_base)
@Api(value = "微信二维码", description = "微信二维码", tags = {"微信二维码服务 - 微信二维码"})
public class WeChatQrcodeController extends EnvelopRestEndpoint {

    @Autowired
    private WeChatQrcodeService weChatQrcodeService;

    @PostMapping(value = WechatRequestMapping.WxQrcode.api_getQrcode, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "生成微信二维码", notes = "生成微信二维码")
    public Envelop getQrcode(@ApiParam(name = "wxId", value = "微信id")
                             @RequestParam(value = "wxId", required = true)String wxId,
                             @ApiParam(name = "scene", value = "场景值")
                             @RequestParam(value = "scene", required = true)String scene) throws Exception{
        return success(weChatQrcodeService.getQrcode(wxId,scene));
    }

}
