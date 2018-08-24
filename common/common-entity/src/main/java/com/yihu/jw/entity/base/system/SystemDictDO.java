package com.yihu.jw.entity.base.system;// default package

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 系统字典
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_system_dict")
public class SystemDictDO extends UuidIdentityEntity {

	public enum Type {
		//基础字典
		basic,
		//扩展字典
		extend
	}

	// Fields
	//saas id 用于租户的自定义字典
	private String saasId;
	//编码（唯一）
	private String code;
	//拼音码
	private String pyCode;
	//名称
	private String name;
	//类型
	private Type type;

	@Column(name = "saas_id")
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "py_code", nullable = false, length = 50)
	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	@Column(name = "name", nullable =  false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}