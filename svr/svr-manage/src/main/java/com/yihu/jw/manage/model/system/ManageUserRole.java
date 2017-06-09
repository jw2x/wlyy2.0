package com.yihu.jw.manage.model.system;// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ManageUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_user_role")
public class ManageUserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roleCode;
	private String userCode;

	// Constructors

	/** default constructor */
	public ManageUserRole() {
	}

	/** minimal constructor */
	public ManageUserRole(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ManageUserRole(Integer id, String roleCode, String userCode) {
		this.id = id;
		this.roleCode = roleCode;
		this.userCode = userCode;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "role_code", length = 100)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "user_code", length = 100)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}