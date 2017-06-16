package com.yihu.jw.base.model.sms;// default package

import com.yihu.jw.base.model.IdEntity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 * BaseSms entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_sms")
public class BaseSms extends IdEntity implements java.io.Serializable {

	// Fields

	private String saasId;	//saasid 关联base_saas code
	private String mobile;	//电话号码
	private String ip;	//发送短信的ip地址
	private Integer type;	//发送短信的类别
	private String captcha;	//验证码 1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录
	private String content;	// 短信内容
	private Date deadline;	//过期时间
	private Integer status;	//短信状态 状态，0未发送，1已发送
	private Date czrq; //操作时间

	// Constructors

	/** default constructor */
	public BaseSms() {
	}

	/** minimal constructor */
	public BaseSms(String mobile, String ip, Integer type, String captcha,
			String content, Timestamp deadline, Integer status, Timestamp czrq) {
		this.mobile = mobile;
		this.ip = ip;
		this.type = type;
		this.captcha = captcha;
		this.content = content;
		this.deadline = deadline;
		this.status = status;
		this.czrq = czrq;
	}

	/** full constructor */
	public BaseSms(String saasId, String mobile, String ip, Integer type,
			String captcha, String content, Timestamp deadline, Integer status,
			Timestamp czrq) {
		this.saasId = saasId;
		this.mobile = mobile;
		this.ip = ip;
		this.type = type;
		this.captcha = captcha;
		this.content = content;
		this.deadline = deadline;
		this.status = status;
		this.czrq = czrq;
	}


	@Column(name = "saas_id", length = 64)
	public String getSaasId() {
		return this.saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	@Column(name = "mobile", nullable = false, length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "ip", nullable = false, length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "captcha", nullable = false, length = 10)
	public String getCaptcha() {
		return this.captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	@Column(name = "content", nullable = false, length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline", nullable = false, length = 0)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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