package com.yihu.jw.security.core.userdetails.jdbc;

import com.yihu.jw.security.core.userdetails.SaltUser;
import com.yihu.jw.security.model.WlyyUserDetails;
import com.yihu.jw.security.model.WlyyUserSimple;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Service - 用户信息
 * Created by progr1mmer on 2018/8/29.
 */
public class WlyyUserDetailsService extends JdbcDaoSupport implements UserDetailsService {

    private static final String DEFAULT_USER_DETAILS_STATEMENT = "select * from base_user u where u.username = ? or u.mobile = ? or u.idcard = ?";

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
        if (users.get(0).isLocked()) {
            Date date = users.get(0).getLockedDate();
            if (new Date().after(DateUtils.addMinutes(date, 5))) {
                this.getJdbcTemplate().update("update base_user u set login_failure_count = 0, u.locked = 0 where u.username = ? or u.mobile = ? or u.idcard = ?", username, username, username);
                users.get(0).setLocked(false);
            }
        }
        return new SaltUser(username, users.get(0).getPassword(), users.get(0).getSalt(), users.get(0).isEnabled(), users.get(0).isLocked(), getGrantedAuthorities(username));
    }

    public WlyyUserSimple authSuccess (String username) throws UsernameNotFoundException {
        //更新登陆时间
        this.getJdbcTemplate().update("update base_user u set login_failure_count = 0, u.login_date = ? where u.username = ? or u.mobile = ? or u.idcard = ?", new Date(), username, username, username);
        //加载用户简略信息
        List<WlyyUserSimple> users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username, username);
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return users.get(0);
    }

    public String authFailure () throws UsernameNotFoundException {
        //获取失败次数
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = request.getParameter("username");
        Integer loginFailureCount = this.getJdbcTemplate().queryForObject("select login_failure_count from base_user u where u.username = ? or u.mobile = ? or u.idcard = ?", Integer.class, username, username, username);
        loginFailureCount ++;
        if (loginFailureCount == 5) {
            this.getJdbcTemplate().update("update base_user u set u.login_failure_count = 5, locked = 1, u.locked_date = ? where u.username = ? or u.mobile = ? or u.idcard = ?", new Date(), username, username, username);
            return "账号已被锁定,请5分钟后重试!";
        } else {
            this.getJdbcTemplate().update("update base_user u set u.login_failure_count = ? where u.username = ? or u.mobile = ? or u.idcard = ?", loginFailureCount, username, username, username);
            return "密码错误,还可以再试" + (5 - loginFailureCount) + "次!";
        }
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
