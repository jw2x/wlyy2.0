package com.yihu.jw.fegin.fallbackfactory.base.version;

import com.yihu.jw.fegin.base.version.ServerUrlVersionFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.business.JiWeiException;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class ServerUrlVersionFeginFallbackFactory implements FallbackFactory<ServerUrlVersionFegin> {

    @Autowired
    private Tracer tracer;

    @Override
    public ServerUrlVersionFegin create(Throwable e) {
        return new ServerUrlVersionFegin() {
            @Override
            public Envelop create(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("创建后台url版本失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop update(@RequestBody String jsonData) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("更新后台url版本失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop delete(String codes, String userCode, String userName) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("删除后台url版本失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("codes:" + codes);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop findByCode(String code) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找后台url版本失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("code:" + code);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("分页查找后台url版本失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filterStr);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                tracer.getCurrentSpan().logEvent("size:" + size);
                tracer.getCurrentSpan().logEvent("page:" + page);
                throw new JiWeiException(e);
            }

            @Override
            public Envelop getListNoPage(String fields, String filters, String sorts) throws JiWeiException {
                tracer.getCurrentSpan().logEvent("查找后台url版本列表失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("fields:" + fields);
                tracer.getCurrentSpan().logEvent("filters:" + filters);
                tracer.getCurrentSpan().logEvent("sorts:" + sorts);
                throw new JiWeiException(e);
            }
        };
    }
}
