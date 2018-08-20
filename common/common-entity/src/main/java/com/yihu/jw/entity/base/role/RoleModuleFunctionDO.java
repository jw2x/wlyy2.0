package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntity;
import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_role_module_function")
public class RoleModuleFunctionDO extends IntegerIdentityEntity {

    private Integer roleId; //角色ID
    private Integer moduleId; //模块ID
    private Integer functionId; //功能ID

    @Column(name = "role_id", nullable = false)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "module_id", nullable = false)
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Column(name = "function_id", nullable = false)
    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}
