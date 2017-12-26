package com.yihu.ehr.iot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURL().indexOf("/ambulance/search")!=-1){
            return true;
        }
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        if (isLogin == null || !isLogin) {
            response.sendRedirect("/iot/login");
            response.setStatus(-200);
            return false;
        }
        return true;
    }
}
