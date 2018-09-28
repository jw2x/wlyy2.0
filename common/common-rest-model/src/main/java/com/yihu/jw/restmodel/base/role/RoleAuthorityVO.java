package com.yihu.jw.restmodel.base.role;

import com.yihu.jw.restmodel.IntegerIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 角色权限
 * @author progr1mmer.
 * @date Created on 2018/9/14.
 */
@ApiModel(value = "RoleVO", description = "角色")
public class RoleAuthorityVO extends IntegerIdentityVO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", example = "402803ee656498890165649ad2da1112")
    private String roleId;
    /**
     * 权限
     */
    @ApiModelProperty(value = "权限", example = "/path/**")
    private String authorities;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
