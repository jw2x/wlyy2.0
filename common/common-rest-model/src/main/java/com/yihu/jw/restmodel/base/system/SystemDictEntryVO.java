package com.yihu.jw.restmodel.base.system;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * VO - 系统字典项
 * Created by progr1mmer on 2018/8/27.
 */
@ApiModel(value = "SystemDictEntryVO", description = "系统字典项")
public class SystemDictEntryVO extends UuidIdentityVO {

	//所属字典编码
	@ApiModelProperty(value = "所属字典编码", example = "SYSTEM_SETTING")
	private String dictCode;
	//编码
	@ApiModelProperty(value = "编码", example = "CURRENT_CITY")
	private String code;
	//拼音码
	@ApiModelProperty(value = "拼音码", example = "DQCS")
	private String pyCode;
	//值
	@ApiModelProperty(value = "值", example = "厦门")
	private String value;
	//排序
	@ApiModelProperty(value = "排序", example = "1")
	private Integer sort;
	//备注
	@ApiModelProperty(value = "备注", example = "我是备注")
	private String remark;
	@ApiModelProperty(value = "所属租户id", example = "1")
	private String saasId;

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

	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
}