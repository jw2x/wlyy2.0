package com.yihu.jw.feign.fallbackfactory.iot.data_input;

import com.yihu.jw.feign.iot.data_input.DataInputFeign;
import com.yihu.jw.restmodel.common.Envelop;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
@Component
public class DataInputFeignFallbackFactory implements FallbackFactory<DataInputFeign> {

    @Autowired
    private Tracer tracer;


    @Override
    public DataInputFeign create(Throwable e) {
        return new DataInputFeign() {
            @Override
            public Envelop bindUser(@RequestBody String jsonData) {
                tracer.getCurrentSpan().logEvent("设备注册绑定失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }

            @Override
            public Envelop uploadData(@RequestBody String jsonData) {
                tracer.getCurrentSpan().logEvent("数据上传失败:原因:" + e.getMessage());
                tracer.getCurrentSpan().logEvent("jsonData:" + jsonData);
                return null;
            }
        };
    }
}
