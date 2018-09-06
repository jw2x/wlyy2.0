package com.yihu.jw.security.core.userdetails.jdbc;

import com.yihu.jw.security.core.userdetails.SaltUser;
import com.yihu.jw.security.model.WlyyUserDetails;
import com.yihu.jw.security.model.WlyyUserSimple;
import org.apache.commons.lang3.StringUtils;
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
 * Service - 用户加载
 * Created by progr1mmer on 2018/8/29.
 */
public class WlyyUserDetailsService extends JdbcDaoSupport implements UserDetailsService {

    private static final String DEFAULT_USER_DETAILS_STATEMENT = "SELECT * FROM base_user u WHERE u.username = ? OR u.mobile = ? OR u.idcard = ?";

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

        return new SaltUser(username, users.get(0).getPassword(), users.get(0).getSalt(), getGrantedAuthorities(username));
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

        List<WlyyUserSimple> users = getWlyyUserSimple(username);
        //加载用户简略信息
        if (users == null || users.size() == 0) {
            throw new UsernameNotFoundException(username);
        }
        return users.get(0);
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
            this.getJdbcTemplate().update("update base_user u set u.login_date = ? where u.username = ? or u.mobile = ? or u.idcard = ?", new Date(), username, username, username);
            users = this.getJdbcTemplate().query(DEFAULT_USER_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username, username);
            //2.为医生登录账号
        }else if("2".equals(loginType)){
            //更新登录时间
            this.getJdbcTemplate().update("update base_doctor d set d.login_date = ? where d.mobile = ? or d.idcard = ?", new Date(), username, username);
            users = this.getJdbcTemplate().query(DEFAULT_DOCTOR_DETAILS_STATEMENT, new BeanPropertyRowMapper(WlyyUserSimple.class), username, username);
            //3.患者登录
        }else if("3".equals(loginType)){
            //更新登录时间
            this.getJdbcTemplate().update("update base_patient p set p.login_date = ? where p.mobile = ? or p.idcard = ?", new Date(), username, username);
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
