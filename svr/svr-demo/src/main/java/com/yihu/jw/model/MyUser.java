package com.yihu.jw.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by chenweida on 2017/11/29.
 */
public class MyUser implements UserDetails {
    /**
     * 权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 密码
     * @return
     */
    @Override
    public String getPassword() {
        return "123456";
    }

    /**
     * 账号
     * @return
     */
    @Override
    public String getUsername() {
        return "admin";
    }

    /**
     * 账号是否过期  false 过期 true 未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否冻结 false 冻结 true 未冻结
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期 false 过期 true 未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用 false 可用 true 不可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
