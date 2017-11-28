package com.yihu.jw.restmodel.base.version;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenweida on 2017/6/16.
 */
public class BaseServerVersionVO {
    private String id;
    private String saasId;//关联base_saas code
    private String userCode;//用户表code 医生
    private String name; //版本名称
    private Integer versionInt;//版本号
    private Date createTime;
    private String createUser;
    private String createUserName;
    private Date updateTime;
    private String updateUser;
    private String updateUserName;
    private Integer status;////-1 删除 0 禁用 可用
    private String remark;

    private List<BaseServerVersionVO> children = new ArrayList<>();

    //children长度为0时    state  “open”表示是子节点，“closed”表示为父节点；
    // children长度>0时,  state   “open,closed”表示是节点的打开关闭
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<BaseServerVersionVO> getChildren() {
        return children;
    }

    public void setChildren(List<BaseServerVersionVO> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
