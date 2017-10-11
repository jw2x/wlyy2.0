package com.yihu.jw.manage.model.version;// default package

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * BaseServerVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_server_version")
public class BaseServerVersion  {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;  // 非业务主键

	@Column(name="code")
	protected String code;

	@CreatedDate
	@Column(name = "create_time", nullable = false, length = 0,updatable = false)
	protected Date createTime;

	@CreatedBy
	@Column(name = "create_user",updatable = false)
	protected String createUser;

	@CreatedBy
	@Column(name = "create_user_name",updatable = false)
	protected String createUserName;

	@LastModifiedDate
	@Column(name = "update_time", nullable = false, length = 0)
	protected Date updateTime;

	@LastModifiedBy
	@Column(name = "update_user", length = 100)
	protected String updateUser;

	@LastModifiedBy
	@Column(name = "update_user_name", length = 50)
	protected String updateUserName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

 
	private String saasId;//关联base_saas code
	private String name; //版本名称
	private Integer versionInt;//版本号
	private Integer status;////-1 删除 0 禁用 可用
	private String remark;

	// Constructors

	/** default constructor */
	public BaseServerVersion() {
	}

	/** minimal constructor */
	public BaseServerVersion(String code, String name, Date createTime,
			Date updateTime) {
		this.code = code;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public BaseServerVersion(String code, String saasId, String userCode,
			String name, Integer versionInt, Date createTime,
			String createUser, String createUserName, Date updateTime,
			String updateUser, String updateUserName, Integer status,
			String remark) {
		this.code = code;
		this.saasId = saasId;
		this.name = name;
		this.versionInt = versionInt;
		this.createTime = createTime;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
		this.updateUserName = updateUserName;
		this.status = status;
		this.remark = remark;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
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

	@Column(name = "status", length = 1)
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