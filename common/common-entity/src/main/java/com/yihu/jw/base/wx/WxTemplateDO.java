package com.yihu.jw.base.wx;

import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * WxTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_template")
public class WxTemplateDO extends IdEntityWithOperation implements java.io.Serializable {

    // Fields
    private String title;//模板标题
    private String wechatId;//关联的微信code 关联表 Wx_Wechat
    @Transient
    private String wechatName;
    private String templateId;//微信模板id
    private String templateName;//模板名称（模板检索名称）
    private String content;//模板内容
    private String remark;
    private Integer status;  //状态 -1删除 0 冻结 1可用

    /**
     * default constructor
     */
    public WxTemplateDO() {
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="wechat_id")
    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    @Column(name="template_id")
    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Column(name="template_name")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
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

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }
}