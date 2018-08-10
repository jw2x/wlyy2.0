package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * WxWechat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_wechat")
public class WxWechatDO extends IdEntityWithOperation implements java.io.Serializable {

    // Fields
    private String name;//名称
    private String token;//token
    private String encodingAesKey;//加密密钥
    private Integer encType;//加密方式  0:明文模式   1:兼容模式   2:安全模式
    private Integer status;//'类型 -1 已删除 0待审核 1审核通过 2 审核不通过'
    private String type;//'1：服务号 2 订阅号
    private String appId;//'微信app_id'
    private String appSecret;//'微信app_secret'
    private String baseUrl;//'微信base_url'
    private String remark;//'备注'


    @Transient
    private List<Map<String,Object>> children = new ArrayList<>();
    @Transient
    private String state;

    /**
     * default constructor
     */
    public WxWechatDO() {
    }

    public List<Map<String, Object>> getChildren() {
        return children;
    }

    public void setChildren(List<Map<String, Object>> children) {
        this.children = children;
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

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", precision = 2, scale = 0)
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}