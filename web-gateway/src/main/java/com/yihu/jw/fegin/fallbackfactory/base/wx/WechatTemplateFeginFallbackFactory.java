package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatTemplateFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class WechatTemplateFeginFallbackFactory implements FallbackFactory<WechatTemplateFegin>{

    @Override
    public WechatTemplateFegin create(Throwable throwable) {
        return new WechatTemplateFegin() {
            @Override
            public Envelop createWxTemplate(String jsonData) {
                return null;
            }

            @Override
            public Envelop updateWxTemplate(String jsonData) {
                return null;
            }

            @Override
            public Envelop deleteWxTemplate(String code) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            @Override
            public Envelop sendTemplateMessage(String openid, String templateCode, String url, String appid, String pagepath, String data) {
                return null;
            }

            @Override
            public Envelop getWechatNoPage(String fields, String filters, String sorts) {
                return null;
            }

            @Override
            public Envelop getWechats(String fields, String filters, String sorts, int size, int page) {
                return null;
            }
        };
    }
}
