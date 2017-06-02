package com.yihu.jw.fegin.fallbackfactory.wlyy.agreement;

import com.yihu.jw.fegin.wlyy.agreement.WlyyAgreementKpiLogFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class WlyyAgreementKpiLogFeginFallbackFactory implements FallbackFactory<WlyyAgreementKpiLogFegin> {

    @Override
    public WlyyAgreementKpiLogFegin create(Throwable throwable) {
        return new WlyyAgreementKpiLogFegin() {
            @Override
            public Envelop create(String jsonData) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            //@Override
            //public Envelop queryPage(String fields, String filters, String sorts, int size, int page, HttpServletRequest request, HttpServletResponse response) {
            //    return null;
            //}

            @Override
            public Envelop getList(String fields, String filters, String sorts) {
                return null;
            }
        };
    }
}
