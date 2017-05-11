package com.yihu.jw.fegin;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenweida on 2017/5/10.
 */
@FeignClient("svr-user")// 值是eurika的实例名字
@RequestMapping("/patient")
public interface PatientFegin {

    @RequestMapping(value = "/findByCode",method = RequestMethod.GET)
    public String findByCode(@RequestParam(value = "code", required = true) String code);

}
