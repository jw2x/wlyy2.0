package com.yihu.health.util;

import com.yihu.ehr.agModel.user.UserDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lith
 * @created 2018/02/06
 */
@Component
public class CurrentRequest {

    @Autowired
    SessionRegistry sessionRegistry;

    /**
     * 获取当前登录用户，当前已登录的用户都缓存在session中
     * @param request
     * @return
     */
    public  UserDetailModel getCurrentUser(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        UserDetailModel user = null;
        SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
        if(null != sessionInformation.getPrincipal()){
            user = (UserDetailModel)sessionInformation.getPrincipal();
        }
        return user;
    }
}
