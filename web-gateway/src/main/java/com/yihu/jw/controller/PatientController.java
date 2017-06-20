package com.yihu.jw.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yihu.jw.fegin.PatientFegin;
import com.yihu.jw.restmodel.common.Envelop;
import com.yihu.jw.restmodel.exception.SystemException;
import com.yihu.jw.restmodel.exception.SecurityException;
import com.yihu.jw.restmodel.exception.business.ManageException;
import com.yihu.jw.version.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenweida on 2017/5/10.
 */
@RestController
@RequestMapping("/{version}/patient")
@Api(description = "患者")
@RefreshScope
public class PatientController {
    private Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    private PatientFegin patientFegin;
    @Autowired
    private Tracer tracer;
    @Value("${test.aaa}")
    private String aaaa;

    @GetMapping("/hello")
    @ApiVersion(1)
    @ResponseBody
    public Envelop hello1(@RequestParam(name = "id") Integer id,
                          @RequestParam(name = "name") String name,
                          HttpServletRequest request
    ) throws Exception {
        tracer.getCurrentSpan().logEvent("logEvent");
        tracer.getCurrentSpan().tag("test","tag");
        tracer.getCurrentSpan().setBaggageItem("test","BaggageItem");

        switch (id) {
            case 1: {
                throw new ManageException("后台管理系统异常");
            }
            case 2: {
                throw new SecurityException("权限异常");
            }
            case 3: {
                throw new SystemException("后台系统异常");
            }
        }

        return Envelop.getSuccess("请求成功");
    }

    @GetMapping("/hello")
    @ApiVersion(2)
    @ResponseBody
    public String hello2(String id) throws Exception {
        System.out.println("haha2.........");
        return "hello2";
    }

    @GetMapping("/refresh")
    @ApiVersion(0)
    @ResponseBody
    public String refresh(HttpServletRequest request) throws Exception {
        return aaaa;
    }


    @ApiOperation(value = "根据code查找患者")
    @GetMapping(value = "findByCode")
    //配置HystrixProperty 则调用的方法和fallback是同一个线程 否则就不是
    //@HystrixCommand(fallbackMethod = "findByCodeFallback",commandProperties = @HystrixProperty(name = "execution.isolation.strategy",value = "SEMAPHORE"))
//   @HystrixCommand(fallbackMethod = "findByCodeFallback" )
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "-1"),//超时时间
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")})
    public String findByCode(
            @ApiParam(name = "code", value = "患者code", required = true) @RequestParam(value = "code", required = true) String code) {
        tracer.getCurrentSpan().logEvent("开始调用微服务查询患者");
        String text1 = patientFegin.findByCode(code);
        tracer.getCurrentSpan().logEvent("查询调用微服务找患者结束");
        return text1;
    }

//    /**
//     * 参数要一致 返回值类型也要一致
//     *
//     * @param code
//     * @return
//     */
//    public String findByCodeFallback(String code) {
//        return "启动断路器";
//    }


}
