package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity - 租户类型模块
 * Created by yeshijie on 2018/10/11.
 */
@Entity
@Table(name = "base_saas_type_module")
public class SaasTypeModuleDO extends UuidIdentityEntityWithOperator {

	//租户类型id
	private String saasTypeId;
	//模块名称
	private String name;
	//模块连接
	private String url;
	//模块id
	private String moduleId;
	//父模块id
	private String parentModuleId;
	//状态
	private Integer status;
	//类型
	private Integer type;
	//备注
	private String remark;
	//0-表示有子节点，1-表示没有子节点
	private Integer isEnd ;
	//0-表示非必选，1-表示必选
	private Integer isMust ;
	//逻辑删除标志1正常，0删除
	private Integer del ;
	//子集
	private List<SaasTypeModuleDO> children = new ArrayList<>();

	// Constructors

	@Column(name = "saas_type_id")
	public String getSaasTypeId() {
		return saasTypeId;
	}

	public void setSaasTypeId(String saasTypeId) {
		this.saasTypeId = saasTypeId;
	}

	@Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "module_id")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "parent_module_id")
	public String getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "is_end")
	public Integer getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Integer isEnd) {
		this.isEnd = isEnd;
	}

	@Column(name = "del")
	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	@Column(name = "is_must")
	public Integer getIsMust() {
		return isMust;
	}

	public void setIsMust(Integer isMust) {
		this.isMust = isMust;
	}

	@Transient
	public List<SaasTypeModuleDO> getChildren() {
		return children;
	}

	public void setChildren(List<SaasTypeModuleDO> children) {
		this.children = children;
	}
}