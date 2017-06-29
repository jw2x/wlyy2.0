package com.yihu.jw.manage.model.system;// default package

import javax.persistence.*;

/**
 * ManageRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_role_menu")
public class ManageRoleMenu  implements java.io.Serializable {

	// Fields

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String roleCode;
	private String menuCode;

	// Constructors

	/** default constructor */
	public ManageRoleMenu() {
	}

	/** full constructor */
	public ManageRoleMenu(String roleCode, String menuCode) {
		this.roleCode = roleCode;
		this.menuCode = menuCode;
	}

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

	@Column(name = "menu_code", length = 100)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}