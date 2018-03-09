package com.yihu.ehr.iot.security.entryPoint;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yeshijie on 2018/3/9.
 */
public class LoginEntryPoint extends LoginUrlAuthenticationEntryPoint {


    public LoginEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        JSONObject json = new JSONObject();
        json.put("status",998);
        json.put("errorMsg","未登录或，登录超时");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(json.toString());
        response.getWriter().flush();

//        super.commence(request, response, authException);
    }


}
