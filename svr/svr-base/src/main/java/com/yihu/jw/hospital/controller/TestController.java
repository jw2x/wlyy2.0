package com.yihu.jw.hospital.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2017/5/15.
 */
@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${testvalue}")
    private String value;


    @GetMapping("value")
    public String getValue(){
        return value;
    }
}
