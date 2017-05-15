package com.yihu.jw.fegin;

import com.yihu.jw.fegin.fallback.PatientFeginFallback;
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
@FeignClient(
        name = "svr-user", // name值是eurika的实例名字
        fallback = PatientFeginFallback.class// fallback是请求超时或者错误的回调函数
//        ,configuration =   //可以配置当个fegin的配置 例如禁用单个feign的hystrix
    )
@RequestMapping("/patient")
public interface PatientFegin {

    @RequestMapping(value = "/findByCode", method = RequestMethod.GET)
    public String findByCode(@RequestParam(value = "code", required = true) String code);

}
