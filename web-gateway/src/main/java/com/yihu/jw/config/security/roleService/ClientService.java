package com.yihu.jw.config.security.roleService;

import com.yihu.base.security.rbas.ClientServiceProvider;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenweida on 2017/12/1.
 */
@Component("baseClientDetailsService")
public class ClientService implements ClientServiceProvider {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        SaasDO saasDO = saasDao.findByAppId(clientId);
//        if (saasDO == null) {
//            throw new ClientRegistrationException("用户没有注册");
//        }
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId("cwd");
        baseClientDetails.setClientSecret("cwd");

        Set<String> strings = new HashSet<>();
        strings.add("password");
        strings.add("custom_password");
        strings.add("authorization_code");
        strings.add("refresh_token");
        baseClientDetails.setAuthorizedGrantTypes(strings);
        return baseClientDetails;
    }


}
