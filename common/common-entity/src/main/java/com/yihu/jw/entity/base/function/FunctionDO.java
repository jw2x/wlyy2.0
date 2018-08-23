package com.yihu.jw.entity.base.function;


import com.yihu.jw.entity.IntegerIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 功能 - Entity
 * WlyyFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_function")
public class FunctionDO extends IntegerIdentityEntityWithOperator {

    //功能名称
    private String name;
    //网关url前缀
    private String prefix;
    //功能对应的后台url路径
    private String url;
    //备注
    private String remark;

    //用于jstree显示
    private String text;

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "prefix")
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "remark")
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }
}