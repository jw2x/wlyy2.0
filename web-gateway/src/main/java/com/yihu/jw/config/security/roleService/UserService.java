package com.yihu.jw.config.security.roleService;

import com.yihu.jw.feign.base.user.EmployeeFeign;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2017/11/29.
 * 获取用户信息
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private EmployeeFeign employeeFeign;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Envelop user= employeeFeign.getEmployeeByAccount(userName);
        return null;
    }
}
