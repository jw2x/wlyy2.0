package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ManageRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_role_menu")
public class ManageRoleMenu  extends IdEntity implements java.io.Serializable {

	// Fields

	private Integer id;
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