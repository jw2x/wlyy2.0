package com.yihu.wlyy.statistics.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Administrator on 2016.10.17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.basic.username}")
    String username;
    @Value("${security.basic.password}")
    String password;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/metrics/**").permitAll()
                .antMatchers("/health/**").permitAll()
                .antMatchers("/env/**").permitAll()
                .antMatchers("/dump/**").permitAll()
                .antMatchers("/trace/**").permitAll()
                .antMatchers("/mappings/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/beans/**").permitAll()
                .antMatchers("/configprops/**").permitAll()
                .antMatchers("/docs/**").permitAll()
                .antMatchers("/info/**").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .antMatchers("/jolokia/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().defaultSuccessUrl("/swagger-ui.html").failureUrl("/login") //登录成功之后的跳转
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                .withUser(username).password(password).roles("USER");
    }

}
