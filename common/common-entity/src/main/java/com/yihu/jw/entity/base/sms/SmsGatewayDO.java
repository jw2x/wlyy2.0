package com.yihu.jw.entity.base.sms;// default package


import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseSmsGateway entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_sms_gateway")
public class SmsGatewayDO extends UuidIdentityEntity implements java.io.Serializable {

	// Fields
	//private String saasId;  //关联 base_saas code
	private String name;  //名称
    private String officialWebsite; //官网地址
	private String username;  //短信接口的账号
    private String password;  //短信接口的密码
    private String certificate; //调用凭证（json串）
    private String ip;  //短信接口的ip地址
    private String url;	 //短信接口的url
	private Integer status;  // -1 删除 0 禁用 可用


	/** default constructor */
	public SmsGatewayDO() {
	}

	/** full constructor */
	public SmsGatewayDO(Long id, String code, String saasId,
                        String orgCode, String ip, String username, String password,
                        String url) {
		//this.saasId = saasId;
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.url = url;
	}

	/*@Column(name = "saas_id", length = 64)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}*/

	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}