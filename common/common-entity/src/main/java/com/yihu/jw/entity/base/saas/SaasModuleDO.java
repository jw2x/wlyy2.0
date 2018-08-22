package com.yihu.jw.entity.base.saas;// default package


import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.*;

/**
 * WlyySaasModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas_module")
public class SaasModuleDO extends IntegerIdentityEntity {

	private String saasId; //关联WlyySaas code
	private Integer moduleId; //关联 WlyyModule code

	// Constructors

	@Column(name = "saas_id", length = 50)
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "module_id", nullable = false)
	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
}