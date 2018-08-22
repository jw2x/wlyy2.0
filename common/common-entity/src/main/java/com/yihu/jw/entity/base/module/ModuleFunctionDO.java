package com.yihu.jw.entity.base.module;// default package

import com.yihu.jw.entity.IntegerIdentityEntity;

import javax.persistence.*;

/**
 * WlyyModuleFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module_function")
public class ModuleFunctionDO extends IntegerIdentityEntity {

	//关联 base_module   id
	private Integer moduleId;
	//关联 base_function   ic
	private Integer functionId;

	@Column(name = "module_id")
	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "function_id")
	public Integer getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

}