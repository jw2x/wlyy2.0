package com.yihu.jw.entity.base.role;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author progr1mmer
 * @date 2018/09/20
 */
@Entity
@Table(name = "base_menu")
public class MenuDO extends UuidIdentityEntityWithOperator {

	private String saasId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 请求路径
	 */
	private String url;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 请求方式
	 */
	private String method;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 父级ID
	 */
	private String parentId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否展示（1是，2否）
     */
	private Integer show;

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

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "method")
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Column(name = "is_show")
	public Integer getShow() {
		return show;
	}

	public void setShow(Integer show) {
		this.show = show;
	}
}