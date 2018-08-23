package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity - 模块
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_module")
public class ModuleDO extends UuidIdentityEntityWithOperator {

	/**
	 * 0-禁用，1-可用，2-不可用
	 */
    public enum Status {
		disable,
		available,
		delete
    }

	//模块名称
	private String name;
	//父id
	private String parentId;
	//状态
	private Status status;
	//备注
	private String remark;
	//closed-表示有子节点，open-表示没有子节点
	private String state ;
	//子集
	private List<ModuleDO> children = new ArrayList<>();

	// Constructors

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Column(name = "parent_id", length = 50)
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