package com.yihu.jw.restmodel.base.sms;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by chenweida on 2017/5/22.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MSms {

    private Long id;
    private String saasId;	//saasid 关联base_saas code
    private String mobile;	//电话号码
    private String ip;	//发送短信的ip地址
    private Integer type;	//发送短信的类别
    private String captcha;	//验证码 1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录
    private String content;	// 短信内容
    private Date deadline;	//过期时间
    private Integer status;	//短信状态 状态，0未发送，1已发送
    private Date czrq; //操作时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCzrq() {
        return czrq;
    }

    public void setCzrq(Date czrq) {
        this.czrq = czrq;
    }
}
