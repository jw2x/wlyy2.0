package com.yihu.jw.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yeshijie on 2018/9/14
 */
@Aspect
@Component
public class HttpAdvice {
    @Autowired
    private Tracer tracer;
    @Autowired
    private ObjectMapper objectMapper;
    private Logger logger = LoggerFactory.getLogger(HttpAdvice.class);


    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object process(ProceedingJoinPoint point) throws Throwable {

        //保存入参
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        tracer.getCurrentSpan().logEvent(objectMapper.writeValueAsString(request.getParameterMap()));
        //访问目标方法的参数：
        Object[] args = point.getArgs();

        Long strartTime = System.currentTimeMillis();
        Object returnValue = point.proceed(args);
        Long endTime = System.currentTimeMillis();
        //保存响应
        tracer.getCurrentSpan().logEvent(objectMapper.writeValueAsString(returnValue));
        //保存响应时间
        tracer.getCurrentSpan().logEvent("executeTime:"+(endTime - strartTime));

        return returnValue;
    }

}
