package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.AccessTokenFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = AccessTokenFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.WxAccessToken.api_common)
public interface AccessTokenFegin {

    @RequestMapping(value = WxContants.WxAccessToken.api_get, method = RequestMethod.GET)
    Envelop getWxAccessToken(@RequestParam(value = "wechatCode") String wechatCode) ;

}
