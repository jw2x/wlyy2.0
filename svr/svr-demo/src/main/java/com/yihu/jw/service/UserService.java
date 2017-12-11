package com.yihu.jw.service;


import com.yihu.jw.model.SaasDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by chenweida on 2017/11/29.
 * 处理用户校验
 */
@Component
@Primary
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 我们只需要把用户返回给spring-security 密码框架自己帮我们校验
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if ("admin".equals(userName)) {
            return new User("admin",
                    passwordEncoder.encode("123456"),
                    true,
                    true,
                    true,
                    true
                    , AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER") //权限
            );
        } else if ((!StringUtils.isEmpty(userName))&&userName.length() == 11) {
            return new User("admin",
                    passwordEncoder.encode("123456"),
                    true,
                    true,
                    true,
                    true
                    , AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER") //权限
            );
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

        System.out.println(new String(Base64Utils.encode(("cwd:"+bCryptPasswordEncoder.encode("cwd")).getBytes())));
    }
}
