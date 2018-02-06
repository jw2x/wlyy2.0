package com.yihu.ehr.iot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.util.rest.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

    @Value("${service-gateway.profileInnerUrl}")
    protected String profileInnerUrl;

    @Autowired
    private ObjectMapper objectMapper;

    public static Map<String,UserDetailModel> userMap = new ConcurrentHashMap<>(1000);

    public UserDetailModel getCurrentUserByName(String username) throws IOException {
        UserDetailModel user = null;
        if(userMap.containsKey(username)){
            return userMap.get(username);
        }
        Map params = new HashMap<>();
        params.put("login_code", username);
        HttpResponse httpResponse = HttpHelper.get(profileInnerUrl + "/users/" + username, params);
        if(httpResponse.getStatusCode() == 200) {
            Envelop envelop = this.objectMapper.readValue(httpResponse.getBody(), Envelop.class);
            if (envelop.isSuccessFlg()){
                String userString = this.objectMapper.writeValueAsString(envelop.getObj());
                user = this.objectMapper.readValue(userString, UserDetailModel.class);
                userMap.put(username,user);
            }
        }
        return user;
    }
}
