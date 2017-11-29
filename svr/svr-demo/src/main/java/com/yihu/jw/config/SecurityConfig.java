package com.yihu.jw.config;

import com.yihu.jw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by chenweida on 2017/11/29.
 */
@EnableWebMvcSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    @Autowired
    private BaseAuthenticationSuccessHandler baseAuthenticationSuccessHandler;
    @Autowired
    private BaseAuthenticationFailureHandler baseAuthenticationFailureHandler;

    /**
     * 处理用户密码加密解密
     * 密码加密工具类 验证密码使用 項目中使用要根據自己項目中的加密規則自定義
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * HttpSecurity：一般用它来具体控制权限，角色，url等安全的东西。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/denglu.html") //自定义登陆页面
                .loginProcessingUrl("/authentication/form") //登陆页面的请求路径
                .usernameParameter("username") //登陆页面的usernma
                .passwordParameter("password") //登陆页面的password
                .successHandler(baseAuthenticationSuccessHandler) //认证成功之后的处理
                .failureHandler(baseAuthenticationFailureHandler) //认证失败之后的处理
                .and()
                .authorizeRequests()
                .antMatchers("/denglu.html", "/authentication/form").permitAll() ///denglu.html 不用认证
                .anyRequest().authenticated() //其他请求需要验证
                .and()
                .sessionManagement()  //session 管理器
                .and()
                .userDetailsService(userService)  //自定义用户认证
                .csrf().disable(); //关闭csrf （防止跨站请求仿造攻击）默认是开启的
    }

    /**
     * ：用来做登录认证的
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication();
    }

    /**
     * For example, if you wish to ignore certain requests
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
