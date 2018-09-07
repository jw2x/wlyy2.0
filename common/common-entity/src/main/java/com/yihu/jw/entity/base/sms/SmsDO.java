package com.yihu.jw.entity.base.sms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity - 短信记录
 * Created by progr1mmer on 2018/8/14.
 */
@Entity
@Table(name = "base_sms")
public class SmsDO extends UuidIdentityEntity {

	//应用ID
	private String clientId;
	//使用的网关
	private String smsGatewayId;
	//请求的ip地址
	private String requestIp;
	//短信接收号码
	private String mobile;
	//短信内容
	private String content;
	//过期时间
	private Date deadline;
	//验证码
	private String captcha;
	//短信标签
	private SmsTemplateDO.Type type;

	@Column(name = "client_id", nullable = false)
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "sms_gateway_id", nullable = false)
	public String getSmsGatewayId() {
		return smsGatewayId;
	}

	public void setSmsGatewayId(String smsGatewayId) {
		this.smsGatewayId = smsGatewayId;
	}

	@Column(name = "request_ip", nullable = false)
	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	@Column(name = "mobile", nullable = false)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", nullable = false)
	public SmsTemplateDO.Type getType() {
		return type;
	}

	public void setType(SmsTemplateDO.Type type) {
		this.type = type;
	}

	@Column(name = "deadline", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name = "captcha", nullable = false)
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}