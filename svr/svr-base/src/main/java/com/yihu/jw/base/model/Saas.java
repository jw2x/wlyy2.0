package com.yihu.jw.base.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * WlyySaas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas")
public class Saas extends IdEntity implements java.io.Serializable {

	// Fields

	private String code;//业务code、
	private String name;//名称
	private Integer status;//状态 -1 已删除 0待审核 1审核通过 2 审核不通过
	private String createUser; //创建人code
	private String createUserName;//创建人名称
	private Date createTime;//创建时间
	private String modifyUser;//修改人
	private String modifyUserName;//修改人名称
	private Date modifyTime;//修改时间
	private String remark;//备注

	// Constructors

	/** default constructor */
	public Saas() {
	}


	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "code", length = 100)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}