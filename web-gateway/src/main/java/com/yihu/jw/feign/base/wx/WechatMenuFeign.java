package com.yihu.jw.feign.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.wx.WechatMenuFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
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
    Envelop createWxMenu( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_update,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWxMenu( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWxMenu(@RequestParam(value="ids")String ids,@RequestParam(value = "userId") String userId,@RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getById,method = RequestMethod.GET)
    Envelop findById(@RequestParam(value="id")String id) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getWxMenuNoPage,method = RequestMethod.GET)
    Envelop getWxMenuNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getWxMenus,method = RequestMethod.GET)
    Envelop getWxMenus(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_createMenu ,method = RequestMethod.GET)
    Envelop createWechatMenu(@RequestParam(value = "wechatId", required = true)String wechatId);

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getParentMenu,method = RequestMethod.GET)
    Envelop getParentMenu(@PathVariable(value = "wechatId", required = true) String wechatId) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxMenu.api_getChildMenus,method = RequestMethod.GET)
    Envelop getChildMenus(@PathVariable(value = "parentId", required = true) String parentId) throws JiWeiException;
}
