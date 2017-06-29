package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * ManageUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_user")
public class ManageUser  extends IdEntity implements Serializable {

	// Fields
	private String name;
	private String password;//密码  密码是 密码+salt MD5加密
	private String salt;//盐值
	private String loginAccount;//登陆的账号
	private String mobile;
	private Integer status;
	private String remark;

	@Transient
	private String roleName;

	// Constructors

	/** default constructor */
	public ManageUser() {
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "login_account", length = 100)
	public String getLoginAccount() {
		return this.loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "status")
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "ManageUser{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", loginAccount='" + loginAccount + '\'' +
				", mobile='" + mobile + '\'' +
				", status=" + status +
				", createTime=" + createTime +
				", createUser='" + createUser + '\'' +
				", createUserName='" + createUserName + '\'' +
				", updateTime=" + updateTime +
				", updateUser='" + updateUser + '\'' +
				", updateUserName='" + updateUserName + '\'' +
				", remark='" + remark + '\'' +
				'}';
	}
}