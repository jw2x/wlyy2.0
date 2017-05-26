package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WechatFeginFallbackFactory implements FallbackFactory<WechatFegin> {
    @Override
    public WechatFegin create(Throwable e) {
        return new WechatFegin() {
            public Envelop createWechat(String jsonData) {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
            public Envelop updateWechat(String jsonData) {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
            public Envelop deleteWechat(String code){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop findByCode(String code){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop getWechats(String fields, String filters, String sorts, int page, int size){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }

            public Envelop getWechatNoPage(String fields, String filters, String sorts){
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                return envelop;
            }
        };
    }
}
