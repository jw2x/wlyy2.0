package com.yihu.jw.feign.fallbackfactory.wlyy.agreement;

import com.yihu.jw.feign.wlyy.agreement.WlyyAgreementKpiFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class WlyyAgreementKpiFeignFallbackFactory implements FallbackFactory<WlyyAgreementKpiFeign> {

    @Override
    public WlyyAgreementKpiFeign create(Throwable throwable) {
        return new WlyyAgreementKpiFeign() {
            @Override
            public Envelop create(String jsonData) {
                return null;
            }

            @Override
            public Envelop update(String jsonData) {
                return null;
            }

            @Override
            public Envelop delete(String code) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            @Override
            public Envelop queryPage(String fields, String filters, String sorts, int size, int page) {
                return null;
            }

            @Override
            public Envelop getList(String fields, String filters, String sorts) {
                return null;
            }
        };
    }
}
