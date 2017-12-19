package com.yihu.ehr.iot.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller - 全局错误页
 * Created by progr1mmer on 2017/11/6.
 */
@Controller
@Api(value = "Error", description = "全局错误页")
public class ErrorController extends BaseController{

    @GetMapping("/400")
    @ApiOperation(value = "BAD REQUEST")
    public void badRequest(HttpServletResponse response) throws IOException{
        response.sendRedirect(contextPath + "/front/views/error/400.html");
    }

    @GetMapping("/404")
    @ApiOperation(value = "NOT FOUND")
    public void notFound(HttpServletResponse response) throws IOException{
        response.sendRedirect(contextPath + "/front/views/error/404.html");
    }

    @GetMapping("/500")
    @ApiOperation(value = "INTERNAL SERVER ERROR")
    public void serverError(HttpServletResponse response) throws IOException{
        response.sendRedirect(contextPath + "/front/views/error/500.html");
    }

}
