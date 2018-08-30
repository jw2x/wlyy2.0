package com.yihu.jw.oauth2.config;

import com.yihu.jw.oauth2.core.WlyyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Config - Spring Security
 * Created by progr1mmer on 2018/8/29.
 */
@Configuration
public class WlyyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.authenticationProvider(authenticationProvider);
    }


    @Bean
    WlyyUserDetailsService wlyyUserDetailsService(){
        return new WlyyUserDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());
        return authenticationProvider;
    }

}
