package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatMenuFeginFallbackFactory;
import com.yihu.jw.restmodel.common.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import com.yihu.jw.restmodel.wx.WxContants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  =WechatMenuFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.WxMenu.api_common)
public interface WechatMenuFegin {

    @RequestMapping(value = WxContants.WxMenu.api_create,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop createWxMenu( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_update,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWxMenu( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWxMenu(@RequestParam(value="codes")String codes,@RequestParam(value = "userCode") String userCode,@RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(@RequestParam(value="code")String code) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_getWxMenuNoPage,method = RequestMethod.GET)
    Envelop getWxMenuNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_getWxMenus,method = RequestMethod.GET)
    Envelop getWxMenus(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_createMenu ,method = RequestMethod.GET)
    Envelop createWechatMenu(@RequestParam(value = "wechatCode", required = true)String wechatCode);

    @RequestMapping(value = WxContants.WxMenu.api_getParentMenu,method = RequestMethod.GET)
    Envelop getParentMenu(@PathVariable(value = "wechatCode", required = true) String wechatCode) throws JiWeiException;

    @RequestMapping(value = WxContants.WxMenu.api_getChildMenus,method = RequestMethod.GET)
    Envelop getChildMenus(@PathVariable(value = "parentCode", required = true) String parentCode) throws JiWeiException;
}
