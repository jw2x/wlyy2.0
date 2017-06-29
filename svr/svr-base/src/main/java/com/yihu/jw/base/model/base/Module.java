package com.yihu.jw.base.model.base;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WlyyModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module")
public class Module extends IdEntity implements java.io.Serializable {

	// Fields
	private String name; //模块名称
	private String saasId; //关联 Saas code
	private String parentCode;//父id
	private Integer status; //-1 删除 0 禁用 可用
	private String remark;

	// Constructors

	/** default constructor */
	public Module() {
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

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}