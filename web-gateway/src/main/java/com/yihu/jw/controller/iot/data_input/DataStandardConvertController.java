package com.yihu.jw.controller.iot.data_input;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.DataConstants;
import com.yihu.jw.feign.iot.data_input.DataStandardConvertFeign;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DataConstants.DataConvert.api_common)
@Api(value = "数据标准转换", description = "数据标准转换")
public class DataStandardConvertController {

    @Autowired
    private DataStandardConvertFeign dataStandardConvertFeign;

    @PostMapping(value = DataConstants.DataConvert.api_convert, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "数据标准转换", notes = "数据标准转换")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop convert(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        return dataStandardConvertFeign.convert(jsonData);
    }
}
