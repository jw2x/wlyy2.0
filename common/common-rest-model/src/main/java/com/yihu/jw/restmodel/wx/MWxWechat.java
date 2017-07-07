package com.yihu.jw.restmodel.wx;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class MWxWechat {

    private Long id;
    private String code;//业务code
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
    private List<MWxMenu> children;

    public List<MWxMenu> getChildren() {
        return children;
    }

    public void setChildren(List<MWxMenu> children) {
        this.children = children;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public Integer getEncType() {
        return encType;
    }

    public void setEncType(Integer encType) {
        this.encType = encType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
}
