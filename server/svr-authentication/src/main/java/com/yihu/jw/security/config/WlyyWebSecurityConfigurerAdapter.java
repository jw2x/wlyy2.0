package com.yihu.jw.security.config;

import com.yihu.jw.security.web.authentication.WlyyAuthenticationFailureHandler;
import com.yihu.jw.security.core.userdetails.jdbc.WlyyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Config - Spring Security
 * Created by progr1mmer on 2018/8/29.
 */
@Configuration
@EnableWebSecurity
public class WlyyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/**").hasRole("USER")
                .and().formLogin().loginPage("/login").failureHandler(new WlyyAuthenticationFailureHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    WlyyUserDetailsService wlyyUserDetailsService(){
        return new WlyyUserDetailsService(dataSource);
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(wlyyUserDetailsService());
        authenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());
        ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
        reflectionSaltSource.setUserPropertyToUse("salt");
        authenticationProvider.setSaltSource(reflectionSaltSource);
        authenticationProvider.setSaltSource(reflectionSaltSource);
        return authenticationProvider;
    }

}
