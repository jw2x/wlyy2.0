package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.GraphicMessageFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class GraphicMessageFeginFallbackFactory implements FallbackFactory<GraphicMessageFegin> {

    @Override
    public GraphicMessageFegin create(Throwable throwable) {
        return new GraphicMessageFegin() {
            @Override
            public Envelop createWxGraphicMessage(@RequestParam(value = "jsonData") String jsonData) {
                return null;
            }

            @Override
            public Envelop updateWxGraphicMessage(@RequestParam(value = "jsonData") String jsonData) {
                return null;
            }

            @Override
            public Envelop deleteWxGraphicMessage(String code) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            //@Override
            //public Envelop getWxGraphicMessages(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts, @RequestParam(value = "size", required = false) int size, @RequestParam(value = "page", required = false) int page, HttpServletRequest request, HttpServletResponse response) {
            //    return null;
            //}

            @Override
            public Envelop getWxGraphicMessageNoPage(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts) {
                return null;
            }

            @Override
            public String sendGraphicMessages(@RequestParam(value = "codes", required = true) String codes, @RequestParam(value = "fromUserName", required = true) String fromUserName, @RequestParam(value = "toUserName", required = true) String toUserName) {
                return null;
            }
        };
    }
}
