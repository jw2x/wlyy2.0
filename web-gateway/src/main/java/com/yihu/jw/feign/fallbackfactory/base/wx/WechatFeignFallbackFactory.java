package com.yihu.jw.feign.fallbackfactory.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.base.wx.WechatFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

@Component
public class WechatFeignFallbackFactory implements FallbackFactory<WechatFeign> {


    @Autowired
    private Tracer tracer;

    @Override
    public WechatFeign create(Throwable e) {
        return new WechatFeign() {

            public Envelop createWechat(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            public Envelop updateWechat(String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            public Envelop deleteWechat(String codes,String userCode,String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                throw new JiWeiException(e);
            }

            public Envelop findByCode(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:"+code);
                throw new JiWeiException(e);
            }

            public Envelop getWechats(String fields, String filters, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            public Envelop getWechatNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信配置列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }
        };
    }
}
