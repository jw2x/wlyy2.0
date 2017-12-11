package com.yihu.jw.model;// default package


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * WlyySaas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_saas")
public class SaasDO implements Serializable, ClientDetails {

    // Fields

    private String name;//名称
    private Integer status;//状态 -1 已删除 0待审核 1审核通过 2 审核不通过
    private String remark;//备注
    // Constructors
    @CreatedDate
    @Column(name = "create_time", nullable = false, length = 0, updatable = false)
    private Date createTime;

    @CreatedBy
    @Column(name = "create_user", updatable = false)
    private String createUser;

    @CreatedBy
    @Column(name = "create_user_name", updatable = false)
    private String createUserName;

    @LastModifiedDate
    @Column(name = "update_time", nullable = false, length = 0)
    private Date updateTime;

    @LastModifiedBy
    @Column(name = "update_user", length = 100)
    private String updateUser;

    @LastModifiedBy
    @Column(name = "update_user_name", length = 50)
    private String updateUserName;


    @Column(name = "app_id", length = 255)
    private String appId;

    @Column(name = "app_secret", length = 255)
    private String appSecret;

    @Column(name = "url", length = 255)
    private String url;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;  // 非业务主键

    /**
     * default constructor
     */
    public SaasDO() {
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

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //================================oauth2========================================


    @Override
    public String getClientId() {
        return appId;
    }

    @Override
    public String getClientSecret() {
        return appSecret;
    }

    @Override
    public Set<String> getResourceIds() {
        return new HashSet<>();
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    /**
     * 是否在授权范围
     * @return
     */
    @Override
    public boolean isScoped() {
        return false;
    }

    /**
     * 允许的授权范围
     *
     * @return
     */
    @Override
    public Set<String> getScope() {
        Set<String> set = new HashSet<>();
        set.add("app");
        return set;
    }

    /**
     * 该client允许的授权类型
     *
     * @return
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> strings = new HashSet<>();
        strings.add("password");
        strings.add("custom_password");
        strings.add("authorization_code");
        strings.add("implicit");
        strings.add("refresh_token");
        return strings;
    }

    /**
     * 授权码模式支持的跳转请求
     * @return
     */
    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> strings = new HashSet<>();
        strings.add("http://example.com");
        strings.add(url);
        return strings;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    /**
     * 过期时间
     * @return
     */
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return 7200;
    }

    /**
     * 过期时间
     * @return
     */
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return 7200;
    }

    /**
     * 是否自动授权
     * @param scope
     * @return
     */
    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }

}