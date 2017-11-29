package com.yihu.jw.service;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.yihu.jw.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by chenweida on 2017/11/29.
 * 处理用户校验
 */
@Service
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
            System.out.printf("password:"+passwordEncoder.encode("123456"));
            return new User("admin",
                    "123456",
                    true,
                    true,
                    true,
                    true,
                    new ArrayList<>()  //权限
            );
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }
}
