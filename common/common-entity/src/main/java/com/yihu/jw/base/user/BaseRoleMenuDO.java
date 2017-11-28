package com.yihu.jw.base.user;

import com.yihu.jw.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_role_menu")
public class BaseRoleMenuDO extends IdEntity implements java.io.Serializable {

	// Fields
	private String roleId;
	private String menuId;

	// Constructors

	/** default constructor */
	public BaseRoleMenuDO() {
	}

	/** minimal constructor */
	public BaseRoleMenuDO(String id) {
		this.id = id;
	}



	@Column(name = "role_id", length = 50)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "menu_id", length = 50)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}