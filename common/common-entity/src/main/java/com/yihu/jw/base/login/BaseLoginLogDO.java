package com.yihu.jw.base.login;// default package


import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.*;
import java.util.Date;

/**
 * 登陆日志
 */
@Entity
@Table(name = "base_login_log")
public class BaseLoginLogDO extends IdEntity implements java.io.Serializable {

	private String loginType;//'1 短信登录  2 密码登录'
	private String userId;//'登录用户主键 患者code或者医生code'
	private String saasId;//'saas配置id'
	private String userType;//'1 患者 2医生 '
	private Date createTime;
	private Integer tokenId;//'tokenid'
	private String type;//'1 成功 2失败'
	private String errorMessage;//'错误信息'
	private String phone;//'电话号码'

	// Constructors

	/** default constructor */
	public BaseLoginLogDO() {
	}

	/** minimal constructor */
	public BaseLoginLogDO(Integer id, Date createTime) {
		this.createTime = createTime;
	}




	@Column(name = "login_type", length = 2)
	public String getLoginType() {
		return this.loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Column(name = "user_id", length = 100)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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