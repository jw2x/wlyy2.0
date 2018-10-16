package com.yihu.jw.entity.base.system;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 系统字典项
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_system_dict_entry")
public class SystemDictEntryDO extends UuidIdentityEntity {

	//所属字典编码
	private String dictCode;
	//编码
	private String code;
	//租户id
	private String saasId;
	//拼音码
	private String pyCode;
	//值
	private String value;
	//排序
	private Integer sort;
	//备注
	private String remark;

	@Column(name = "dict_code", nullable = false, length = 50)
	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	@Column(name = "saasId")
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

	@Column(name = "value", nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}