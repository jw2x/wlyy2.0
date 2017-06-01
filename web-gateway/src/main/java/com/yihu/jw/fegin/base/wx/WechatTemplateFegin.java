package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatTemplateFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  =WechatTemplateFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.WxTemplate.api_common)
public interface WechatTemplateFegin {

    //@PostMapping(value = WxContants.WxTemplate.api_create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = WxContants.WxTemplate.api_create ,method = RequestMethod.POST)
    Envelop createWxTemplate(@RequestBody String jsonData);

    //@PutMapping(value = WxContants.WxTemplate.api_update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(value = WxContants.WxTemplate.api_update ,method = RequestMethod.PUT)
    Envelop updateWxTemplate(@RequestBody String jsonData);

    //@DeleteMapping(value = WxContants.WxTemplate.api_delete)
    @RequestMapping(value = WxContants.WxTemplate.api_delete ,method = RequestMethod.DELETE)
    Envelop deleteWxTemplate( @RequestParam(value = "code", required = true) String code);

    //@GetMapping(value = WxContants.WxTemplate.api_getByCode)
    @RequestMapping(value = WxContants.WxTemplate.api_getByCode ,method = RequestMethod.GET)
    Envelop findByCode( @RequestParam(value = "code", required = true) String code);

    //@GetMapping(value = WxContants.WxTemplate.api_getWxTemplatesNoPage)
    @RequestMapping(value = WxContants.WxTemplate.api_getWxTemplatesNoPage ,method = RequestMethod.GET)
    Envelop getWechatNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);

    //@RequestMapping(value = WxContants.WxTemplate.api_getWxTemplates, method = RequestMethod.GET)
    //Envelop getWechats(
    //        @RequestParam(value = "fields", required = false) String fields,
    //        @RequestParam(value = "filters", required = false) String filters,
    //        @RequestParam(value = "sorts", required = false) String sorts,
    //        @RequestParam(value = "size", required = false) int size,
    //        @RequestParam(value = "page", required = false) int page,
    //        HttpServletRequest request,
    //        HttpServletResponse response);

    //@GetMapping(value = WxContants.WxTemplate.api_sendTemplateMessage)
    @RequestMapping(value = WxContants.WxTemplate.api_sendTemplateMessage ,method = RequestMethod.GET)
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
