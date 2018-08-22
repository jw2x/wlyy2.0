package com.yihu.jw.fegin.iot.data_input;

import com.yihu.jw.fegin.fallbackfactory.iot.data_input.DataStandardConvertFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = DataStandardConvertFeignFallbackFactory.class
)
@RequestMapping(DataRequestMapping.api_iot_common)
public interface DataStandardConvertFeign {

    @PostMapping(value = DataRequestMapping.DataStandardConvert.api_convert, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop convert(@RequestBody String jsonData);
}
