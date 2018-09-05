package com.yihu.jw.security.web.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint - 基础登陆
 * Created by progr1mmer on 2018/8/29.
 */
@RestController
public class BaseLoginEndpoint {

    @GetMapping(value = "/login")
    public String login() {
        return "Get method no support for path /login";
    }

}
