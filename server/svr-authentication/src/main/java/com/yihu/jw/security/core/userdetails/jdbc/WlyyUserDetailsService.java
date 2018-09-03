package com.yihu.jw.security.core.userdetails.jdbc;

import com.yihu.jw.security.core.userdetails.SaltUser;
import com.yihu.jw.security.model.WlyyUserDetails;
import com.yihu.jw.security.model.WlyyUserSimple;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Service - 用户加载
 * Created by progr1mmer on 2018/8/29.
 */
public class WlyyUserDetailsService extends JdbcDaoSupport implements UserDetailsService {

    private static final String DEFAULT_USER_DETAILS_STATEMENT = "SELECT * FROM base_user u WHERE u.username = ? OR u.mobile = ? OR u.idcard = ?";

    public WlyyUserDetailsService(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * 用户登录判读接口
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<WlyyUserDetails> users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserDetails.class), username, username, username);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return new SaltUser(username, users.get(0).getPassword(), users.get(0).getSalt(), getGrantedAuthorities(username));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    public WlyyUserSimple authSuccess (String username) throws UsernameNotFoundException {
        //更新登陆时间
        this.getJdbcTemplate().update("update base_user u set u.login_date = ? where u.username = ? or u.mobile = ? or u.idcard = ?", new Date(), username, username, username);
        //加载用户简略信息
        List<WlyyUserSimple> users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username, username);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return users.get(0);
    }

}
