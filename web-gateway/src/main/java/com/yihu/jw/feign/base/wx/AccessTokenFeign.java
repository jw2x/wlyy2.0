package com.yihu.jw.feign.base.wx;

import com.yihu.jw.feign.fallbackfactory.base.wx.AccessTokenFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = AccessTokenFeignFallbackFactory.class
)
@RequestMapping(value =  WechatRequestMapping.api_common)
public interface AccessTokenFeign {

    @RequestMapping(value = WechatRequestMapping.WxAccessToken.api_get, method = RequestMethod.GET)
    Envelop getWxAccessToken(@RequestParam(value = "wechatId") String wechatId) ;

}
