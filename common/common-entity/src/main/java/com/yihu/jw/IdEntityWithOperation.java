/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.yihu.jw;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 统一定义id的entity基类.
 * 主键生成策略是UUID
 * 还包含业务表用的 创建人，创建时间 创建人名  修改人，修改时间 修改人名
 * @author calvin
 */
// JPA 基类的标识
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class IdEntityWithOperation extends IdEntity{


	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "create_time", nullable = false, length = 0,updatable = false)
	protected Date createTime;

	@CreatedBy
	@Column(name = "create_user",updatable = false)
	protected String createUser;

	@CreatedBy
	@Column(name = "create_user_name",updatable = false)
	protected String createUserName;

	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	@Column(name = "update_time", nullable = false, length = 0)
	protected Date updateTime;

	@LastModifiedBy
	@Column(name = "update_user")
	protected String updateUser;

	@LastModifiedBy
	@Column(name = "update_user_name")
	protected String updateUserName;


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

}
