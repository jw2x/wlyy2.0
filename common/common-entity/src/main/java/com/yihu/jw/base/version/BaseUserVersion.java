package com.yihu.jw.base.version;// default package

import com.yihu.jw.IdEntityWithOperation;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseUserVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_user_version")
public class BaseUserVersion  extends IdEntityWithOperation implements java.io.Serializable {

	// Fields

	private String saasId;
	private String userCode;
	private String version;
	private Integer status;

	// Constructors

	/** default constructor */
	public BaseUserVersion() {
	}


	@Column(name = "saas_id", length = 100)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "user_code", length = 100)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "version", length = 100)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}