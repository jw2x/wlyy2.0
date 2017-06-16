package com.yihu.jw.restmodel.base.version;

import java.util.Date;

/**
 * Created by chenweida on 2017/6/16.
 */
public class MBaseServerVersionLog {

    private Long id;
    private String saasId; //关联saas code
    private String userCodes;//此次更新的用户 多个逗号分割
    private String name;//此次更新的版本名称
    private Integer versionInt;//版本号
    private Integer type;//1更新 2回滚
    private Date createTime;
    private String createUser;
    private String createUserName;

    public String getSaasId() {
        return saasId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getUserCodes() {
        return userCodes;
    }

    public void setUserCodes(String userCodes) {
        this.userCodes = userCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersionInt() {
        return versionInt;
    }

    public void setVersionInt(Integer versionInt) {
        this.versionInt = versionInt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
