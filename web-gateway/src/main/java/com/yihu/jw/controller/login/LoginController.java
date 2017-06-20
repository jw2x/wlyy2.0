package com.yihu.jw.controller.login;

import com.yihu.jw.commnon.wlyy.PatientContants;
import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/6/16.
 */
@RestController
@Api(value = "登陆相关操作", description = "登陆相关操作")
public class LoginController {

    @GetMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "登陆", notes = "登陆")
    public Envelop create(
            @ApiParam(name = "account", value = "", defaultValue = "") @RequestParam(name = "account", required = true) String account) {
        return null;
    }
}
