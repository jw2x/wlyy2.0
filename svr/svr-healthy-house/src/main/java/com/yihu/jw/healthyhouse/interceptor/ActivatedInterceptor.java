package com.yihu.jw.healthyhouse.interceptor;

import com.yihu.jw.healthyhouse.service.user.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  用户激活状态验证拦截器
 * @author HZY
 * @created 2018/10/9 9:19
 */
@Aspect
@Component
public class ActivatedInterceptor  {

    @Autowired
    private UserService userService;

    @Before("execution(* com.yihu.jw.healthyhouse.controller..*.*(..))")
    public void beforeTest(JoinPoint point) throws Throwable {
        System.out.println("beforeTest:" + point.getArgs());
    }


    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        // 得到类对象
        Class userCla = (Class) obj.getClass();
 /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val = new Object();
            try {
                val = f.get(obj);
                map.put(f.getName(), val);// 设置键
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }




}
