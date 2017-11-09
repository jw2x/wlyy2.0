package com.yihu.jw.base.base;// default package


import com.yihu.jw.base.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WlyySaas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas")
public class Saas extends IdEntity implements java.io.Serializable {

	// Fields

	private String name;//名称
	private Integer status;//状态 -1 已删除 0待审核 1审核通过 2 审核不通过
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

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}