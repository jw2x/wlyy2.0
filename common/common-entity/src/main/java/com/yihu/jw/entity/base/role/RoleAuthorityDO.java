package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 角色权限
 * @author progr1mmer.
 * @date Created on 2018/9/14.
 */
@Entity
@Table(name = "base_role_authority")
public class RoleAuthorityDO extends IntegerIdentityEntity {

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 权限
     */
    private String authorities;

    @Column(name = "role_id", length = 50)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
