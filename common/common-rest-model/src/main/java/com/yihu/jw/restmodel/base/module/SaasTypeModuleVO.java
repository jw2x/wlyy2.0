package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity - 租户类型模块
 * Created by yeshijie on 2018/10/11.
 */
@ApiModel(value = "SaasTypeModuleVO", description = "租户类型模块")
public class SaasTypeModuleVO extends UuidIdentityVOWithOperator {

	@ApiModelProperty(value = "租户类型id", example = "saasTypeId")
	private String saasTypeId;
	@ApiModelProperty(value = "模块名称", example = "模块1")
	private String name;
	@ApiModelProperty(value = "模块链接", example = "模块链接")
	private String url;
	@ApiModelProperty(value = "模块id", example = "模块id")
	private String moduleId;
	@ApiModelProperty(value = "父模块id", example = "父模块id")
	private String parentModuleId;
	@ApiModelProperty(value = "状态", example = "1有效，0失效")
	private Integer status;
	@ApiModelProperty(value = "类型", example = "0通用，1医生端你，2居民端")
	private Integer type;
	@ApiModelProperty(value = "备注", example = "备注")
	private String remark;
	@ApiModelProperty(value = "0-表示有子节点，1-表示没有子节点", example = "1")
	private Integer isEnd ;
	@ApiModelProperty(value = "必选 0-表示非必选，1-表示必选", example = "1")
	private Integer isMust ;
	@ApiModelProperty(value = "逻辑删除标志1正常，0删除", example = "1")
	private Integer del ;
	@ApiModelProperty(value = "是否选中（0-表示未选，1-表示已选)", example = "1")
	private Integer isCheck =1;
	@ApiModelProperty(value = "子集", example = "子集")
	private List<SaasTypeModuleVO> children = new ArrayList<>();

	public String getSaasTypeId() {
		return saasTypeId;
	}

	public void setSaasTypeId(String saasTypeId) {
		this.saasTypeId = saasTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public List<SaasTypeModuleVO> getChildren() {
		return children;
	}

	public void setChildren(List<SaasTypeModuleVO> children) {
		this.children = children;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}