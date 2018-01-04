package com.yihu.iot.data_input.controller;

import com.yihu.iot.data_input.service.DataInputService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
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
@RequestMapping(DataRequestMapping.api_iot_common)
@Api(value = "数据上传操作", description = "数据上传操作")
public class DataInputController {

    @Autowired
    private DataInputService dataInputService;

    @PostMapping(value = DataRequestMapping.DataInput.api_user_bind, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "设备注册绑定", notes = "设备注册并绑定用户")
    public Envelop bindUser(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success,dataInputService.bindUser(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataInput.api_data_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "数据上传", notes = "数据上传入库")
    public Envelop uoloadData(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try {
            return Envelop.getSuccess(DataRequestMapping.DataInput.message_success, dataInputService.uploadData(jsonData));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }
}
