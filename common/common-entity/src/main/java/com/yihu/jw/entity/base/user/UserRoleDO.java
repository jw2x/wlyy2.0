package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseEmployRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_user_role")
public class UserRoleDO extends IntegerIdentityEntity {

	// Fields
	private String userId;
	private String roleCode;

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "role_code", length = 16)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}