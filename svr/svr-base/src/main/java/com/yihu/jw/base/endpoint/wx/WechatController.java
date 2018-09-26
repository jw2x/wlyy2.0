package com.yihu.jw.base.endpoint.wx;

import com.yihu.jw.base.service.wx.WechatService;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.rm.base.BaseRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Trick on 2018/9/26.
 */
@RestController
@RequestMapping(BaseRequestMapping.WeChat.PREFIX)
@Api(value = "微信基础信息管理", description = "微信基础信息管理", tags = {"微信基础 - 微信基础信息管理"})
public class WechatController extends EnvelopRestEndpoint {

    @Autowired
    private WechatService wechatService;

    @GetMapping(value = BaseRequestMapping.WeChat.getWechatInfos)
    @ApiOperation(value = "获取微信基本信息列表", notes = "获取微信基本信息列表")
    public MixEnvelop getWxWechatList(@ApiParam(name = "name", value = "微信名称")
                                      @RequestParam(value = "name", required = false) String name,
                                      @ApiParam(name = "saasName", value = "租户名称")
                                      @RequestParam(value = "saasName", required = false) String saasName,
                                      @ApiParam(name = "status", value = "状态")
                                      @RequestParam(value = "status", required = false) Integer status,
                                      @ApiParam(name = "publicType", value = "微信类型")
                                      @RequestParam(value = "publicType", required = false) Integer publicType,
                                      @ApiParam(name = "page", value = "页码")
                                      @RequestParam(value = "page", required = true) Integer page,
                                      @ApiParam(name = "size", value = "每页大小")
                                      @RequestParam(value = "size", required = true) Integer size) {
        return wechatService.getWxWechatList(name, saasName, status, publicType, page, size);
    }
}
