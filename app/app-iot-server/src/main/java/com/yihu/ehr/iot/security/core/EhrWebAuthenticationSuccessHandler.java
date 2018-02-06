package com.yihu.ehr.iot.security.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.CurrentRequest;
import com.yihu.ehr.util.rest.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by progr1mmer on 2018/1/26.
 */
public class EhrWebAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    //@Autowired
    //private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    /**
     * Step 4
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Envelop envelop = new Envelop();
        envelop.setSuccessFlg(true);
        Map userMap = new HashMap();
        String id = (String) httpServletRequest.getAttribute("id");
        String username = (String) httpServletRequest.getAttribute("username");
        String realName = (String) httpServletRequest.getAttribute("realName");
        userMap.put("id", id);
        userMap.put("username", username);
        userMap.put("realName", realName);
        envelop.setObj(userMap);

        List modelList = new ArrayList<>();
        UserDetailModel userDetailModel = (UserDetailModel)httpServletRequest.getAttribute("user");
        modelList.add(userDetailModel);
        envelop.setDetailModelList(modelList);

        //Map<String, Object> sessionMap = findByIndexNameSessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().print(objectMapper.writeValueAsString(envelop));
    }
}
