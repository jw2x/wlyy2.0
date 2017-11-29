package com.yihu.oauth2.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by chenweida on 2017/11/28.
 */
@Configuration
@EnableAuthorizationServer  //开启授权服务器
public class AuthorizationConfig {
    /**
     * token使用jdbc存储
     * @param dataSource
     * @return
     */
    @Bean
    public TokenStore createTokenStore(DataSource dataSource){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService createClientDetailsService(
            @Qualifier("myDefaultBaseClientDetails")ClientDetails clientDetails,
            DataSource dataSource){
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.addClientDetails(clientDetails);

        return jdbcClientDetailsService;
    }

    @Bean(name = "myDefaultBaseClientDetails")
    public BaseClientDetails createBaseClientDetails(){
        BaseClientDetails baseClientDetails = new BaseClientDetails();

        baseClientDetails.setClientId("whc_client_id");
        baseClientDetails.setClientSecret("whc_client_secret");

        LinkedList<String> scope = new LinkedList<String>();
        scope.add("whc");
        baseClientDetails.setScope(scope);

        Set<String> registeredRedirectUris = new HashSet<String>();
        registeredRedirectUris.add("http://localhost:8080/test");
        baseClientDetails.setRegisteredRedirectUri(registeredRedirectUris);

        LinkedList<String> grant_types = new LinkedList<String>();
        grant_types.add("client_credentials");
        baseClientDetails.setAuthorizedGrantTypes(grant_types);

        baseClientDetails.setAccessTokenValiditySeconds(24 * 60 * 60);
        baseClientDetails.setRefreshTokenValiditySeconds(48 * 60 * 60);

        LinkedList<String> autoApproveScopes = new LinkedList<String>();
        autoApproveScopes.add("whc");
        baseClientDetails.setAutoApproveScopes(autoApproveScopes);

        return baseClientDetails;
    }
}
