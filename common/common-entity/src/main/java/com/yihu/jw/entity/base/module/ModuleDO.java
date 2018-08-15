package com.yihu.jw.entity.base.module;// default package

import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * WlyyModule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_module")
public class ModuleDO extends UuidIdentityEntityWithOperation {

    public enum Status {
        delete,
        disable,
        available;
    }

	private String saasId; //关联 Saas id
	private String name; //模块名称
	private String parentId; //父id
	private Status status; //0删除 1禁用 2可用
	private String remark; //备注
	private String state ;  //closed:表示有子节点   open:表示没有子节点
	private List<ModuleDO> children = new ArrayList<>();

	// Constructors

	/** default constructor */
	public ModuleDO() {
	}

    @Column(name = "saas_id", length = 100)
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "parent_id", length = 100)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

    @Column(name = "status", precision = 2, scale = 0)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

    @Transient
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

    @Transient
	public List<ModuleDO> getChildren() {
		return children;
	}

	public void setChildren(List<ModuleDO> children) {
		this.children = children;
	}
}