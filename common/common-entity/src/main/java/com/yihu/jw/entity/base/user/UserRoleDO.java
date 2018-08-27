package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 用户角色
 * Created by progr1mmer on 2018/8/27.
 */
@Entity
@Table(name = "base_user_role")
public class UserRoleDO extends IntegerIdentityEntity {

	//用户ID
	private String userId;
	//角色ID
	private String roleId;

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "role_id", length = 50)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}