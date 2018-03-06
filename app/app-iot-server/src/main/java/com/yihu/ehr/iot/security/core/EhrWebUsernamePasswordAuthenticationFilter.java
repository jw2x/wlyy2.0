package com.yihu.ehr.iot.security.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.util.rest.Envelop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Sso integrated
 * Created by progr1mmer on 2018/1/27.
 */
public class EhrWebUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static Logger logger = LoggerFactory.getLogger(EhrWebUsernamePasswordAuthenticationFilter.class);

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private ObjectMapper objectMapper = new ObjectMapper();
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private String clientIdParameter = "clientId";
    private String accessTokenParameter = "accessToken";
    private boolean postOnly = true;

    private final String oauth2InnerUrl;
    private final String profileInnerUrl;

    public EhrWebUsernamePasswordAuthenticationFilter(String oauth2InnerUrl, String profileInnerUrl) {
        super(new AntPathRequestMatcher("/login", "POST"));
        Assert.hasText(oauth2InnerUrl, "Oauth2InnerUrl must not be empty or null");
        Assert.hasText(profileInnerUrl, "ProfileInnerUrl must not be empty or null");
        this.oauth2InnerUrl = oauth2InnerUrl;
        this.profileInnerUrl = profileInnerUrl;
    }

    /**
     * Step 1
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = null;
            String password = null;
            if(isSso(request)) {
                Map<String, Object> params = new HashMap<>();
                params.put("clientId", this.obtainClientId(request));
                params.put("accessToken", this.obtainAccessToken(request));
                try {
                    HttpResponse httpResponse = HttpHelper.post(oauth2InnerUrl + "/oauth/validToken", params);
                    if(httpResponse.getStatusCode() == 200) {
                        Map<String, Object> map = objectMapper.readValue(httpResponse.getBody(), Map.class);
                        if ((Boolean) map.get("successFlg")) {
                            String loginName = (String) map.get("user");
                            //验证通过。赋值session中的用户信息
                            params.clear();
                            params.put("login_code", loginName);
                            httpResponse = HttpHelper.get(profileInnerUrl + "/users/" + loginName, params);
                            Envelop envelop = this.objectMapper.readValue(httpResponse.getBody(), Envelop.class);
                            String user = this.objectMapper.writeValueAsString(envelop.getObj());
                            UserDetailModel userDetailModel = this.objectMapper.readValue(user, UserDetailModel.class);
                            username = userDetailModel.getLoginCode();
                            password = userDetailModel.getPassword();
                        }
                    }else {
                        logger.error(httpResponse.getBody());
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                username = this.obtainUsername(request);
                password = this.obtainPassword(request);
            }

            if(username == null) {
                username = "";
            }
            if(password == null) {
                password = "";
            }

            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new EhrWebAuthenticationToken(username, password, isSso(request)); //单点登陆集成

            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    //单点登陆集成 ------------ Start -------------
    protected String obtainClientId(HttpServletRequest request) {
        return request.getParameter(this.clientIdParameter);
    }

    protected String obtainAccessToken(HttpServletRequest request) {
        return request.getParameter(this.accessTokenParameter);
    }
    //单点登陆集成 ------------ End -------------

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    //单点登陆集成 ------------ Start -------------
    public void setClientIdParameter(String clientIdParameter) {
        Assert.hasText(clientIdParameter, "ClientId parameter must not be empty or null");
        this.clientIdParameter = clientIdParameter;
    }

    public void setAccessTokenParameter(String accessTokenParameter) {
        Assert.hasText(accessTokenParameter, "AccessTokenParameter parameter must not be empty or null");
        this.accessTokenParameter = accessTokenParameter;
    }
    //单点登陆集成 ------------ End -------------

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public final String getClientIdParameter() {
        return this.clientIdParameter;
    }

    public final String getAccessTokenParameter() {
        return this.accessTokenParameter;
    }

    public final String getUsernameParameter() {
        return this.usernameParameter;
    }

    public final String getPasswordParameter() {
        return this.passwordParameter;
    }

    private boolean isSso(HttpServletRequest request){
        return null != request.getParameter(accessTokenParameter);
    }

}
