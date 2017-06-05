package com.yihu.jw.fegin.base.wx;

import com.yihu.jw.fegin.fallbackfactory.base.wx.GraphicMessageFeginFallbackFactory;
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
        ,fallbackFactory  = GraphicMessageFeginFallbackFactory.class
)
@RequestMapping(value = WxContants.WxGraphicMessage.api_common)
public interface GraphicMessageFegin {

    @RequestMapping(value = WxContants.WxGraphicMessage.api_create, method = RequestMethod.POST)
    Envelop createWxGraphicMessage( @RequestBody String jsonData);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_update,method = RequestMethod.PUT)
    Envelop updateWxGraphicMessage( @RequestBody String jsonData);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_delete,method = RequestMethod.DELETE)
    Envelop deleteWxGraphicMessage(String code);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_getByCode,method = RequestMethod.GET)
    Envelop findByCode(String code);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_getWxGraphicMessages,method = RequestMethod.GET)
    Envelop getWxGraphicMessages(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "page", required = false) int page);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_getWxGraphicMessageNoPage,method = RequestMethod.GET)
    Envelop getWxGraphicMessageNoPage(
            @RequestParam(value = "fields", required = false) String fields,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sorts", required = false) String sorts);

    @RequestMapping(value = WxContants.WxGraphicMessage.api_sendGraphicMessages,method = RequestMethod.GET)
    String sendGraphicMessages(
            @RequestParam(value = "codes", required = true) String codes,
            @RequestParam(value = "fromUserName", required = true) String fromUserName,
            @RequestParam(value = "toUserName", required = true) String toUserName
    );
}
