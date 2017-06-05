package com.yihu.jw.quota.model.jpa.rule;// default package

import java.util.Date;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * TjCleanRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tj_clean_rule")
public class TjCleanRule implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;
	private String type;//1非空
	private Date createTime;
	private String createUser;
	private String createUserName;
	private Date updateTime;
	private String updateUser;
	private String updateUserName;
	private String status;
	private String remark;

	// Constructors

	/** default constructor */
	public TjCleanRule() {
	}

	/** minimal constructor */
	public TjCleanRule(String code, Date createTime, Date updateTime) {
		this.code = code;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "update_user", length = 100)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_user_name", length = 50)
	public String getUpdateUserName() {
		return this.updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "remark", length = 1500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}