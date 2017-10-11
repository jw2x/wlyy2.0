package com.yihu.jw.manage.aop;

import com.yihu.jw.restmodel.gateway.GatewayContanrts;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Controller层切点
    @Pointcut("execution(* com.yihu.jw.manage..*.*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect() && @annotation(com.yihu.jw.manage.aop.annotation.ManageLog)")
    public Object checkToken(ProceedingJoinPoint point) throws Throwable {
        ;
        Object o = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
             o = point.proceed();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return o;
    }

}
