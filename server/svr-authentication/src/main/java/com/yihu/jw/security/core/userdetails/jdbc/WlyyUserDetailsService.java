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

    private static final String DEFAULT_DOCTOR_DETAILS_STATEMENT = "SELECT * FROM base_doctor d WHERE d.mobile = ? OR d.idcard = ?";

    private static final String DEFAULT_PATIENT_DETAILS_STATEMENT = "SELECT * FROM base_patient p WHERE p.mobile = ? OR p.idcard = ?";


    public WlyyUserDetailsService(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    /**
     * 用户登录判读接口
     * 判断loginType查找用户信息
     * 用户类型 1或默认为user，2：医生登录，3：患者登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<WlyyUserDetails> users = getWlyyUserDetails(username);
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

    /**
     * 用户登录判读接口
     * 判断loginType查找用户信息
     * 用户类型 1或默认为user，2：医生登录，3：患者登录
     * @param username
     * @return
     */
    public  List<WlyyUserDetails> getWlyyUserDetails(String username){

        String loginType = getLogintype();

        List<WlyyUserDetails> users = null;
        //1或默认查找user表，为平台管理员账号
        if(StringUtils.isBlank(loginType)||"1".equals(loginType)){
            users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserDetails.class), username, username, username);
            //2.为医生登录账号
        }else if("2".equals(loginType)){
            users = this.getJdbcTemplate().query(DEFAULT_DOCTOR_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserDetails.class), username, username);
            //3.患者登录
        }else if("3".equals(loginType)){
            users = this.getJdbcTemplate().query(DEFAULT_PATIENT_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserDetails.class), username, username);
        }//..
        return users;
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    /**
     * 设置用户登录时间，返回登录信息
     * 判断loginType，用户类型 1或默认为user，2：医生登录，3：患者登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public WlyyUserSimple authSuccess (String username) throws UsernameNotFoundException {
        //加载用户简略信息
        List<WlyyUserSimple> users = getWlyyUserSimple(username);
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

    /**
     * 设置用户登录时间，返回登录信息
     * 判断loginType，用户类型 1或默认为user，2：医生登录，3：患者登录
     * @param username
     * @return
     */
    public List<WlyyUserSimple> getWlyyUserSimple(String username){

        String loginType = getLogintype();

        List<WlyyUserSimple> users = null;

        //1或默认查找user表，为平台管理员账号
        if(StringUtils.isBlank(loginType)||"1".equals(loginType)){
            //更新登录时间
            this.getJdbcTemplate().update("update base_user u set u.login_failure_count = 0, u.login_date = ? where u.username = ? or u.mobile = ? or u.idcard = ?", new Date(), username, username, username);
            users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username, username);
            //2.为医生登录账号
        }else if("2".equals(loginType)){
            //更新登录时间
            this.getJdbcTemplate().update("update base_doctor d set set d.login_failure_count = 0, d.login_date = ? where d.mobile = ? or d.idcard = ?", new Date(), username, username);
            users = this.getJdbcTemplate().query(DEFAULT_DOCTOR_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username);
            //3.患者登录
        }else if("3".equals(loginType)){
            //更新登录时间
            this.getJdbcTemplate().update("update base_patient p set p.login_failure_count = 0, p.login_date = ? where p.mobile = ? or p.idcard = ?", new Date(), username, username);
            users = this.getJdbcTemplate().query(DEFAULT_PATIENT_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username);
        } //...

        return users;
    }

    /**
     * 获取用户登录类型
     * @return
     */
    public String getLogintype(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String loginType = request.getParameter("loginType");

        return loginType;
    }

}
