package com.yihu.jw.service;

import com.yihu.base.security.rbas.ClientServiceProvider;
import com.yihu.jw.model.SaasDO;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * Created by chenweida on 2017/12/1.
 */
@Component("baseClientDetailsService")
public class ClientService implements ClientServiceProvider {
    /**
     * 根据自己的业务查询表 返回相关的平台用户信息
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
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
