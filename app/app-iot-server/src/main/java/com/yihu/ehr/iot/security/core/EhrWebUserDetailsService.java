package com.yihu.ehr.iot.security.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.ehr.agModel.user.UserDetailModel;
import com.yihu.ehr.iot.util.http.HttpHelper;
import com.yihu.ehr.iot.util.http.HttpResponse;
import com.yihu.ehr.util.rest.Envelop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by progr1mmer on 2018/1/26.
 */
public class EhrWebUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String profileInnerUrl;

    public EhrWebUserDetailsService(String profileInnerUrl){
        Assert.hasText(profileInnerUrl, "ProfileInnerUrl must not be empty or null");
        this.profileInnerUrl = profileInnerUrl;
    }

    /**
     * Step 2
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("login_code", username);
            HttpResponse httpResponse = HttpHelper.get(profileInnerUrl + "/users/" + username, params);
            if(httpResponse.getStatusCode() == 200) {
                Envelop envelop = this.objectMapper.readValue(httpResponse.getBody(), Envelop.class);
                if (envelop.isSuccessFlg()){
                    String user = this.objectMapper.writeValueAsString(envelop.getObj());
                    UserDetailModel userDetailModel = this.objectMapper.readValue(user, UserDetailModel.class);
                    String password = userDetailModel.getPassword();
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    //登陆成功后需要的属性
                    request.setAttribute("id", userDetailModel.getId());
                    request.setAttribute("username", username);
                    request.setAttribute("realName", userDetailModel.getRealName());
                    return new User(username, password, getGrantedAuthorities(username));
                }
                logger.error(httpResponse.getBody());
                logger.error(envelop.getErrorMsg());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new UsernameNotFoundException(username);
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
