package com.yihu.jw.feign.fallbackfactory.iot.data_input;

import com.yihu.jw.exception.business.JiWeiException;
import com.yihu.jw.feign.iot.data_input.DataInputFeign;
import com.yihu.jw.feign.iot.data_input.DataStandardConvertFeign;
import com.yihu.jw.feign.iot.supplier.IotDeviceSupplierFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Component
public class DataStandardConvertFeignFallbackFactory implements FallbackFactory<DataStandardConvertFeign> {

    @Autowired
    private Tracer tracer;


    @Override
    public DataStandardConvertFeign create(Throwable throwable) {
        return new DataStandardConvertFeign() {
            @Override
            public Envelop convert(String jsonData) {
                tracer.getCurrentSpan().logEvent("标准转换失败:原因:" + throwable.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }
        };
    }
}
