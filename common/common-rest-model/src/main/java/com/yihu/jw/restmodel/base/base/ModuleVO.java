package com.yihu.jw.restmodel.base.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenweida on 2017/5/19.
 */
public class ModuleVO{
    private String id;
    private String name; //模块名称
    private String saasId; //关联 Saas code
    private String parentCode;//父id
    private Integer status; //-1 删除 0 禁用 可用
    private String createUser;
    private String createUserName;
    private Date createTime;
    private String updateUser;
    private String updateUserName;
    private Date updateTime;
    private String remark;
    private String state ;   //closed:表示有子节点   open:表示没有子节点
    private List<ModuleVO> children = new ArrayList<>();

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

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ModuleVO> getChildren() {
        return children;
    }

    public void setChildren(List<ModuleVO> children) {
        this.children = children;
    }
}
