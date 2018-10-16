package com.yihu.jw.entity.base.saas;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zdm on 2018/10/16.
 */
@Entity
@Table(name = "base_email_template_config")
public class BaseEmailTemplateConfigDO extends UuidIdentityEntityWithOperator {
    @Column(name = "template_name")
    private String templateName; // T '自定义模板名称
    @Column(name = "first")
    private String first; //
    @Column(name = "url")
    private String url; // 转链接
    @Column(name = "remark")
    private String remark; //
    @Column(name = "keyword1")
    private String keyword1; // 自定义文本1
    @Column(name = "keyword2")
    private String keyword2; // 自定义文本2
    @Column(name = "keyword3")
    private String keyword3; // 自定义文本3
    @Column(name = "keyword4")
    private String keyword4; // 自定义文本4
    @Column(name = "keyword5")
    private String keyword5; // 自定义文本5
    @Column(name = "keyword6")
    private String keyword6; // 自定义文本6
    @Column(name = "keyword7")
    private String keyword7; // 自定义文本7
    @Column(name = "status")
    private Integer status; // 状态 1：正常 0：删除

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
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

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public String getKeyword6() {
        return keyword6;
    }

    public void setKeyword6(String keyword6) {
        this.keyword6 = keyword6;
    }

    public String getKeyword7() {
        return keyword7;
    }

    public void setKeyword7(String keyword7) {
        this.keyword7 = keyword7;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
