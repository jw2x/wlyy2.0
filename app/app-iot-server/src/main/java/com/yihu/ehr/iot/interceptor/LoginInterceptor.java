package com.yihu.ehr.iot.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${server.contextPath}")
    protected String contextPath;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(request.getRequestURL().indexOf("/ambulance/search")!=-1){
//            return true;
//        }
//        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
//        if (isLogin == null || !isLogin) {
//            response.sendRedirect(contextPath+"/login");
//            response.setStatus(-200);
//            return false;
//        }
        return true;
    }
}
