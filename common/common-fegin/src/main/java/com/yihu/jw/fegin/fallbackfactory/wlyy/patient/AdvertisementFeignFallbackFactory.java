package com.yihu.jw.fegin.fallbackfactory.wlyy.patient;

import com.yihu.jw.fegin.wlyy.patient.AdvertisementFeign;
import com.yihu.jw.restmodel.web.MixEnvelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Component
public class AdvertisementFeignFallbackFactory implements FallbackFactory<AdvertisementFeign> {


    @Override
    public AdvertisementFeign create(Throwable throwable) {
        return new AdvertisementFeign() {
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

            @Override
            public MixEnvelop getListByPatientCode(@RequestParam(value = "patientCode") String patientCode) {
                return null;
            }

            @Override
            public MixEnvelop getListByIp(@RequestParam(value = "ipAddress") String ipAddress) {
                return null;
            }


        };
    }
}
