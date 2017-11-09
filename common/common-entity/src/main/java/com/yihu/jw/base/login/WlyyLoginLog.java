package com.yihu.jw.base.login;// default package

import javax.persistence.*;
import java.util.Date;

/**
 * 登陆日志
 */
@Entity
@Table(name = "wlyy_login_log")
public class WlyyLoginLog implements java.io.Serializable {

	// Fields
	private Integer id;//'主键'
	private String loginType;//'1 短信登录  2 密码登录'
	private String userCode;//'登录用户主键 患者code或者医生code'
	private String saasId;//'saas配置id'
	private String userType;//'1 患者 2医生 '
	private Date createTime;
	private Integer tokenId;//'tokenid'
	private String type;//'1 成功 2失败'
	private String errorMessage;//'错误信息'
	private String phone;//'电话号码'

	// Constructors

	/** default constructor */
	public WlyyLoginLog() {
	}

	/** minimal constructor */
	public WlyyLoginLog(Integer id, Date createTime) {
		this.id = id;
		this.createTime = createTime;
	}

	/** full constructor */
	public WlyyLoginLog(Integer id, String loginType, String userCode,
			String saasId, String userType, Date createTime, Integer tokenId,
			String type, String errorMessage, String phone) {
		this.id = id;
		this.loginType = loginType;
		this.userCode = userCode;
		this.saasId = saasId;
		this.userType = userType;
		this.createTime = createTime;
		this.tokenId = tokenId;
		this.type = type;
		this.errorMessage = errorMessage;
		this.phone = phone;
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

	@Column(name = "login_type", length = 2)
	public String getLoginType() {
		return this.loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "user_code", length = 100)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "user_type", length = 2)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "token_id")
	public Integer getTokenId() {
		return this.tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "error_message", length = 1000)
	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}