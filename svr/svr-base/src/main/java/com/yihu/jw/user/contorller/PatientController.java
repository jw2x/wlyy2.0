package com.yihu.jw.user.contorller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/5/10.
 */
@RestController
@RequestMapping("/patient")
@Api(description = "患者")
public class PatientController {


    @ApiOperation(value = "根据code查找患者")
    @GetMapping(value = "findByCode")
    public String findByCode(
            @ApiParam(name = "code", value = "患者code", required = true)@RequestParam(value = "code", required = true) String code) {
       return "调用根据code查找患者";
    }
}
