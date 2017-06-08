package com.yihu.jw.fegin.fallbackfactory.wlyy.patient;

import com.yihu.jw.fegin.wlyy.patient.AdvertisementFegin;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2017/6/6 0006.
 */
@Component
public class AdvertisementFeginFallbackFactory implements FallbackFactory<AdvertisementFegin> {


    @Override
    public AdvertisementFegin create(Throwable throwable) {
        return new AdvertisementFegin() {
            @Override
            public Envelop create(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop update(@RequestBody String jsonData) {
                return null;
            }

            @Override
            public Envelop delete(@RequestParam(value = "code") String code) {
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

            @Override
            public Envelop getListByPatientCode(@RequestParam(value = "patientCode") String patientCode) {
                return null;
            }

            @Override
            public Envelop getListByIp(@RequestParam(value = "ipAddress") String ipAddress) {
                return null;
            }


        };
    }
}
