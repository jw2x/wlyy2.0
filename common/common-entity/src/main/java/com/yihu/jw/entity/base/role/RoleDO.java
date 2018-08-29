package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 角色
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_role")
public class RoleDO extends UuidIdentityEntityWithOperator {

	//角色名称
	private String name;
	//备注
	private String remark;
	//是否系统管理员
	private Boolean isSystem;

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "is_system")
	public Boolean getSystem() {
		return isSystem;
	}

	public void setSystem(Boolean system) {
		isSystem = system;
	}
}