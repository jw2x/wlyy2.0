package com.yihu.jw.entity.base.saas;// default package


import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.*;

/**
 * WlyySaasModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas_module")
public class SaasModuleDO extends UuidIdentityEntity {

	private String saasId; //关联WlyySaas code
	private String moduleId; //关联 WlyyModule code

	// Constructors

	@Column(name = "saas_id", length = 50)
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "module_id", length = 50)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}