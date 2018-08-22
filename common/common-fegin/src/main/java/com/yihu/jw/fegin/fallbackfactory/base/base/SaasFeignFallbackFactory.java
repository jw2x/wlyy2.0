//package com.yihu.jw.fegin.fallbackfactory.base.base;
//
//import com.yihu.jw.fegin.base.base.SaasFeign;
//import com.yihu.jw.restmodel.common.Envelop;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Component
//public class SaasFeignFallbackFactory implements FallbackFactory<SaasFeign> {
//
//    @Override
//    public SaasFeign create(Throwable throwable) {
//        return  new SaasFeign() {
//            @Override
//            public Envelop getList(@RequestParam(value = "fields") String fields, @RequestParam(value = "filters") String filters, @RequestParam(value = "sorts") String sorts) {
//                return null;
//            }
//
//            @Override
//            public Envelop findByCode(@RequestParam(value = "id") String id) {
//                return null;
//            }
//        };
//    }
//}
