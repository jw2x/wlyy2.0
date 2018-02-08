package com.yihu.ehr.iot.security.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.CurrentRequest;
import com.yihu.ehr.util.rest.Envelop;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by progr1mmer on 2018/1/26.
 */
public class EhrWebContextLogoutHandler extends SecurityContextLogoutHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    SessionRegistry sessionRegistry;

    /**
     * Step 5
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     */
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        Assert.notNull(httpServletRequest, "HttpServletRequest required");
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                this.logger.debug("removeSessionInformation, session: " + session.getId());
                sessionRegistry.removeSessionInformation(session.getId());
            }
    }
}
