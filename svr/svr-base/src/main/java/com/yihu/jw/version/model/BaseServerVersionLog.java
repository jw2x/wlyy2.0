package com.yihu.jw.version.model;// default package

import com.yihu.jw.base.model.IdEntity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseServerVersionLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_server_version_log")
public class BaseServerVersionLog extends IdEntity implements java.io.Serializable {

	private String saasId; //关联saas code
	private String userCodes;//此次更新的用户 多个逗号分割
	private String name;//此次更新的版本名称
	private Integer versionInt;//版本号
	private Integer type;//1更新 2回滚
	private Date createTime;
	private String createUser;
	private String createUserName;
	private Date updateTime;
	private String updateUser;
	private String updateUserName;
	private Integer status;////-1 删除 0 禁用 可用

	// Constructors

	/** default constructor */
	public BaseServerVersionLog() {
	}

	/** minimal constructor */
	public BaseServerVersionLog(String name, Date createTime) {
		this.name = name;
		this.createTime = createTime;
	}

	/** full constructor */
	public BaseServerVersionLog(String saasId, String userCodes, String name,
			Integer versionInt, Date createTime, String createUser,
			String createUserName) {
		this.saasId = saasId;
		this.userCodes = userCodes;
		this.name = name;
		this.versionInt = versionInt;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserName = createUserName;
	}


	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "user_codes")
	public String getUserCodes() {
		return this.userCodes;
	}

	public void setUserCodes(String userCodes) {
		this.userCodes = userCodes;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "version_int")
	public Integer getVersionInt() {
		return this.versionInt;
	}

	public void setVersionInt(Integer versionInt) {
		this.versionInt = versionInt;
	}

	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_user", length = 100)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_user_name", length = 50)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}