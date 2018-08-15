package com.yihu.jw.entity.base.user;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_role")
public class RoleDO extends UuidIdentityEntityWithOperator {

	//saas id
	private String saasId;
	//角色名称
	private String name;
	//角色编码
	private String code;
	//备注
	private String remark;

	// Constructors
	/** default constructor */
	public RoleDO() {
	}

	/** minimal constructor */
	public RoleDO(String id, Timestamp createTime, Timestamp updateTime) {
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