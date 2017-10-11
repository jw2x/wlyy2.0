package com.yihu.jw.login.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 账号实体 包含医生 患者的账号
 */
@Entity
@Table(name = "base_login_account")
public class BaseLoginAccount extends IdEntity implements java.io.Serializable {

	private String userType;//'用户类型 1居民 2医生 或者行政管理员'
	private String password;//'密码'
	private String salt;//'盐值'
	private String accountStatus;//'状态 ( -2  锁定 -1 删除，0可用 )'
	private String saasId;//'saas配置id'
	private String email;//'邮箱'

	// Constructors

	/** default constructor */
	public BaseLoginAccount() {
	}


	/** full constructor */
	public BaseLoginAccount(Integer id, String code, String userType,
							String password, String salt, String accountStatus, Date createTime,
							Date updateTime, String saasId, String email) {
		this.code = code;
		this.userType = userType;
		this.password = password;
		this.salt = salt;
		this.accountStatus = accountStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.saasId = saasId;
		this.email = email;
	}


	@Column(name = "user_type", precision = 2, scale = 0)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", length = 100)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "account_status", precision = 2, scale = 0)
	public String getAccountStatus() {
		return this.accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}