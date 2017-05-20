package com.yihu.jw.restmodel.wx;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class MWxWechat {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getWeichatId() {
        return weichatId;
    }

    public void setWeichatId(String weichatId) {
        this.weichatId = weichatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
}
