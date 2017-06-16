package com.yihu.jw.fegin.fallbackfactory.base;

import com.yihu.jw.fegin.base.SaasFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class SaasFeginFallbackFactory implements FallbackFactory<SaasFegin> {

    @Override
    public SaasFegin create(Throwable throwable) {
        return  new SaasFegin() {
            @Override
            public Envelop getList(@RequestParam(value = "fields") String fields, @RequestParam(value = "filters") String filters, @RequestParam(value = "sorts") String sorts) {
                return null;
            }
        };
    }
}
