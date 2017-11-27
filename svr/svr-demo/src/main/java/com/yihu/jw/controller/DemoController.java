package com.yihu.jw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweida on 2017/11/3.
 */
@RestController
public class DemoController {

    @GetMapping("demo")
    public String demo() throws Exception {
        return "123";
    }

}
