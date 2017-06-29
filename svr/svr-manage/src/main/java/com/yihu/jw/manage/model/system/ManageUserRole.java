package com.yihu.jw.manage.model.system;// default package

import javax.persistence.*;
import java.io.Serializable;

/**
 * ManageUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_user_role")
@Access(value = AccessType.PROPERTY)
public class ManageUserRole  implements Serializable {

	// Fields

	private Long id;

	private String roleCode;
	private String userCode;
	private String userName;

	// Constructors

	/** default constructor */
	public ManageUserRole() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}