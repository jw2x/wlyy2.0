package com.yihu.jw.base.user;

import com.yihu.jw.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseEmployRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_employ_role")
public class BaseEmployRoleDO extends IdEntity implements java.io.Serializable {

	// Fields

	private String roleId;
	private String employId;

	// Constructors

	/** default constructor */
	public BaseEmployRoleDO() {
	}

	/** minimal constructor */
	public BaseEmployRoleDO(String id) {
		this.id = id;
	}

	/** full constructor */
	public BaseEmployRoleDO(String id, String roleId, String employId) {
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