package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.IntegerIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_role")
public class RoleDO extends IntegerIdentityEntityWithOperator {

	/**
	 * 角色类型
	 */
	public enum Type {
		//系统 - user对应的角色类型
		system,
		//医生
		doctor,
		//患者
		patient
	}

	//saas id
	private String saasId;
	//角色名称
	private String name;
	//角色编码
	private String code;
	//备注
	private String remark;
	//角色类型
	private Type type;

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

	@Column(name = "code", length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "type")
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}