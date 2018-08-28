package com.yihu.jw.restmodel.base.role;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 角色
 * Created by progr1mmer on 2018/8/14.
 */
@ApiModel(value = "RoleVO", description = "角色")
public class RoleVO extends UuidIdentityVOWithOperator {

	@ApiModelProperty(value = "角色名称", example = "超级管理员")
	private String name;
	@ApiModelProperty(value = "备注", example = "我是备注")
	private String remark;
	@ApiModelProperty(value = "是否系统管理员", example = "1")
	private Boolean isSystem;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getSystem() {
		return isSystem;
	}

	public void setSystem(Boolean system) {
		isSystem = system;
	}
}