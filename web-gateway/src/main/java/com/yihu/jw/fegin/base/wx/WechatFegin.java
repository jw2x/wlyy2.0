package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WechatFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.Wechat.api_common)
public interface WechatFegin {

    @RequestMapping(value = WxContants.Wechat.api_create, method = RequestMethod.POST)
    Envelop createWechat( @RequestBody String jsonData);

    @RequestMapping(value = WxContants.Wechat.api_update, method = RequestMethod.PUT)
    Envelop updateWechat(String jsonData);

    @RequestMapping(value = WxContants.Wechat.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWechat(String code);

    @RequestMapping(value = WxContants.Wechat.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(String code);

    @RequestMapping(value = WxContants.Wechat.api_getWechats ,method = RequestMethod.GET)
    Envelop getWechats(String fields, String filters, String sorts, int page, int size);

    @RequestMapping(value = WxContants.Wechat.api_getWechatNoPage,method = RequestMethod.GET )
    Envelop getWechatNoPage(String fields, String filters, String sorts);
}
