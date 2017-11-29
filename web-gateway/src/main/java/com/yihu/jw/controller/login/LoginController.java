package com.yihu.jw.controller.login;

import com.yihu.jw.restmodel.common.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/6/16.
 */
@RestController
@Api(value = "登陆相关操作", description = "登陆相关操作")
public class LoginController {
    @Autowired
    private Tracer tracer;


    @GetMapping(value = "/employLogin", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "患者登陆", notes = "患者登陆")
    public Envelop employLogin(
            @ApiParam(name = "account", value = "账号", defaultValue = "")
            @RequestParam(name = "account", required = true) String account,
            @ApiParam(name = "password", value = "密码", defaultValue = "")
            @RequestParam(name = "password", required = true) String password,
            @ApiParam(name = "type", value = "登陆方式(默认1)：1账号密码 2账号验证码", defaultValue = "")
            @RequestParam(name = "type", required = false, defaultValue = "1") Integer type) throws Exception {

        return null;
    }
}
