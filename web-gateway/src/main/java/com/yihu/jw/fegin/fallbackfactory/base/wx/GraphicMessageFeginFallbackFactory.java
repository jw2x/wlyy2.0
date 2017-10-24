package com.yihu.jw.fegin.fallbackfactory.base.wx;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.fegin.base.wx.GraphicMessageFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/5/31 0031.
 */
@Component
public class GraphicMessageFeginFallbackFactory implements FallbackFactory<GraphicMessageFegin> {

    @Autowired
    private Tracer tracer;

    @Override
    public GraphicMessageFegin create(Throwable e) {
        return new GraphicMessageFegin() {
            @Override
            public Envelop createWxGraphicMessage(@RequestParam(value = "jsonData") String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建微信图文消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop updateWxGraphicMessage(@RequestParam(value = "jsonData") String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新微信图文消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:"+jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop deleteWxGraphicMessage(String codes,String userCode,String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除微信配置失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:"+codes);
                throw new JiWeiException(e);

            }

            @Override
            public Envelop findByCode(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("根据code查找微信图文消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("code:"+code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getWxGraphicMessages(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts, @RequestParam(value = "size", required = false) int size, @RequestParam(value = "page", required = false) int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找微信图文消息失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getWxGraphicMessageNoPage(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找微信图文消息列表失败:原因:"+e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }

            @Override
            public String sendGraphicMessages(@RequestParam(value = "codes", required = true) String codes, @RequestParam(value = "fromUserName", required = true) String fromUserName, @RequestParam(value = "toUserName", required = true) String toUserName) {
                return null;
            }
        };
    }
}
