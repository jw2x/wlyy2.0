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

	//saas id
	private String saasId;
	//所属应用
	private String clientId;
	//角色名称
	private String name;
	//角色编码
	private String code;
	//备注
	private String remark;
	//是否系统管理员
	private Boolean isSystem;

	@Column(name = "saas_id", nullable = false, length = 50)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "client_id", nullable = false, length = 10)
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 8)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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