package com.yihu.jw.entity.base.system;// default package

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SystemDictList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_dict_entry")
public class SystemDictEntryDO extends UuidIdentityEntity implements java.io.Serializable {

	//所属字典编码
	private String dictCode;
	//编码
	private String code;
	//拼音码
	private String pyCode;
	//值
	private String value;
	//排序
	private Integer sort;
	//备注
	private String remark;

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}