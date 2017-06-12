package com.yihu.jw.manage.model.login;// default package

import com.yihu.jw.manage.model.IdEntity;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * ManageLoginLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_login_log")
public class ManageLoginLog implements java.io.Serializable {
	public static Integer type_error=0;
	public static Integer type_success=1;
	// Fields
	private String id;
	private Date loginTime;
	private String loginAccount;
	private String loginUser;
	private String loginUserName;
	private Integer type;
	private String errorMessage;

	// Constructors

	/** default constructor */
	public ManageLoginLog() {
	}

	/** minimal constructor */
	public ManageLoginLog(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	/** full constructor */
	public ManageLoginLog(Timestamp loginTime, String loginUser,
			String loginUserName, Integer type, String errorMessage) {
		this.loginTime = loginTime;
		this.loginUser = loginUser;
		this.loginUserName = loginUserName;
		this.type = type;
		this.errorMessage = errorMessage;
	}
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "login_time", nullable = false, length = 0)
	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name = "login_user", length = 100)
	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	@Column(name = "login_user_name", length = 100)
	public String getLoginUserName() {
		return this.loginUserName;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "error_message", length = 1000)
	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
}