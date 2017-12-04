package com.yihu.jw.config.security.roleService;

import com.yihu.jw.feign.base.user.EmployFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/11/29.
 * 获取用户信息
 */
@Service
public class ClientService implements ClientDetailsService {
    @Autowired
    private EmployFeign employeeFeign;


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}
