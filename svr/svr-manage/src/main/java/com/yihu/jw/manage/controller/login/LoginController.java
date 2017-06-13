package com.yihu.jw.manage.controller.login;

import com.yihu.jw.manage.cache.login.LoginCache;
import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.login.LoginService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/8.
 */
@RestController
@Api(description = "登陆模块")
public class LoginController extends EnvelopRestController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    public Envelop login(
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "password", value = "密码", required = true)@RequestParam(required = true, name = "password") String password) {
        try {
            ManageUser data = loginService.login(username, password);
            return Envelop.getSuccess("登陆成功", data);
        } catch (Exception e) {
            error(e);
            return Envelop.getError("登陆失败:" + e.getMessage(), -1);
        }
    }

    @GetMapping("/loginout")
    @ApiOperation(value = "退出")
    public Envelop loginout(
            @ApiParam(name = "userCode", value = "用户code", required = true)@RequestParam(required = true, name = "userCode") String userCode) {
        try {
            //从缓存清空
            LoginCache.cleanUser(userCode);
            return Envelop.getSuccess("登出成功");
        } catch (Exception e) {
            error(e);
            return Envelop.getError("登出成功:" + e.getMessage(), -1);
        }
    }

    @GetMapping("/index")
    @ApiOperation(value = "index页面需要的参数,菜单 用户信息")
    public Envelop index(
            @ApiParam(name = "userCode", value = "用户code", required = true)  @RequestParam(required = true, name = "userCode") String userCode
    ) {
        try {
            Map<String, List> data = loginService.index(userCode);
            return Envelop.getSuccess("获取信息成功", data);
        } catch (Exception e) {
            return Envelop.getError("获取信息成功:" + e.getMessage(), -1);
        }
    }
}
