package com.yihu.jw.entity.base.sms;


import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 短信网关
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_sms_gateway")
public class SmsGatewayDO extends UuidIdentityEntity {

	/**
	 * 0-禁用，1-可用，2-不可用
	 */
	public enum Status {
		disable,
		available,
		delete
	}

	//关联 base_saas id
	private String saasId;
	//名称
	private String name;
	//短信接口的账号
	private String username;
	//短信接口的密码
	private String password;
	//官网地址
	private String website;
	//短信接口调用的地址
	private String url;
	//调用凭证（json串）
	private String certificate;
	//状态
	private Status status;


	@Column(name = "saas_id", nullable = false)
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "certificate", nullable = false)
	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	@Column(name = "status", nullable = false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}