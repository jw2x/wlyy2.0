package com.yihu.jw.healthyhouse.controller;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.service.user.LoginService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author HZY
 * @created 2018/9/18 19:55
 */
public class LoginController extends EnvelopRestEndpoint {

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    public Envelop login(
            HttpServletRequest request,
            @ApiParam(name = "username", value = "账号", required = true)@RequestParam(required = true, name = "username") String username,
            @ApiParam(name = "password", value = "密码", required = true)@RequestParam(required = true, name = "password") String password) throws ManageException {
        User data = loginService.login(request,username, password);

        return Envelop.getSuccess("登陆成功");
    }

    @GetMapping("/loginout")
    @ApiOperation(value = "退出")
    public Envelop loginout(
            HttpServletRequest request,
            @ApiParam(name = "userCode", value = "用户code", required = true)@RequestParam(required = true, name = "userCode") String userCode) {
        try {
            //修改用户状态  离线
           return Envelop.getSuccess("登出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Envelop.getError("登出成功:" + e.getMessage(), -1);
        }
    }

}
