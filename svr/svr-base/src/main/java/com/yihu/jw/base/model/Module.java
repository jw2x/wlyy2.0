package com.yihu.jw.base.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * WlyyModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module")
public class Module extends IdEntity implements java.io.Serializable {

	// Fields
	private String code; //业务code
	private String name; //模块名称
	private String saasId; //关联 Saas code
	private String parentCode;//父id
	private Integer status; //-1 删除 0 禁用 可用
	private String createUser;
	private String createUserName;
	private Date createTime;
	private String modifyUser;
	private String modifyUserName;
	private Date modifyTime;
	private String remark;

	// Constructors

	/** default constructor */
	public Module() {
	}

	/** minimal constructor */
	public Module(Long id, Timestamp createTime, Timestamp modifyTime) {
		this.id = id;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Module(Long id, String code, String name, String saasId,
			String parentCode, Integer status, String createUser,
			String createUserName, Timestamp createTime, String modifyUser,
			String modifyUserName, Timestamp modifyTime, String remark) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.saasId = saasId;
		this.parentCode = parentCode;
		this.status = status;
		this.createUser = createUser;
		this.createUserName = createUserName;
		this.createTime = createTime;
		this.modifyUser = modifyUser;
		this.modifyUserName = modifyUserName;
		this.modifyTime = modifyTime;
		this.remark = remark;
	}

	@Column(name = "code", length = 100)
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

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "parent_code", length = 100)
	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	@Column(name = "status", precision = 2, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Column(name = "modify_user", length = 200)
	public String getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Column(name = "modify_user_name", length = 200)
	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", nullable = false, length = 0)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}