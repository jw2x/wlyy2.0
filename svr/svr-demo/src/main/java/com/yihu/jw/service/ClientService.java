package com.yihu.jw.service;

import com.yihu.base.security.rbas.ClientServiceProvider;
import com.yihu.jw.dao.SaasDao;
import com.yihu.jw.model.SaasDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenweida on 2017/12/1.
 */
@Component("baseClientDetailsService")
public class ClientService implements ClientServiceProvider {

    public ClientService() {
        System.out.println("初始化");
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//        SaasDO saasDO = saasDao.findByAppId(clientId);
//        if (saasDO == null) {
//            throw new ClientRegistrationException("用户没有注册");
//        }
        SaasDO baseClientDetails = new SaasDO();
        baseClientDetails.setAppId("cwd");
        baseClientDetails.setAppSecret("cwd");

        baseClientDetails.getAuthorizedGrantTypes();
        return baseClientDetails;
    }


}
