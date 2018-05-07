package com.yihu.jw.config.security;


import com.yihu.jw.base.user.BaseEmployDO;
import com.yihu.jw.fegin.base.user.EmployFeign;
import com.yihu.jw.restmodel.common.Envelop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by chenweida on 2017/11/29.
 * 处理用户校验
 */
@Component
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployFeign employFeign;

//    @Autowired
//    private EmployFeign employFeign;

    /**
     * 我们只需要把用户返回给spring-security 密码框架自己帮我们校验
     *
     * @param saasPhone
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String saasPhone) throws UsernameNotFoundException {

        //saasPhone=saas+phone
        //根据userName获取账户信息（手机号码）
        String[] sp = saasPhone.split(",");
        String phone = sp[0];
        String saasId = sp[1];
        Envelop envelop = employFeign.getEmployeeByPhoneAndSaasId(phone,saasId);
        Map baseEmployDO =  (Map)envelop.getObj();
        if (!baseEmployDO.isEmpty()) {
            return new User(saasPhone,
                    passwordEncoder.encode(baseEmployDO.get("password")+""),
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
}
