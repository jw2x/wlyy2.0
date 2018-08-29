package com.yihu.jw.fegin.iot.data_input;

import com.yihu.jw.fegin.fallbackfactory.iot.data_input.DataInputFeignFallbackFactory;
import com.yihu.jw.common.CommonContants;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        name = CommonContants.svr_iot // name值是eurika的实例名字
        ,fallbackFactory  = DataInputFeignFallbackFactory.class
)
@RequestMapping(DataRequestMapping.api_iot_common)
public interface DataInputFeign {

    @PostMapping(value = DataRequestMapping.DataInput.api_user_bind, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop bindUser(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataInput.api_data_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop uploadData(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataInput.api_weRunData_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MixEnvelop uploadWeRunData(@RequestBody String jsonData);

}
