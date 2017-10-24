package com.yihu.jw.business.login.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * 账号实体 包含医生 患者的账号
 */
@Entity
@Table(name = "wlyy_login_account")
public class WlyyLoginAccount implements java.io.Serializable {

	// Fields
	private Integer id;//'主键'
	private String code;//'业务code'
	private String userType;//'用户类型 1居民 2医生 或者行政管理员'
	private String password;//'密码'
	private String salt;//'盐值'
	private String accountStatus;//'状态 ( -2  锁定 -1 删除，0可用 )'
	private Date createTime;//'创建时间'
	private Date updateTime;//'修改时间'
	private String saasId;//'saas配置id'
	private String email;//'邮箱'

	// Constructors

	/** default constructor */
	public WlyyLoginAccount() {
	}

	/** minimal constructor */
	public WlyyLoginAccount(Integer id, Date createTime, Date updateTime) {
		this.id = id;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public WlyyLoginAccount(Integer id, String code, String userType,
			String password, String salt, String accountStatus, Date createTime,
			Date updateTime, String saasId, String email) {
		this.id = id;
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

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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