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
	private Boolean system;
	@ApiModelProperty(value = "角色代码", example = "1")
	private String code;
	@ApiModelProperty(value = "1.系统管理员，2.saas管理员，3.机构管理员", example = "1")
	private String type;

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
		return system;
	}

	public void setSystem(Boolean system) {
		this.system = system;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}