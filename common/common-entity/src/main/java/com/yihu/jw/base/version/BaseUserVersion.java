package com.yihu.jw.base.version;// default package

import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseUserVersion entity. @author MyEclipse Persistence Tools
 * 灰度发布表 用户在这个表有数据 说明是属于灰度发布的用户 没有数据就说明没有
 */
@Entity
@Table(name = "base_user_version")
public class BaseUserVersion  extends IdEntity implements java.io.Serializable {

	// Fields

	private String userId;
	private String version;

	// Constructors

	/** default constructor */
	public BaseUserVersion() {
	}


	@Column(name = "user_id", length = 100)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Column(name = "version", length = 100)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@CreatedDate
	@Column(name = "create_time", nullable = false, length = 0,updatable = false)
	protected Date createTime;

	@CreatedBy
	@Column(name = "create_user",updatable = false)
	protected String createUser;

	@CreatedBy
	@Column(name = "create_user_name",updatable = false)
	protected String createUserName;

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
}