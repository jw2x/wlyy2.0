package com.yihu.jw.base.user;

import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_role")
public class BaseRoleDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields

	private String saasId;
	private String name;
	private Integer status;
	private String remark;
	private String code;

	// Constructors

	/** default constructor */
	public BaseRoleDO() {
	}

	/** minimal constructor */
	public BaseRoleDO(String id, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	// Property accessors

	@Column(name = "saas_id", length = 50)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "name", length = 50)
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

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}