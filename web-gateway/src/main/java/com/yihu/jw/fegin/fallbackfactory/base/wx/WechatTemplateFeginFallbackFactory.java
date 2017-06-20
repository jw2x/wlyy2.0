package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.fegin.base.wx.WechatTemplateFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class WechatTemplateFeginFallbackFactory implements FallbackFactory<WechatTemplateFegin>{

    @Autowired
    private Tracer tracer;

    @Override
    public WechatTemplateFegin create(Throwable e) {
        return new WechatTemplateFegin() {
            @Override
            public Envelop createWxTemplate(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建微信模板消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop updateWxTemplate(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新微信模板消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop deleteWxTemplate(String codes) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除微信模板消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop findByCode(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据code查找微信模板消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:"+code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop sendTemplateMessage(String openid, String templateCode, String url, String appid, String pagepath, String data) {
                return null;
            }

            @Override
            public Envelop getWechatNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信模板消息列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getWechats(String fields, String filters, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找微信模板消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }
        };
    }
}
