package com.yihu.jw.restmodel.base.sms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.base.sms.SmsTemplateDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * VO - 短信记录
 * Created by progr1mmer on 2018/8/28.
 */
@ApiModel(value = "SmsVO", description = "短信记录")
public class SmsVO extends UuidIdentityVOWithOperator {

    //应用ID
    @ApiModelProperty(value = "应用ID", example = "EwC0iRSrcS")
    private String clientId;
    //使用的网关
    @ApiModelProperty(value = "使用的网关", example = "402803f9657fa37b01657fb58b9b0000")
    private String smsGatewayId;
    //请求的ip地址
    @ApiModelProperty(value = "请求的ip地址", example = "192.168.0.121")
    private String requestIp;
    //短信接收号码
    @ApiModelProperty(value = "短信接收号码", example = "18888888888")
    private String mobile;
    //短信内容
    @ApiModelProperty(value = "短信内容", example = "【i健康综合管理平台】您使用的是i健康综合管理平台短信模板，您的验证码是826612，请于10分钟内正确输入！")
    private String content;
    //过期时间
    @ApiModelProperty(value = "过期时间", example = "2018-09-03 15:34:34")
    private Date deadline;
    //验证码
    @ApiModelProperty(value = "验证码", example = "651187")
    private String captcha;
    @ApiModelProperty(value = "标签", example = "login")
    private SmsTemplateDO.Type type;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSmsGatewayId() {
        return smsGatewayId;
    }

    public void setSmsGatewayId(String smsGatewayId) {
        this.smsGatewayId = smsGatewayId;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public SmsTemplateDO.Type getType() {
        return type;
    }

    public void setType(SmsTemplateDO.Type type) {
        this.type = type;
    }
}
