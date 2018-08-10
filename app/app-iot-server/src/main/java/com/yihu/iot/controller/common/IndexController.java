package com.yihu.iot.controller.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller - 首页
 * Created by progr1mmer on 2017/11/6.
 */
@Controller
@RequestMapping("/index")
@Api(value = "Index", description = "首页")
public class IndexController extends BaseController {

    /**
     * 首页
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "首页")
    public void index(HttpServletResponse response)throws IOException {
        response.sendRedirect(contextPath + "/front/views/index.html");
    }

}
