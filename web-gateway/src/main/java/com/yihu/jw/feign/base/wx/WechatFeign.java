package com.yihu.jw.feign.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.wx.WechatFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WechatFeignFallbackFactory.class
)
@RequestMapping(value = WechatRequestMapping.api_common)
public interface WechatFeign {

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_create, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop createWechat( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_update, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWechat(String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWechat(@RequestParam(value = "codes") String codes,@RequestParam(value = "userCode") String userCode,@RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_getById,method = RequestMethod.GET)
    Envelop findByCode(@RequestParam(value = "code") String code) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_getWechats ,method = RequestMethod.GET)
    Envelop getWechats(@RequestParam(value = "fields" ,required = false)String fields, @RequestParam(value = "filters",required = false) String filters, @RequestParam(value = "sorts" ,required = false)String sorts, @RequestParam(value = "size" ,required = false) int size,@RequestParam(value = "page" ,required = false)int page) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxConfig.api_getWechatNoPage,method = RequestMethod.GET )
    Envelop getWechatNoPage(@RequestParam(value = "fields")String fields,@RequestParam(value = "filters") String filters,@RequestParam(value = "sorts") String sorts) throws JiWeiException;
}
