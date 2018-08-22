package com.yihu.jw.fegin.fallbackfactory.wlyy.agreement;

import com.yihu.jw.fegin.wlyy.agreement.WlyyAgreementFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class WlyyAgreementFeignFallbackFactory implements FallbackFactory<WlyyAgreementFeign> {

    @Override
    public WlyyAgreementFeign create(Throwable throwable) {
        return new WlyyAgreementFeign() {
            @Override
            public MixEnvelop create(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public MixEnvelop update(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public MixEnvelop delete(@RequestParam(value = "code") String code) {
                return null;
            }

            @Override
            public MixEnvelop findByCode(@RequestParam(value = "code") String code) {
                return null;
            }

            @Override
            public MixEnvelop queryPage(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts, @RequestParam(value = "size", required = false) int size, @RequestParam(value = "page", required = false) int page) {
                return null;
            }

            @Override
            public MixEnvelop getList(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts) {
                return null;
            }
        };
    }
}
