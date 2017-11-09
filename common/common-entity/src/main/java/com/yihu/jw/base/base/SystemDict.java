package com.yihu.jw.base.base;// default package

import com.yihu.jw.base.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SystemDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_dict")
public class SystemDict extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId;
	private String dictName;
	private String code;
	private String value;
	private String pyCode;
	private Integer sort;

	// Constructors

	/** default constructor */
	public SystemDict() {
	}

	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "dict_name", nullable = false, length = 50)
	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "value", nullable = false, length = 50)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "py_code", length = 50)
	public String getPyCode() {
		return this.pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}