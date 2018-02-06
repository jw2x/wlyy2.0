package com.yihu.ehr.iot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.iot.util.spring.SpringContextHolder;
import com.yihu.ehr.util.rest.Envelop;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
