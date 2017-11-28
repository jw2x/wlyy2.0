package com.yihu.jw.base.user;

import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_menu")
public class BaseMenuDO extends IdEntityWithOperation implements java.io.Serializable {

	// Fields
	private String url;//请求路径
	private String method;//请求方式 get post
	private String saasId;
	private String parentId;
	private String name;
	private Integer sort;
	private Integer status;
	private String remark;

	// Constructors

	/** default constructor */
	public BaseMenuDO() {
	}

	/** minimal constructor */
	public BaseMenuDO(String id, Timestamp createTime, Timestamp updateTime) {
		this.id = id;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "saas_id", length = 50)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "parent_id", length = 50)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "status", precision = 2, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "url", length = 255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "method", length = 255)
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}