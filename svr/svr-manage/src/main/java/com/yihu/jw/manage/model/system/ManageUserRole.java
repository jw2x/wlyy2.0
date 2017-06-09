package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ManageUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_user_role")
public class ManageUserRole  extends IdEntity implements java.io.Serializable {

	// Fields

	private String roleCode;
	private String userCode;

	// Constructors

	/** default constructor */
	public ManageUserRole() {
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