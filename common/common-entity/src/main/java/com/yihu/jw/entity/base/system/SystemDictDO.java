package com.yihu.jw.entity.base.system;// default package

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity - 系统字典
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_system_dict")
public class SystemDictDO implements Serializable {

	public enum Type {
		//基础字典
		basic,
		//扩展字典
		extend
	}

	//编码（唯一）
	private String code;
	//saas id 用于租户的自定义字典
	private String saasId;
	//拼音码
	private String pyCode;
	//名称
	private String name;
	//类型
	private Type type;

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "assigned")
	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "saas_id")
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
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