package com.yihu.jw.entity.base.user;

import com.yihu.jw.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseEmployRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_employ_role")
public class EmployRoleDO extends UuidIdentityEntity implements java.io.Serializable {

	// Fields
	private String employId;
	private String roleId;

	// Constructors

	/** default constructor */
	public EmployRoleDO() {
	}

	/** minimal constructor */
	public EmployRoleDO(String id) {
		this.id = id;
	}

	/** full constructor */
	public EmployRoleDO(String id, String roleId, String employId) {
		this.id = id;
		this.roleId = roleId;
		this.employId = employId;
	}

	@Column(name = "role_id", length = 50)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "employ_id", length = 50)
	public String getEmployId() {
		return this.employId;
	}

	public void setEmployId(String employId) {
		this.employId = employId;
	}

}