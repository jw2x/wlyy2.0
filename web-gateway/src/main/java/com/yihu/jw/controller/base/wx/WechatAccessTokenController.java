package com.yihu.jw.controller.base.wx;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.base.wx.AccessTokenFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WechatContants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@RestController
@RequestMapping("{version}/"+ WechatContants.WxAccessToken.api_common)
@Api(description = "微信token相关")
public class WechatAccessTokenController {

    private Logger logger= LoggerFactory.getLogger(WechatAccessTokenController.class);

    @Autowired
    private AccessTokenFegin accessTokenFegin;

    @Autowired
    private Tracer tracer;

    @GetMapping(value = WechatContants.WxAccessToken.api_get)
    @ApiOperation(value = "根据wechatCode获取最新的WxAccessToken")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop getWxAccessToken(
            @ApiParam(name = "wechatCode", value = "wechatCode")
            @RequestParam(value = "wechatCode") String wechatCode) {
        return accessTokenFegin.getWxAccessToken(wechatCode);
    }
}
