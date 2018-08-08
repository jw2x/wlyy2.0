package com.yihu.iot.controller.common;

import com.yihu.iot.model.Result;
import com.yihu.iot.service.common.LoginService;
import com.yihu.ehr.util.rest.Envelop;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller - 登陆
 * Created by Progr1mmer on 2017/2/21.
 */
@Controller
@RequestMapping("/login")
@Api(value = "Login", description = "登陆控制器")
public class LoginController extends BaseController{

    @Autowired
    private LoginService loginService;

    /**
     * 登陆页面
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "登陆首页")
    public void index(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
//        response.sendRedirect(contextPath + "/front/views/login.html");
        request.getRequestDispatcher("/front/views/login.html").forward(request, response);
    }

    /**
     * 登陆验证
     * @param request
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "登录验证")
    public Result login(HttpServletRequest request,
                        @ApiParam(name = "userName", value = "登录账号")
            @RequestParam(value = "userName") String userName,
                        @ApiParam(name = "password", value = "密码")
            @RequestParam(value = "password") String password) {
            return loginService.login(request, userName, password);
    }

    /**
     * 退出登陆
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exit", method = RequestMethod.GET)
    @ApiOperation(value = "用户安全退出")
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("isLogin");
        request.getSession().removeAttribute("token");
        request.getSession().removeAttribute("loginName");
        request.getSession().removeAttribute("userId");
        response.sendRedirect(contextPath + "/login");
    }

    /**
     * 政府服务平台-oauth2验证集成
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping(value = "/signin")
    @ApiOperation(value = "oauth2验证集成")
    public void signIn(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String clientId = request.getParameter("clientId");
        String host = request.getHeader("referer");
        request.getSession().setAttribute("host", host);
        response.sendRedirect(contextPath + "/front/views/signin.html?clientId=" + clientId);
    }

    /*
     * 自动登录
     */
    @RequestMapping(value = "/autoLogin", method = RequestMethod.POST)
    @ResponseBody
    public Envelop autoLogin(HttpServletRequest request, Model model,
                             @ApiParam(name = "token", value = "TOKEN")
                             @RequestParam(value = "token") String token) throws Exception {
        return loginService.autoLogin(request, model, token);
    }
}
