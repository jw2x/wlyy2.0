package com.yihu.jw.wx.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * WxTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template")
public class WxTemplate implements java.io.Serializable {

	// Fields

	private Integer id;//模板id
	private String code;//模板code
	private String name;//模板名称
	private String wechatCode;//关联的微信code 关联表 Wx_Wechat
	private String templateCode;//微信的模板code
	private String value;//模板值
	private String createUser;//创建人
	private String createUserName;//创建人名
	private Date createTime;//创建时间
	private String updateUser;//修改人
	private String updateUserName;//修改人名称
	private Date updateTime;//修改时间
	private String remark;

	// Constructors

	/** default constructor */
	public WxTemplate() {
	}


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 64)
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

	@Column(name = "wechat_code", length = 64)
	public String getWechatCode() {
		return this.wechatCode;
	}

	public void setWechatCode(String wechatCode) {
		this.wechatCode = wechatCode;
	}

	@Column(name = "template_code", length = 100)
	public String getTemplateCode() {
		return this.templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Column(name = "value", length = 2000)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "create_user", length = 200)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_user_name", length = 200)
	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user", length = 200)
	public String geUpdateUser() {
		return this.updateUser;
	}

	public void seUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_user_name", length = 200)
	public String geUpdateUserName() {
		return this.updateUserName;
	}

	public void seUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", nullable = false, length = 0)
	public Date geUpdateTime() {
		return this.updateTime;
	}

	public void seUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}