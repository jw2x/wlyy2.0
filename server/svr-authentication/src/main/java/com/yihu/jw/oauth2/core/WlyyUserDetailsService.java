package com.yihu.jw.oauth2.core;

import com.yihu.jw.oauth2.model.WlyyUserSimple;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service - 用户加载
 * Created by progr1mmer on 2018/8/29.
 */
public class WlyyUserDetailsService extends JdbcDaoSupport implements UserDetailsService {

    private static final String DEFAULT_USER_DETAILS_STATEMENT = "SELECT * FROM user u WHERE u.username = ? OR u.mobile = ? OR u.idcard = ?";

    /**
     * 用户登录判读接口
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(UserDetails.class), username, username, username);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username, users.get(0).getPassword(), getGrantedAuthorities(username));

        /*if (username.equals("admin")){
            return new User("admin", "e10adc3949ba59abbe56e057f20f883e", getGrantedAuthorities(username));
        }
        return null;*/
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    public WlyyUserSimple loadUserSimpleByUsername(String username) throws UsernameNotFoundException {
        List<WlyyUserSimple> users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username, username);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return users.get(0);
    }

}
