package com.yihu.jw.base.model.base;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.*;

/**
 * WlyyModuleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module_function")
public class ModuleFunction extends IdEntity implements java.io.Serializable {

	// Fields

	private String functionId;//关联 WlyyFunction    code
	private String moduleId;//关联 WlyyModule code

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

}