package com.yihu.jw.feign.fallbackfactory.iot.supplier;

import com.yihu.jw.feign.iot.supplier.IotDeviceSupplierFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeshijie on 2017/12/5.
 */
@Component
public class IotDeviceSupplierFeignFallbackFactory implements FallbackFactory<IotDeviceSupplierFeign> {

    @Override
    public IotDeviceSupplierFeign create(Throwable throwable) {
        return new IotDeviceSupplierFeign() {
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
        };
    }
}
