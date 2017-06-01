package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatMenuFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  =WechatMenuFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.WxMenu.api_common)
public interface WechatMenuFegin {

    @RequestMapping(value = WxContants.WxMenu.api_create,method = RequestMethod.POST)
    Envelop createWxMenu( @RequestBody String jsonData) ;

    @RequestMapping(value = WxContants.WxMenu.api_update,method = RequestMethod.PUT)
    Envelop updateWxMenu( @RequestBody String jsonData);

    @RequestMapping(value = WxContants.WxMenu.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWxMenu(@RequestParam(value="code")String code);

    @RequestMapping(value = WxContants.WxMenu.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(@RequestParam(value="code")String code);

    @RequestMapping(value = WxContants.WxMenu.api_getWxMenuNoPage,method = RequestMethod.GET)
    Envelop getWxMenuNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);

    //@RequestMapping(value = WxContants.WxMenu.api_getWxMenus,method = RequestMethod.GET)
    //Envelop getWxMenus(
    //        @RequestParam(value = "fields", required = false) String fields,
    //        @RequestParam(value = "filters", required = false) String filters,
    //        @RequestParam(value = "sorts", required = false) String sorts,
    //        @RequestParam(value = "size", required = false) int size,
    //        @RequestParam(value = "page", required = false) int page,
    //        HttpServletRequest request,
    //        HttpServletResponse response);

    @RequestMapping(value = WxContants.WxMenu.api_createMenu ,method = RequestMethod.GET)
    Envelop createWechatMenu(@RequestParam(value = "wechatCode", required = true)String wechatCode);
}
