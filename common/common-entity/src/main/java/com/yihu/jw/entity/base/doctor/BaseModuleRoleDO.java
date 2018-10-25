package com.yihu.jw.entity.base.doctor;

import com.yihu.jw.entity.IntegerIdentityEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务模块与业务模块角色关联信息实体
 * 
 * @author litaohong on  2018年10月25日
 *
 */
@Entity
@Table(name = "base_module_role")
public class BaseModuleRoleDO extends IntegerIdentityEntity {

    /**
	 * 角色标识
	 */
	private String roleCode;

    /**
	 * 业务模块id
	 */
	private String moduleId;


	@Column(name = "role_code")
    public String getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

	@Column(name = "module_id")
    public String getModuleId() {
        return moduleId;
    }
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }



}