package com.yihu.jw.feign.iot.data_input;

import com.yihu.jw.feign.fallbackfactory.iot.data_input.DataInputFeignFallbackFactory;
import com.yihu.jw.restmodel.CommonContants;
import com.yihu.jw.restmodel.common.Envelop;
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
    Envelop bindUser(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataInput.api_data_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop uploadData(@RequestBody String jsonData);

    @PostMapping(value = DataRequestMapping.DataInput.api_weRunData_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Envelop uploadWeRunData(@RequestBody String jsonData);

}
