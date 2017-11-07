package com.yihu.jw.feign.fallbackfactory.base.version;

import com.yihu.jw.feign.base.version.WlyyVersionFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class WlyyVersionFeignFallbackFactory implements FallbackFactory<WlyyVersionFeign> {


    @Autowired
    private Tracer tracer;

    @Override
    public WlyyVersionFeign create(Throwable e) {
        return new WlyyVersionFeign() {
            @Override
            public Envelop create(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop update(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop delete(@PathVariable String codes, @RequestParam String userCode, @RequestParam String userName) {
                return null;
            }

            @Override
            public Envelop findByCode(String code) {
                return null;
            }

            @Override
            public Envelop getList(String fields, String filterStr, String sorts, int size, int page) {
                return null;
            }

            @Override
            public Envelop getListNoPage(String fields, String filters, String sorts) {
                return null;
            }
        };
    }
}
