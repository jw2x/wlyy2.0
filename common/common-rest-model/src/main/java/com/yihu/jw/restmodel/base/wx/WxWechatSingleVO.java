package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by Trick on 2018/10/16.
 */
public class WxWechatSingleVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "名称")
    private String name;//名称
    @ApiModelProperty(value = "token")
    private String token;//token
    @ApiModelProperty(value = "加密密钥")
    private String encodingAesKey;//加密密钥
    @ApiModelProperty(value = "加密方式  0:明文模式   1:兼容模式   2:安全模式")
    private Integer encType;//加密方式  0:明文模式   1:兼容模式   2:安全模式
    @ApiModelProperty(value = "类型 -1 已删除 0未认证 1已认证 2 审核中 3.审核未通过'")
    private Integer status;//'类型 -1 已删除 0未认证 1已认证 2 审核中 3.审核未通过'
    @ApiModelProperty(value = "1：服务号 2 订阅号")
    private String type;//'1：服务号 2 订阅号
    @ApiModelProperty(value = "微信app_id")
    private String appId;//'微信app_id'
    @ApiModelProperty(value = "微信app_secret'")
    private String appSecret;//'微信app_secret'
    @ApiModelProperty(value = "原始ID")
    private String appOriginId;//原始ID
    @ApiModelProperty(value = "微信base_url'")
    private String baseUrl;//'微信base_url'
    @ApiModelProperty(value = "公总号登录账户")
    private String userName;//公总号登录账户
    @ApiModelProperty(value = "用户密码")
    private String password;//用户密码
    @ApiModelProperty(value = "1.自运营，2.外接")
    private String publicType;//1.自运营，2.外接
    @ApiModelProperty(value = "备注")
    private String remark;//'备注'
    //创建时间
    @ApiModelProperty(value = "创建时间")
    protected Date createTime;
    //创建者
    @ApiModelProperty(value = "创建者")
    protected String createUser;
    //创建者
    @ApiModelProperty(value = "创建者")
    protected String createUserName;
    //更新时间
    @ApiModelProperty(value = "更新时间")
    protected Date updateTime;
    //更新者
    @ApiModelProperty(value = "更新者")
    protected String updateUser;
    //更新者
    @ApiModelProperty(value = "更新者")
    protected String updateUserName;

    @ApiModelProperty(value = "租户")
    private List<WxSaasVO> saas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAppOriginId() {
        return appOriginId;
    }

    public void setAppOriginId(String appOriginId) {
        this.appOriginId = appOriginId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public List<WxSaasVO> getSaas() {
        return saas;
    }

    public void setSaas(List<WxSaasVO> saas) {
        this.saas = saas;
    }
}
