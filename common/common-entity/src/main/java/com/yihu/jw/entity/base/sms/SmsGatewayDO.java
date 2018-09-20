package com.yihu.jw.entity.base.sms;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity - 短信网关
 * @author progr1mmer
 * @date Created on 2018/8/14.
 */
@Entity
@Table(name = "base_sms_gateway")
public class SmsGatewayDO extends UuidIdentityEntityWithOperator {

	/**
	 * 0-禁用，1-可用，2-不可用
	 */
	public enum Status {
		disable,
		available,
		delete
	}

	//关联 client id
	private String clientId;
	//名称
	private String name;
	//短信接口的账号
	private String username;
	//短信接口的密码
	private String password;
	//官网地址
	private String website;
	//超时时间（分钟）
	private Integer expireMin;
	//短信接口调用的地址
	private String requestUrl;
	//调用凭证（json串）
	private String requestCertificate;
	//短信接口调用状态标识
	private String responseCode;
	//调用成功的状态码
	private String successValue;
	//状态
	private Status status;

	@Column(name = "client_id", nullable = false)
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	@Column(name = "expire_min")
	public Integer getExpireMin() {
		return expireMin;
	}

	public void setExpireMin(Integer expireMin) {
		this.expireMin = expireMin;
	}

	@Column(name = "request_url", nullable = false)
	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	@Column(name = "request_certificate", nullable = false)
	public String getRequestCertificate() {
		return requestCertificate;
	}

	public void setRequestCertificate(String requestCertificate) {
		this.requestCertificate = requestCertificate;
	}

	@Column(name = "response_code", nullable = false)
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@Column(name = "success_value", nullable = false)
	public String getSuccessValue() {
		return successValue;
	}

	public void setSuccessValue(String successValue) {
		this.successValue = successValue;
	}

	@Column(name = "status", nullable = false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}