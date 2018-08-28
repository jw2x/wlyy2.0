package com.yihu.jw.restmodel.base.sms;

import com.yihu.jw.entity.base.sms.SmsGatewayDO;
import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * VO - 短信网关
 * Created by Progr1mmer on 2018/8/27.
 */
@ApiModel(value = "SmsGatewayVO", description = "短信网关")
public class SmsGatewayVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas id", example = "402803ee656498890165649ad2da0000")
    private String saasId;
    @ApiModelProperty(value = "网关名称", example = "基础短信网关")
    private String name;
    @ApiModelProperty(value = "短信接口的账号", example = "user123")
    private String username;
    @ApiModelProperty(value = "短信接口的密码", example = "password123")
    private String password;
    @ApiModelProperty(value = "官网地址", example = "http://www.smsgateway.com")
    private String website;
    @ApiModelProperty(value = "短信接口调用的地址", example = "http://www.smsgateway.com/api/v1.0")
    private String url;
    @ApiModelProperty(value = "调用凭证（json串）", example = "{\"app_id\":\"EwC0iRSrcS\",\"signature\":\"iREOlyuyKfRBIGOHbBGJ\"}")
    private String certificate;
    @ApiModelProperty(value = "状态", example = "available")
    private SmsGatewayDO.Status status;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public SmsGatewayDO.Status getStatus() {
        return status;
    }

    public void setStatus(SmsGatewayDO.Status status) {
        this.status = status;
    }
}
