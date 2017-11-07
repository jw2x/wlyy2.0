package com.yihu.jw.feign.fallbackfactory.wlyy.agreement;

import com.yihu.jw.feign.wlyy.agreement.WlyySignFamilyFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Component
public class WlyySignFamilyFeignFallbackFactory implements FallbackFactory<WlyySignFamilyFeign>{

    @Override
    public WlyySignFamilyFeign create(Throwable throwable) {
        return new WlyySignFamilyFeign() {
            @Override
            public Envelop create(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop update(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop findByCode(@RequestParam(value = "code") String code) {
                return null;
            }

            @Override
            public Envelop queryPage(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts, @RequestParam(value = "size", required = false) int size, @RequestParam(value = "page", required = false) int page) {
                return null;
            }

            @Override
            public Envelop getList(@RequestParam(value = "fields", required = false) String fields, @RequestParam(value = "filters", required = false) String filters, @RequestParam(value = "sorts", required = false) String sorts) {
                return null;
            }
        };
    }
}
