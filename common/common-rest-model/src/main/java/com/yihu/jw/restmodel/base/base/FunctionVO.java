package com.yihu.jw.restmodel.base.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;

/**
 * Created by chenweida on 2017/5/19.
 */
@ApiModel(value = "FunctionVO", description = "功能")
public class FunctionVO implements Serializable {

    @ApiModelProperty(value = "id", example = "1" )
    protected Integer id;
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
    //功能名称
    @ApiModelProperty(value = "名称", example = "功能1" )
    private String name;
    //网关url前缀
    @ApiModelProperty(value = "网关url前缀", example = "/base" )
    private String prefix;
    //功能对应的后台url路径
    @ApiModelProperty(value = "功能对应的后台url路径", example = "/function/list" )
    private String url;
    //备注
    @ApiModelProperty(value = "备注", example = "我是备注" )
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
