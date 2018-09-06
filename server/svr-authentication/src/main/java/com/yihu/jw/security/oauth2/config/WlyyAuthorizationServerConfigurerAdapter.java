package com.yihu.jw.security.oauth2.config;

import com.yihu.jw.security.oauth2.provider.client.WlyyJdbcClientRedirectUriService;
import com.yihu.jw.security.oauth2.provider.WlyyTokenGranter;
import com.yihu.jw.security.oauth2.core.redis.WlyyRedisVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * Config - Spring Oauth2
 * Created by progr1mmer on 2018/8/29.
 */
@Configuration
@EnableAuthorizationServer
public class WlyyAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore());
        endpoints.authorizationCodeServices(authorizationCodeServices());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /*@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(new StandardPasswordEncoder());
    }*/

    @Bean
    @Primary
    WlyyTokenGranter wlyyTokenGranter (
            AuthenticationManager authenticationManager,
            AuthorizationServerTokenServices authorizationServerTokenServices,
            UserDetailsService userDetailsService) {
        WlyyTokenGranter tokenGranter = new WlyyTokenGranter(
                authenticationManager,
                authorizationServerTokenServices,
                authorizationCodeServices(),
                clientDetailsService(),
                new DefaultOAuth2RequestFactory(clientDetailsService()),
                wlyyRedisVerifyCodeService(),
                userDetailsService);
        return tokenGranter;
    }

    @Bean
    ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(jedisConnectionFactory);
    }

    @Bean
    WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService() {
        return new WlyyRedisVerifyCodeService(redisTemplate);
    }

    @Bean
    AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Bean
    WlyyJdbcClientRedirectUriService wlyyJdbcClientRedirectUriService() {
        return new WlyyJdbcClientRedirectUriService(jdbcTemplate);
    }
}
