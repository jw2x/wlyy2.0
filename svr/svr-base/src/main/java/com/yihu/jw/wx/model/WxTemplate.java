package com.yihu.jw.wx.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * WxTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template")
public class WxTemplate extends IdEntity implements java.io.Serializable{

	// Fields

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
	private Integer status; //状态 -1 已删除 0可用

	public WxTemplate(Long id, String code, String name, String wechatCode, String templateCode, String value, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark, Integer status) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.wechatCode = wechatCode;
		this.templateCode = templateCode;
		this.value = value;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateUserName = updateUserName;
		this.updateTime = updateTime;
		this.remark = remark;
		this.status = status;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	// Constructors

	/** default constructor */
	public WxTemplate() {
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