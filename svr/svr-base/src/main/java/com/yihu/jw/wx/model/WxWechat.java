package com.yihu.jw.wx.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * WxWechat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_wechat")
public class WxWechat extends IdEntity implements java.io.Serializable {

    // Fields
    private String code;//业务code
    private String saasId;//'saas配置id'
    private String weichatId;//微信的id
    private String name;//名称
    private String status;//'类型 -1 已删除 0待审核 1审核通过 2 审核不通过'
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

    public WxWechat(String code, String saasId, String weichatId, String name, String status, String type, String appId, String appSecret, String baseUrl, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark) {
        this.code = code;
        this.saasId = saasId;
        this.weichatId = weichatId;
        this.name = name;
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
    public WxWechat() {
    }


    @Column(name = "code", length = 64)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "saas_id", length = 50)
    public String getSaasId() {
        return this.saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "weichat_id", length = 50)
    public String getWeichatId() {
        return this.weichatId;
    }

    public void setWeichatId(String weichatId) {
        this.weichatId = weichatId;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status", precision = 2, scale = 0)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
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

    @Column(name = "create_user", length = 200)
    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "create_user_name", length = 200)
    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 0)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "update_user", length = 200)
    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_user_name", length = 200)
    public String getUpdateUserName() {
        return this.updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 0)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}