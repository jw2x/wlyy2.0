package com.yihu.ehr.iot.security.config;

import com.yihu.ehr.iot.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by progr1mmer on 2018/1/26.
 */
@Configuration
@EnableWebSecurity
public class EhrWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.oauth2InnerUrl}")
    protected String oauth2InnerUrl;
    @Value("${service-gateway.profileInnerUrl}")
    protected String profileInnerUrl;

    @Autowired
    private EhrWebAuthenticationProvider ehrWebAuthenticationProvider;
    @Autowired
    private EhrWebAuthenticationSuccessHandler ehrWebAuthenticationSuccessHandler;
    @Autowired
    private EhrWebAuthenticationFailureHandler ehrWebAuthenticationFailureHandler;
    //@Autowired
    //private EhrWebAccessDecisionManager ehrWebAccessDecisionManager;
    //@Autowired
    //private SessionRegistry sessionRegistry;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/ambulance/search"); //忽略授权地址
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // ---------- 自定义Filter Start ----------
        EhrWebUsernamePasswordAuthenticationFilter ehrWebUsernamePasswordAuthenticationFilter = new EhrWebUsernamePasswordAuthenticationFilter(oauth2InnerUrl, profileInnerUrl);
        ehrWebUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(ehrWebAuthenticationSuccessHandler);
        ehrWebUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(ehrWebAuthenticationFailureHandler);
        ehrWebUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        //ehrWebUsernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
        http.addFilterBefore(ehrWebUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // ---------- 自定义Filter End ----------
        //http.sessionManagement().maximumSessions(3).expiredUrl("/login?expired").sessionRegistry(sessionRegistry);
        //http.addFilter(ehrWebUsernamePasswordAuthenticationFilter);
        http.authorizeRequests()
                //.accessDecisionManager(ehrWebAccessDecisionManager)
                //.antMatchers("/front/views/*.html").hasRole("USER") //拦截html
                //.antMatchers("/user").hasRole("USER")
                //.antMatchers("/ambulance/**").hasRole("USER")
                //.antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/front/css/**").permitAll()
                .antMatchers("/front/fonts/**").permitAll()
                .antMatchers("/front/images/**").permitAll()
                .antMatchers("/front/js/**").permitAll()
                .antMatchers("/front/views/signin.html").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/front/views/**").hasRole("USER")
                .antMatchers("/**").hasRole("USER")
                .and().formLogin().loginPage("/login")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and().headers().frameOptions().disable()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ehrWebAuthenticationProvider); //自定义认证提供者
    }

    @Bean
    EhrWebUserDetailsService ehrWebUserDetailsService(){
        return new EhrWebUserDetailsService(profileInnerUrl);
    }

    @Bean
    EhrWebAuthenticationProvider ehrWebAuthenticationProvider(UserDetailsService userDetailsService) {
        EhrWebAuthenticationProvider ehrWebAuthenticationProvider = new EhrWebAuthenticationProvider(userDetailsService);
        ehrWebAuthenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());
        return ehrWebAuthenticationProvider;
    }

    @Bean
    EhrWebAuthenticationSuccessHandler ehrWebAuthenticationSuccessHandler(){
        return new EhrWebAuthenticationSuccessHandler();
    }

    @Bean
    EhrWebAuthenticationFailureHandler ehrWebAuthenticationFailureHandler(){
        return new EhrWebAuthenticationFailureHandler();
    }
    /**
    @Bean
    EhrWebAccessDecisionManager ehrWebAccessDecisionManager() {
        return new EhrWebAccessDecisionManager(null);
    }
    */

}
