package com.yihu.jw.restmodel.base.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * Created by chenweida on 2017/5/19.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MSaas {
    private Long id;
    private String code;//业务code、
    private String name;//名称
    private Integer status;//状态 -1 已删除 0待审核 1审核通过 2 审核不通过
    private String createUser; //创建人code
    private String createUserName;//创建人名称
    private Date createTime;//创建时间
    private String modifyUser;//修改人
    private String modifyUserName;//修改人名称
    private Date modifyTime;//修改时间
    private String remark;//备注

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
