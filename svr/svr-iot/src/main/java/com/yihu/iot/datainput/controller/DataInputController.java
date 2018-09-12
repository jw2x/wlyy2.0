package com.yihu.iot.datainput.controller;

import com.yihu.iot.datainput.service.DataInputService;
import com.yihu.jw.exception.ApiException;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.rm.iot.DataRequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(DataRequestMapping.api_iot_common)
@Api(tags = "数据上传操作", description = "数据上传操作")
public class DataInputController {

    @Autowired
    private DataInputService dataInputService;

    @PostMapping(value = DataRequestMapping.DataInput.api_user_bind, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "设备注册绑定", notes = "设备注册并绑定用户")
    public MixEnvelop bindUser(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return MixEnvelop.getSuccess(DataRequestMapping.DataInput.message_success,dataInputService.bindUser(jsonData));
        } catch (ApiException e){
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PostMapping(value = DataRequestMapping.DataInput.api_data_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "体征数据上传", notes = "数据上传入库")
    public MixEnvelop uploadData(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) throws IOException  {
        String str = "";
        try {
            str = dataInputService.inputBodySignsData(jsonData);
            if (!str.equals("success")) {
                return MixEnvelop.getSuccess(DataRequestMapping.DataInput.message_fail, str);
            }
        } catch (ApiException e) {
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
        return MixEnvelop.getSuccess(DataRequestMapping.DataInput.message_success, str);
    }

    @PostMapping(value = DataRequestMapping.DataInput.api_weRunData_input, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "微信运动数据上传", notes = "微信运动数据上传入库")
    public MixEnvelop uploadWeRunData(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData) {
        String str = "";
        try {
            str = dataInputService.inputWeRunData(jsonData);
            if (!StringUtils.endsWithIgnoreCase("success",str)) {
                return MixEnvelop.getError(DataRequestMapping.DataInput.message_fail, 0);
            }
        } catch (ApiException e) {
            return MixEnvelop.getError(e.getMessage(), e.getErrorCode());
        }
        return MixEnvelop.getSuccess(DataRequestMapping.DataInput.message_success, str);
    }

}
