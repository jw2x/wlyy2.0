package com.yihu.jw.entity.base.version;// default package

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * WlyyVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wlyy_version")
public class WlyyVersionDO  extends UuidIdentityEntityWithOperator implements java.io.Serializable {

	// Fields

	private String saasId;
	private String code;
	private String name;
	private Double versionInt;
	private String versionStr;
	private String url;
	private String info;
	private Double size;
	private Integer status;

	// Constructors

	/** default constructor */
	public WlyyVersionDO() {
	}



	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "code", nullable = false, length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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


	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}