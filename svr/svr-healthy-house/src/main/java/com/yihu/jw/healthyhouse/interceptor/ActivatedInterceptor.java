package com.yihu.jw.healthyhouse.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.service.user.UserService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import net.sf.json.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户在线状态验证拦截器
 *
 * @author HZY
 * @created 2018/10/9 9:19
 */
@Aspect
@Component
public class ActivatedInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ActivatedInterceptor.class);

    @Autowired
    private UserService userService;

//    @Around("execution(* com.yihu.jw.healthyhouse.controller..*.*(..))")
//    public Object logAround(ProceedingJoinPoint  joinPoint) throws Throwable{
//        ObjectMapper objectMapper = new ObjectMapper();
//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        HttpSession session = request.getSession();
//        String method = request.getMethod();
//        String queryString = request.getQueryString();
//
//        String classType = joinPoint.getTarget().getClass().getName();
//        Class<?> clazz = Class.forName(classType);
//        String clazzName = clazz.getName();
//        String methodName = joinPoint.getSignature().getName(); //获取方法名称
//        Object[] args = joinPoint.getArgs();//参数
//
//        String params = "";
//        //获取请求参数集合并进行遍历拼接
//        if(args.length>0){
//            if("POST".equals(method)){
//                Object object = args[0];
//                Map map = getFieldsName(this.getClass(), clazzName, methodName,args);
//                params = objectMapper.writeValueAsString(map);
//            }else if("GET".equals(method)){
//                Map<String, String[]> parameterMap = request.getParameterMap();
//                params = objectMapper.writeValueAsString(parameterMap);;
//            }
//        }
//        //获取参数名称和值
////        Map<String,Object > nameAndArgs = getFieldsName(this.getClass(), clazzName, methodName,args);
//        System.out.println(params);
//        return  joinPoint.proceed();
//
//    }


    @Around("execution(* com.yihu.jw.healthyhouse.controller..*.*(..))")
    public Object activatedAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (requestUri.indexOf("/login") != -1
                || requestUri.indexOf("/register") != -1
                || requestUri.indexOf("/loginout") != -1
                || requestUri.indexOf("/getRandomImageCode") != -1
                || requestUri.indexOf("/checkRandomImageCode") != -1
                || requestUri.indexOf("/captcha") != -1
                || requestUri.indexOf("swagger") != -1
                || requestUri.indexOf(contextPath + "/v2/api-docs") != -1) {

            return joinPoint.proceed();
        }

        String userId = request.getHeader("userId");
        if (userId == null) {
            response.setStatus(402);
            return joinPoint.proceed();
//            return failed("用户未登录，请登录！",-10000);
        }
        User user = userService.findById(userId);
        if (user == null) {
            response.setStatus(402);
            return joinPoint.proceed();
//            return failed("用户不存在，请重新登录！",-10000);
        } else if (HouseUserContant.activated_lock.equals(user.getActivated())) {
            response.setStatus(103);
//           return failed("用户已被冻结，请联系管理员！",-10000);
        } else if (HouseUserContant.activated_offline.equals(user.getActivated())) {
            response.setStatus(402);
//            return failed("用户已离线，请重新登录！",-10000);
        }
        return joinPoint.proceed();

    }


    private Map<String, Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        Map<String, Object> map = new HashMap<String, Object>();

        ClassPool pool = ClassPool.getDefault();
        //ClassClassPath classPath = new ClassClassPath(this.getClass());
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 1 : 2;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            map.put(attr.variableName(i + pos), args[i]);//paramNames即参数名
        }
        return map;
    }


    protected Envelop failed(String message, int status) {
        Envelop envelop = new Envelop();
        envelop.setMessage(message);
        envelop.setStatus(status);
        return envelop;
    }




}
