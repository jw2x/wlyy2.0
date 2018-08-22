package com.yihu.jw.entity.base.wx;


import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 微信 accesstoken表
 */
@Entity
@Table(name = "wx_access_token")
public class WxAccessTokenDO extends UuidIdentityEntity implements java.io.Serializable {

	// Fields

	private String wechatId;//关联的微信code 关联表 Wx_Wechat
	private String accessToken;//调用微信返回的accesstoken
	private Long addTimestamp;//创建时间 秒数
	private Long expiresIn;//凭证有效时间（秒）
	private Date czrq;//操作时间
	private String code;
	/** default constructor */
	public WxAccessTokenDO() {
	}


	@Column(name = "wechat_id", length = 64)
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}


	@Column(name = "access_token", nullable = false, length = 300)
	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "add_timestamp", nullable = false, precision = 15, scale = 0)
	public long getAddTimestamp() {
		return this.addTimestamp;
	}

	public void setAddTimestamp(long addTimestamp) {
		this.addTimestamp = addTimestamp;
	}

	@Column(name = "expires_in", nullable = false)
	public Long getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "czrq", nullable = false, length = 0)
	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}