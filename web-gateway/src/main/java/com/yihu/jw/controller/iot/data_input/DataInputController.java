package com.yihu.jw.controller.iot.data_input;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.commnon.iot.DataConstants;
import com.yihu.jw.fegin.iot.data_input.DataInputFeign;
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
@RequestMapping(DataConstants.DataInput.api_common)
@Api(value = "数据上传入库", description = "数据上传入库")
public class DataInputController {

    @Autowired
    private DataInputFeign dataInputFeign;

    @PostMapping(value = DataConstants.DataInput.api_bind_user, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "设备注册绑定", notes = "设备注册并绑定居民信息")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop bindUser(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) {
        return dataInputFeign.bindUser(jsonData);
    }

    @PostMapping(value = DataConstants.DataInput.api_upload_data, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "体征数据上传", notes = "体征数据上传")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop uploadData(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) {
        return dataInputFeign.uploadData(jsonData);
    }

    @PostMapping(value = DataConstants.DataInput.api_upload_weRunData, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "微信运动数据上传", notes = "微信运动数据上传")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false") })
    public Envelop uploadWeRunData(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) {
        return dataInputFeign.uploadWeRunData(jsonData);
    }
}
