package com.yihu.jw.base.controller;

import com.yihu.jw.base.model.Function;
import com.yihu.jw.base.service.FunctionService;
import com.yihu.jw.restmodel.base.BaseContants;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import com.yihu.jw.restmodel.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/5/19.
 */
@RestController
@RequestMapping(BaseContants.Function.common)
@Api(value = "功能模块", description = "功能模块接口管理")
public class FunctionController extends EnvelopRestController {
    @Autowired
    private FunctionService functionService;

    @PostMapping(value = BaseContants.Function.create, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "创建功能", notes = "创建单个功能")
    public Envelop createFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseContants.Function.message_success_create, functionService.createFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

    @PutMapping(value = BaseContants.Function.update, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改功能", notes = "修改功能")
    public Envelop updateFunction(
            @ApiParam(name = "json_data", value = "", defaultValue = "")
            @RequestBody String jsonData) {
        try {
            Function function = toEntity(jsonData, Function.class);
            return Envelop.getSuccess(BaseContants.Function.message_success_create, functionService.updateFunction(function));
        } catch (ApiException e) {
            return Envelop.getError(e.getMessage(), e.getErrorCode());
        }
    }

}
