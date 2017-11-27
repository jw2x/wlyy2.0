package com.yihu.jw.business.base.model.base;// default package

import javax.persistence.*;

/**
 * WlyyModuleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module_function")
public class ModuleFunction implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String functionId;//关联 base_function    code
	private String moduleId;//关联 base_module  code

	// Constructors

	/** default constructor */
	public ModuleFunction() {
	}

	@Column(name = "function_id", length = 100)
	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Column(name = "module_id", length = 100)
	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}