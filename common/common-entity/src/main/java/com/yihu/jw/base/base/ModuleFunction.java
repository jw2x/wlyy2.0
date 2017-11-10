package com.yihu.jw.base.base;// default package

import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.*;

/**
 * WlyyModuleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module_function")
public class ModuleFunction extends IdEntity implements java.io.Serializable {

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
}