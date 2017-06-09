package com.yihu.jw.manage.interceptors;

import com.yihu.jw.restmodel.common.Envelop;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by chenweida on 2017/6/9.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    private static Integer NOT_LOGIN=-1000;


    @Override
    public boolean preHandle(HttpServletRequest requset, HttpServletResponse response, Object o) throws Exception {
        boolean flag = true;
        try {
            Object obj= requset.getParameterMap().get("userCode");
            if(org.springframework.util.StringUtils.isEmpty(obj)){
                // 未登录
                response.getOutputStream().write(JSONObject.fromObject(Envelop.getError("请登录后再操作！",NOT_LOGIN)).toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
