package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class WechatFeginFallbackFactory implements FallbackFactory<WechatFegin> {
    @Override
    public WechatFegin create(Throwable e) {
        return new WechatFegin() {
            public Envelop createWechat(String jsonData) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }
            public Envelop updateWechat(String jsonData) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }
            public Envelop deleteWechat(String code) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }

            public Envelop findByCode(String code) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }

            public Envelop getWechats(String fields, String filters, String sorts, int size, int page) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }

            public Envelop getWechatNoPage(String fields, String filters, String sorts) throws JiWeiException {
                Envelop envelop = new Envelop();
                envelop.getError(e.getMessage(),-1);
                throw new JiWeiException(e);
            }
        };
    }
}
