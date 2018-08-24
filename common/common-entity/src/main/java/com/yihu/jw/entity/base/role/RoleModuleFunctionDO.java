package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 角色模块功能
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_role_module_function")
public class RoleModuleFunctionDO extends IntegerIdentityEntity {

    //角色ID
    private String roleId;
    //模块ID
    private String moduleId;
    //功能ID
    private String functionId;
    //是否启用
    private Boolean isEnabled;

    @Column(name = "role_id", nullable = false)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "module_id", nullable = false)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "function_id", nullable = false)
    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    @Column(name = "is_enabled", nullable = false)
    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
