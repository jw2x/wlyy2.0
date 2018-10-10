package com.yihu.jw.restmodel.base.wx;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/20 0020.
 */
public class WxTemplateVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "模板标题")
    private String title;//模板标题
    @ApiModelProperty(value = "关联的微信code 关联表 Wx_Wechat")
    private String wechatId;//关联的微信code 关联表 Wx_Wechat
    @ApiModelProperty(value = "微信模板id")
    private String templateId;//微信模板id
    @ApiModelProperty(value = "模板名称（模板检索名称）")
    private String templateName;//模板名称（模板检索名称）
    @ApiModelProperty(value = "模板内容")
    private String content;//模板内容
    @ApiModelProperty(value = "remark")
    private String remark;

    @ApiModelProperty(value = "状态 -1删除 0 冻结 1可用")
    private Integer status;  //状态 -1删除 0 冻结 1可用
    @ApiModelProperty(value = "创建人")
    private String createUser;//创建人
    @ApiModelProperty(value = "创建人名")
    private String createUserName;//创建人名
    @ApiModelProperty(value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(value = "修改人")
    private String updateUser;//修改人
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;//修改人名称
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
