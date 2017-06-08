package com.yihu.jw.wx.model;// default package

import com.yihu.jw.base.model.base.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * WxTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template")
public class WxTemplate extends IdEntity implements java.io.Serializable {

    // Fields
    private String code;//模板code
    private String title;//模板标题
    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    private String templateId;//微信模板id
    private String content;//模板内容
    private String createUser;//创建人
    private String createUserName;//创建人名
    private Date createTime;//创建时间
    private String updateUser;//修改人
    private String updateUserName;//修改人名称
    private Date updateTime;//修改时间
    private String remark;
    private Integer status;  //状态 -1删除 0 冻结 1可用

    public WxTemplate(String code, String title, String wechatCode, String templateId, String content, String createUser, String createUserName, Date createTime, String updateUser, String updateUserName, Date updateTime, String remark, Integer status) {
        this.code = code;
        this.title = title;
        this.wechatCode = wechatCode;
        this.templateId = templateId;
        this.content = content;
        this.createUser = createUser;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateUserName = updateUserName;
        this.updateTime = updateTime;
        this.remark = remark;
        this.status = status;
    }

    /**
     * default constructor
     */
    public WxTemplate() {
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="wechat_code")
    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    @Column(name="template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Column(name="content")

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getStatus() {
        return status;
    }
    // Constructors

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "code", length = 64)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "create_user", length = 200)
    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "create_user_name", length = 200)
    public String getCreateUserName() {
        return this.createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 0)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_user", length = 200)
    public String geUpdateUser() {
        return this.updateUser;
    }

    public void seUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Column(name = "update_user_name", length = 200)
    public String geUpdateUserName() {
        return this.updateUserName;
    }

    public void seUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 0)
    public Date geUpdateTime() {
        return this.updateTime;
    }

    public void seUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "remark", length = 1000)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}