package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * ManageMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_menu")
public class ManageMenu extends IdEntity implements java.io.Serializable {

	// Fields
	private String parentCode;
	private String name;
	private String url;
	private Integer status;
	private Integer sort;
	private String remark;

	// Constructors

	/** default constructor */
	public ManageMenu() {
	}

	/** full constructor */
	public ManageMenu(String code, String parentCode, String name, String url,
			Integer status, Date createTime, String createUser,
			String createUserName, Date updateTime, String updateUser,
			String updateUserName, String remark) {
		this.code = code;
		this.parentCode = parentCode;
		this.name = name;
		this.url = url;
		this.status = status;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.updateUserName = updateUserName;
		this.remark = remark;
	}


	@Column(name = "parent_code", length = 100)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url", length = 1500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
