package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.fallbackfactory.base.wx.WechatMenuFeignFallbackFactory;
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
        ,fallbackFactory  = WechatMenuFeignFallbackFactory.class
)
@RequestMapping(value = WechatRequestMapping.api_common)
public interface WechatMenuFeign {

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_create,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop createWxMenu(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_update,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop updateWxMenu(@RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_delete,method = RequestMethod.DELETE)
    MixEnvelop deleteWxMenu(@RequestParam(value="ids")String ids, @RequestParam(value = "userId") String userId, @RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getById,method = RequestMethod.GET)
    MixEnvelop findById(@RequestParam(value="id")String id) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getWxMenuNoPage,method = RequestMethod.GET)
    MixEnvelop getWxMenuNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getWxMenus,method = RequestMethod.GET)
    MixEnvelop getWxMenus(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_createMenu ,method = RequestMethod.GET)
    MixEnvelop createWechatMenu(@RequestParam(value = "wechatId", required = true)String wechatId);

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getParentMenu,method = RequestMethod.GET)
    MixEnvelop getParentMenu(@PathVariable(value = "wechatId", required = true) String wechatId) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getChildMenus,method = RequestMethod.GET)
    MixEnvelop getChildMenus(@PathVariable(value = "parentId", required = true) String parentId) throws JiWeiException;
}
