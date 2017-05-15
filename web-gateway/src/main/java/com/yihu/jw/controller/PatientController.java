package com.yihu.jw.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.PatientFegin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

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
    //配置HystrixProperty 则调用的方法和fallback是同一个线程 否则就不是
    //@HystrixCommand(fallbackMethod = "findByCodeFallback",commandProperties = @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"))
    //@HystrixCommand(fallbackMethod = "findByCodeFallback" )
    public String findByCode(
            @ApiParam(name = "code", value = "患者code", required = true) @RequestParam(value = "code", required = true) String code) {
        return patientFegin.findByCode(code);
    }

//    /**
//     * 参数要一致 返回值类型也要一致
//     *
//     * @param code
//     * @return
//     */
//    public String findByCodeFallback(String code) {
//        return "";
//    }
}
