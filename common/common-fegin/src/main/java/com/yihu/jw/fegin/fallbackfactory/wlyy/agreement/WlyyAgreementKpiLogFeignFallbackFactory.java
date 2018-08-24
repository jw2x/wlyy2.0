package com.yihu.jw.fegin.fallbackfactory.wlyy.agreement;

import com.yihu.jw.fegin.wlyy.agreement.WlyyAgreementKpiLogFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class WlyyAgreementKpiLogFeignFallbackFactory implements FallbackFactory<WlyyAgreementKpiLogFeign> {

    @Override
    public WlyyAgreementKpiLogFeign create(Throwable throwable) {
        return new WlyyAgreementKpiLogFeign() {
            @Override
            public MixEnvelop create(String jsonData) {
                return null;
            }

            @Override
            public MixEnvelop findByCode(String code) {
                return null;
            }

            @Override
            public MixEnvelop queryPage(String fields, String filters, String sorts, int size, int page) {
                return null;
            }

            @Override
            public MixEnvelop getList(String fields, String filters, String sorts) {
                return null;
            }
        };
    }
}
