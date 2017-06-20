package com.yihu.jw.manage.interceptors;

import com.yihu.jw.manage.model.system.ManageUser;
import com.yihu.jw.manage.service.system.UserService;
import com.yihu.jw.restmodel.common.Envelop;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2017/6/9.
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    private static Integer NOT_LOGIN=-1000;
    List<String> unFilters=new ArrayList<>();
    @Autowired
    private UserService userService;
    @PostConstruct
    public void addUnFilterURI(){
        //添加不需要过滤的路径
        unFilters.add("/login");
        unFilters.add("/error");
    }
    @Override
    public boolean preHandle(HttpServletRequest requset, HttpServletResponse response, Object o) throws Exception {
        boolean flag = true;
        try {
            //判断路径是否要过滤
            String uri=requset.getRequestURI();
            if (unFilters.contains(uri)){
                return true;
            }
            String obj= requset.getParameter("userCode");
            if(org.springframework.util.StringUtils.isEmpty(obj)){
                // 未登录
                response.getOutputStream().write(JSONObject.fromObject(Envelop.getError("请登录后再操作！",NOT_LOGIN)).toString().getBytes());
            }
            //判断usercode是否存在
            ManageUser manageUser= userService.findByCode(obj);
            if(manageUser==null){
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
