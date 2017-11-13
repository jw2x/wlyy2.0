package com.yihu.jw.feign.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.fallbackfactory.base.wx.GraphicMessageFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;;
import com.yihu.jw.rm.wx.WechatRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@FeignClient(
        name = CommonContants.svr_base // name值是eurika的实例名字
        ,fallbackFactory  = GraphicMessageFeignFallbackFactory.class
)
@RequestMapping(value = WechatRequestMapping.api_common)
public interface GraphicMessageFeign {

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_create, method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop createWxGraphicMessage( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_update,method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop updateWxGraphicMessage( @RequestBody String jsonData) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWxGraphicMessage(@RequestParam(value = "ids") String ids,@RequestParam(value = "userId") String userId,@RequestParam(value = "userName") String userName) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_getById,method = RequestMethod.GET)
    Envelop findById(@RequestParam(value = "id") String id) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessages,method = RequestMethod.GET)
    Envelop getWxGraphicMessages(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_getWxGraphicMessageNoPage,method = RequestMethod.GET)
    Envelop getWxGraphicMessageNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException;

    @RequestMapping(value = WechatRequestMapping.WxGraphicMessage.api_sendGraphicMessages,method = RequestMethod.GET)
    String sendGraphicMessages(
            @RequestParam(value = "ids", required = true) String ids,
            @RequestParam(value = "fromUserName", required = true) String fromUserName,
            @RequestParam(value = "toUserName", required = true) String toUserName
    );
}
