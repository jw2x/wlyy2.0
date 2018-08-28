package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatTemplateFeignFallbackFactory;
import com.yihu.jw.common.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.base.WechatRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = WechatTemplateFeignFallbackFactory.class
)
@RequestMapping(value = WechatRequestMapping.api_common)
public interface WechatTemplateFeign {

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_create ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop createWxTemplate(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_update ,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop updateWxTemplate(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_delete ,method = RequestMethod.DELETE)
    MixEnvelop deleteWxTemplate(@RequestParam(value = "ids", required = true) String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_getById ,method = RequestMethod.GET)
    MixEnvelop findById(@RequestParam(value = "id", required = true) String id) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_getWxTemplatesNoPage ,method = RequestMethod.GET)
    MixEnvelop getWechatNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
    MixEnvelop getWechats(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxTemplate.api_sendTemplateMessage ,method = RequestMethod.GET)
    @ResponseBody
    MixEnvelop sendTemplateMessage(
            @RequestParam(value="openid") String openid,
            @RequestParam(value="templateId") String templateId,
            @RequestParam(value="url",required = false) String url,
            @RequestParam(value="appid",required = false) String appid,
            @RequestParam(value="pagepath",required = false) String pagepath,
            @RequestParam(value="data") String data
    );
}
