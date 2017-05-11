package com.yihu.jw.controller;

import com.yihu.jw.fegin.PatientFegin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by chenweida on 2017/5/10.
 */
@RestController
@RequestMapping("/rest/patient")
@Api(description = "患者")
public class PatientController {
    @Autowired
    private PatientFegin patientFegin;

    @ApiOperation(value = "根据code查找患者")
    @GetMapping(value = "findByCode")
    public String findByCode(
            @ApiParam(name = "code", value = "患者code", required = true) @RequestParam(value = "code", required = true) String code) {
        return patientFegin.findByCode(code);
    }
}
