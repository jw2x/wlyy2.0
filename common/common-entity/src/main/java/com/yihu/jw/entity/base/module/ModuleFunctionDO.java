package com.yihu.jw.entity.base.module;// default package

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.*;

/**
 * WlyyModuleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module_function")
public class ModuleFunctionDO extends UuidIdentityEntity {

	//关联 base_function   ic
	private String functionId;
	//关联 base_module   id
	private String moduleId;

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