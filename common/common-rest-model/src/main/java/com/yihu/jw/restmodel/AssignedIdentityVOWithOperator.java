package com.yihu.jw.restmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by progr1mmer on 2018/8/13.
 */

public abstract class AssignedIdentityVOWithOperator extends AssignedIdentityVO {

    @ApiModelProperty(value = "创建日期", example = "2018-03-14 11:35:34" )
    protected Date createTime;
    @ApiModelProperty(value = "创建者", example = "0dae0003590016e5b3865e377b2f8615" )
    protected String createUser;
    @ApiModelProperty(value = "创建者用户名", example = "Progr1mmer" )
    protected String createUserName;
    @ApiModelProperty(value = "修改日期", example = "2018-03-14 11:35:34" )
    protected Date updateTime;
    @ApiModelProperty(value = "修改者", example = "0dae0003590016e5b3865e377b2f8615" )
    protected String updateUser;
    @ApiModelProperty(value = "修改者用户名", example = "Progr1mmer" )
    protected String updateUserName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
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

}
