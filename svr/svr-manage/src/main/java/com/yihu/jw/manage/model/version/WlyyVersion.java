package com.yihu.jw.manage.model.version;// default package


import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WlyyVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wlyy_version")
public class WlyyVersion extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId; //saas code
	private String name;
	private Double versionInt;
	private String versionStr;
	private String url;//app下载的路径
	private String info;//app更新的信息
	private Double size;//大小 MB
	private Integer status;////-1 删除 0 禁用 可用

	// Constructors

	/** default constructor */
	public WlyyVersion() {
	}

	/** minimal constructor */
	public WlyyVersion(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/** full constructor */
	public WlyyVersion(String saasId, String code, String name,
					   Double versionInt, String versionStr, String url, String info,
					   Double size) {
		this.saasId = saasId;
		this.code = code;
		this.name = name;
		this.versionInt = versionInt;
		this.versionStr = versionStr;
		this.url = url;
		this.info = info;
		this.size = size;
	}


	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "version_int", precision = 22, scale = 0)
	public Double getVersionInt() {
		return this.versionInt;
	}

	public void setVersionInt(Double versionInt) {
		this.versionInt = versionInt;
	}

	@Column(name = "version_str", length = 10)
	public String getVersionStr() {
		return this.versionStr;
	}

	public void setVersionStr(String versionStr) {
		this.versionStr = versionStr;
	}

	@Column(name = "url", length = 300)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "info", length = 1000)
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "size", precision = 22, scale = 0)
	public Double getSize() {
		return this.size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}