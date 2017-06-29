package com.yihu.jw.manage.model.wechat;// default package


import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * WechatConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_wechat")
public class WechatConfig extends IdEntity implements java.io.Serializable {

    // Fields
    private String saasId;//'saas配置id'
    private String name;//名称
    private String token;//token
    private String encodingAesKey;//加密密钥
    private Integer encType;//加密方式  0:明文模式   1:兼容模式   2:安全模式
    private Integer status;//'类型 -1 已删除 0待审核 1审核通过 2 审核不通过'
    private String type;//'1：服务号 2 订阅号
    private String appId;//'微信app_id'
    private String appSecret;//'微信app_secret'
    private String baseUrl;//'微信base_url'
    private String createUser;//'创建人'
    private String createUserName;//'创建人名'
    private Date createTime;//'创建时间'
    private String updateUser;//'修改人
    private String updateUserName;//'修改人名'
    private Date updateTime;//'修改时间'
    private String remark;//'备注'
    public WechatConfig(String code, String saasId, String name, String token, String encodingAesKey, Integer encType, Integer status, String type, String appId, String appSecret, String baseUrl, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark) {
        this.code = code;
        this.saasId = saasId;
        this.name = name;
        this.token = token;
        this.encodingAesKey = encodingAesKey;
        this.encType = encType;
        this.status = status;
        this.type = type;
        this.appId = appId;
        this.appSecret = appSecret;
        this.baseUrl = baseUrl;
        this.createUser = createUser;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateUserName = updateUserName;
        this.updateTime = updateTime;
        this.remark = remark;
    }
    /**
     * default constructor
     */
    public WechatConfig() {
    }
    @Column(name = "enc_type")
    public Integer getEncType() {
        return encType;
    }

    public void setEncType(Integer encType) {
        this.encType = encType;
    }

    @Column(name = "token", length = 64)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name="encoding_aes_key")
    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    @Column(name = "saas_id", length = 50)
    public String getSaasId() {
        return this.saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "type", length = 2)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "app_id", length = 200)
    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name = "app_secret", length = 200)
    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Column(name = "base_url", length = 200)
    public String getBaseUrl() {
        return this.baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}