package com.yihu.jw.base.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;

/**
 * WlyySaasModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas_module")
public class SaasModule extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId; //关联WlyySaas code
	private String moduleId; //关联 WlyyModule code

	// Constructors

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "module_id", length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}