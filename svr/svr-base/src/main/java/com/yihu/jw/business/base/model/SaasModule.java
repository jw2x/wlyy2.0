package com.yihu.jw.business.base.model;// default package


import javax.persistence.*;

/**
 * WlyySaasModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas_module")
public class SaasModule extends IdEntity implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}