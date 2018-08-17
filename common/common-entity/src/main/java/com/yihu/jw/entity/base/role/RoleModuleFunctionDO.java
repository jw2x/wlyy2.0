package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_role_module_function")
public class RoleModuleFunctionDO extends UuidIdentityEntity {

    private String roleId; //角色ID
    private String moduleId; //模块ID
    private String functionId; //功能ID

    @Column(name = "role_id", length = 50)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Column(name = "module_id", length = 50)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "function_id", length = 50)
    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }
}
