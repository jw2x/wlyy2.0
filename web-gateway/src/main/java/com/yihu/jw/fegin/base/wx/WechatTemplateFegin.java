package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatTemplateFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import com.yihu.jw.restmodel.wx.WechatContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  =WechatTemplateFeginFallbackFactory.class
)
@RequestMapping(value = WechatContants.api_common)
public interface WechatTemplateFegin {

    @RequestMapping(value = WechatContants.WxTemplate.api_create ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop createWxTemplate(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_update ,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWxTemplate(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_delete ,method = RequestMethod.DELETE)
    Envelop deleteWxTemplate( @RequestParam(value = "codes", required = true) String codes,@RequestParam(value = "userCode") String userCode,@RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_getByCode ,method = RequestMethod.GET)
    Envelop findByCode( @RequestParam(value = "code", required = true) String code) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_getWxTemplatesNoPage ,method = RequestMethod.GET)
    Envelop getWechatNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
    Envelop getWechats(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WechatContants.WxTemplate.api_sendTemplateMessage ,method = RequestMethod.GET)
    @ResponseBody
    Envelop sendTemplateMessage(
            @RequestParam(value="openid") String openid,
            @RequestParam(value="templateCode") String templateCode,
            @RequestParam(value="url",required = false) String url,
            @RequestParam(value="appid",required = false) String appid,
            @RequestParam(value="pagepath",required = false) String pagepath,
            @RequestParam(value="data") String data
    );
}
