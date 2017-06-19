package com.yihu.jw.aspect;

import com.yihu.jw.restmodel.gateway.GatewayContanrts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenweida on 2017/6/19.
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private Tracer tracer;

    //Controller层切点
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {
        ;
        Object o = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            //访问前日志
            tracer.getCurrentSpan().tag(GatewayContanrts.ZipkinElasticKey.gateway_input_params,new JSONObject(request.getParameterMap()).toString());
            o = point.proceed();
            //访问后日志
            tracer.getCurrentSpan().tag(GatewayContanrts.ZipkinElasticKey.gateway_out_params,new JSONObject(o).toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return o;
    }

}
