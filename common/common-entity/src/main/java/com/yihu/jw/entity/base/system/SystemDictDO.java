package com.yihu.jw.entity.base.system;// default package

import com.yihu.jw.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SystemDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_dict")
public class SystemDictDO extends UuidIdentityEntity implements java.io.Serializable {

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


	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}