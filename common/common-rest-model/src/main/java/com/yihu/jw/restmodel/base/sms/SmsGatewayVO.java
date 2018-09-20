package com.yihu.jw.restmodel.base.sms;

import com.yihu.jw.entity.base.sms.SmsGatewayDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 短信网关
 * Created by Progr1mmer on 2018/8/27.
 */
@ApiModel(value = "SmsGatewayVO", description = "短信网关")
public class SmsGatewayVO extends UuidIdentityVOWithOperator {

    @ApiModelProperty(value = "client id", example = "EwC0iRSrcS")
    private String clientId;
    @ApiModelProperty(value = "网关名称", example = "基础短信网关")
    private String name;
    @ApiModelProperty(value = "短信接口的账号", example = "user123")
    private String username;
    @ApiModelProperty(value = "短信接口的密码", example = "password123")
    private String password;
    @ApiModelProperty(value = "官网地址", example = "http://www.smsgateway.com")
    private String website;
    @ApiModelProperty(value = "超时时间（分钟）", example = "10")
    private Integer expireMin;
    @ApiModelProperty(value = "短信接口调用的地址", example = "http://www.smsgateway.com/api/v1.0")
    private String requestUrl;
    @ApiModelProperty(value = "调用凭证（json串）", example = "{\"app_id\":\"EwC0iRSrcS\",\"signature\":\"iREOlyuyKfRBIGOHbBGJ\"}")
    private String requestCertificate;
    @ApiModelProperty(value = "短信接口调用状态标识", example = "result")
    private String responseCode;
    @ApiModelProperty(value = "调用成功的状态码", example = "0")
    private String successValue;
    @ApiModelProperty(value = "状态", example = "available")
    private SmsGatewayDO.Status status;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getExpireMin() {
        return expireMin;
    }

    public void setExpireMin(Integer expireMin) {
        this.expireMin = expireMin;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestCertificate() {
        return requestCertificate;
    }

    public void setRequestCertificate(String requestCertificate) {
        this.requestCertificate = requestCertificate;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSuccessValue() {
        return successValue;
    }

    public void setSuccessValue(String successValue) {
        this.successValue = successValue;
    }

    public SmsGatewayDO.Status getStatus() {
        return status;
    }

    public void setStatus(SmsGatewayDO.Status status) {
        this.status = status;
    }
}
