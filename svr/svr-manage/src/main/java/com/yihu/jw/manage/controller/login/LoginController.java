package com.yihu.jw.manage.controller.login;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.login.LoginService;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.common.EnvelopRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/6/8.
 */
@RestController
public class LoginController extends EnvelopRestController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public Envelop login(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "password") String password) {
        try {
            ManageUser data = loginService.login(username, password);
            return Envelop.getSuccess("登陆成功", data);
        }catch (Exception e){
            return Envelop.getError("登陆失败:"+e.getMessage(), -1);
        }
    }

    @GetMapping("/index")
    public Envelop index(
            @RequestParam(required = true, name = "usercode") String usercode
    ) {
        try {
            Map<String, List> data = loginService.index(usercode);
            return Envelop.getSuccess("获取信息成功", data);
        }catch (Exception e){
            return Envelop.getError("获取信息成功:"+e.getMessage(), -1);
        }
    }
}
