package com.yihu.iot.datainput.controller;

import com.yihu.iot.datainput.service.DataStandardConvertService;
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
@Api(value = "数据标准转换操作", description = "数据标准转换接口")
public class DataStandardConvertController {

    @Autowired
    private DataStandardConvertService dataStandardConvertService;

    @PostMapping(value = DataRequestMapping.DataStandardConvert.api_convert, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "数据标准转换", notes = "数据标准转换")
    public Envelop convert(@ApiParam(name = "json_data", value = "", defaultValue = "") @RequestBody String jsonData){
        try{
            return Envelop.getSuccess(DataRequestMapping.DataStandardConvert.message_success_convert,dataStandardConvertService.iconvert(jsonData));
        } catch (ApiException e){
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
