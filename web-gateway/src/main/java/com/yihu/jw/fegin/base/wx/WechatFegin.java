package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WechatFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.Wechat.api_common)
public interface WechatFegin {

    @RequestMapping(value = WxContants.Wechat.api_create, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop createWechat( @RequestBody String jsonData);

    @RequestMapping(value = WxContants.Wechat.api_update, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWechat(String jsonData);

    @RequestMapping(value = WxContants.Wechat.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWechat(@RequestParam(value = "codes") String codes);

    @RequestMapping(value = WxContants.Wechat.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(@RequestParam(value = "code") String code);

    @RequestMapping(value = WxContants.Wechat.api_getWechats ,method = RequestMethod.GET)
    Envelop getWechats(@RequestParam(value = "fields" ,required = false)String fields, @RequestParam(value = "filters",required = false) String filters, @RequestParam(value = "sorts" ,required = false)String sorts, @RequestParam(value = "size" ,required = false) int size,@RequestParam(value = "page" ,required = false)int page);

    @RequestMapping(value = WxContants.Wechat.api_getWechatNoPage,method = RequestMethod.GET )
    Envelop getWechatNoPage(@RequestParam(value = "fields")String fields,@RequestParam(value = "filters") String filters,@RequestParam(value = "sorts") String sorts);
}
