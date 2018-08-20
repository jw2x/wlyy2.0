package com.yihu.health.security.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
