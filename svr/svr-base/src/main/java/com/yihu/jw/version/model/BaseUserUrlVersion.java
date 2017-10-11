package com.yihu.jw.version.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseUserUrlVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_user_url_version")
public class BaseUserUrlVersion extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId;//saasid 关联 base_saas
	private String userCode;//关联用户
	private String bsvCode;//关联base_url_server_version code
	private Integer status;//1: 正常 0：不可用  -1删除

	// Constructors

	/** default constructor */
	public BaseUserUrlVersion() {
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

	@Column(name = "bsv_code", length = 100)
	public String getBsvCode() {
		return this.bsvCode;
	}

	public void setBsvCode(String bsvCode) {
		this.bsvCode = bsvCode;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}