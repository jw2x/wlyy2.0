package com.yihu.jw.wx.model;// default package

import java.util.Date;
import javax.persistence.*;

/**
 * 微信 accesstoken表
 */
@Entity
@Table(name = "wx_access_token")
public class WxAccessToken implements java.io.Serializable {

	// Fields

	private Integer id;//业务id
	private String wechatCode;//关联的微信code 关联表 Wx_Wechat
	private String accessToken;//调用微信返回的accesstoken
	private long addTimestamp;//创建时间
	private Integer expiresIn;//凭证有效时间（秒）
	private Date czrq;//操作时间

	// Constructors

	/** default constructor */
	public WxAccessToken() {
	}

	/** minimal constructor */
	public WxAccessToken(Integer id, String accessToken, long addTimestamp,
			Integer expiresIn, Date czrq) {
		this.id = id;
		this.accessToken = accessToken;
		this.addTimestamp = addTimestamp;
		this.expiresIn = expiresIn;
		this.czrq = czrq;
	}

	/** full constructor */
	public WxAccessToken(Integer id, String wechatCode, String accessToken,
			long addTimestamp, Integer expiresIn, Date czrq) {
		this.id = id;
		this.wechatCode = wechatCode;
		this.accessToken = accessToken;
		this.addTimestamp = addTimestamp;
		this.expiresIn = expiresIn;
		this.czrq = czrq;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "wechat_code", length = 64)
	public String getWechatCode() {
		return this.wechatCode;
	}

	public void setWechatCode(String wechatCode) {
		this.wechatCode = wechatCode;
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
	public Integer getExpiresIn() {
		return this.expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
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

}