package com.yihu.jw.service;

import com.yihu.jw.dao.SaasDao;
import com.yihu.jw.model.SaasDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/12/1.
 */
@Service
public class ClientService implements ClientDetailsService {
    @Autowired
    private SaasDao saasDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        SaasDO saasDO = saasDao.findByAppId(clientId);
        if (saasDO == null) {
            throw new ClientRegistrationException("用户没有注册");
        }
        return saasDO;
    }
}
