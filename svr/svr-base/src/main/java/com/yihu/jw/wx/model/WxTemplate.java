package com.yihu.jw.wx.model;// default package

import com.yihu.jw.base.model.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * WxTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template")
public class WxTemplate extends IdEntity implements java.io.Serializable {

    // Fields
    private String title;//模板标题
    private String wechatCode;//关联的微信code 关联表 Wx_Wechat
    private String templateId;//微信模板id
    private String content;//模板内容
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

    public Integer getStatus() {
        return status;
    }
    // Constructors

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

}