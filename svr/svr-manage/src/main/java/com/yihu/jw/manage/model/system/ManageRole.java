package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * ManageRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_role")
public class ManageRole extends IdEntity implements java.io.Serializable {

	// Fields
	private String name;
	private Integer status;
	private String remark;

	// Constructors

	/** default constructor */
	public ManageRole() {
	}


	/** full constructor */
	public ManageRole(String code, String name, Integer status,
			Date createTime, String createUser, String createUserName,
			Date updateTime, String updateUser, String updateUserName,
			String remark) {
		this.code = code;
		this.name = name;
		this.status = status;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.updateUserName = updateUserName;
		this.remark = remark;
	}


	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}