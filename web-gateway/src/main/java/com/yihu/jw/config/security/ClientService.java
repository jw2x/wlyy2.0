package com.yihu.jw.config.security;

import com.yihu.base.security.rbas.ClientServiceProvider;
import com.yihu.jw.base.base.SaasDO;
import com.yihu.jw.fegin.base.base.SaasFeign;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chenweida on 2017/12/1.
 */
@Component("baseClientDetailsService")
@Primary
public class ClientService implements ClientServiceProvider {

    @Autowired
    private SaasFeign saasFeign;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Envelop envelop = saasFeign.findByCode(clientId);
        Map saasDO = (Map)envelop.getObj();
        if (saasDO.isEmpty()) {
            throw new ClientRegistrationException("该平台未注册");
        }
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(saasDO.get("id")+"");
        baseClientDetails.setClientSecret("");

        Set<String> strings = new HashSet<>();
        strings.add("password");
        strings.add("custom_password");
        strings.add("authorization_code");
        strings.add("refresh_token");
        baseClientDetails.setAuthorizedGrantTypes(strings);
        return baseClientDetails;
    }

//    public static void main(String[] args) {
//        byte[] a = Base64.encode("1:".getBytes());
//        String s = new String(a);
//        System.out.println(s);
//    }

}
