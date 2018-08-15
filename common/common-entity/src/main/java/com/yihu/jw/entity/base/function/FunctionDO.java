package com.yihu.jw.entity.base.function;// default package


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * WlyyFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_function")
public class FunctionDO extends UuidIdentityEntityWithOperator implements java.io.Serializable {

    // Fields

    private String name; //功能名称
    private String url;//功能对应的后台url路径
    private String saasId; // saasid
    private String parentCode; //父功能code
    private Integer status; //状态 -1 删除 0 禁用 可用
    private String remark; //备注
    private List<FunctionDO> children = new ArrayList<>();
    private String text;//用于jstree显示
    // Constructors

    /**
     * default constructor
     */
    public FunctionDO() {
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "saas_id", length = 100)
    public String getSaasId() {
        return this.saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "parent_code", length = 100)
    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name = "status", precision = 2, scale = 0)
    public Integer getStatus() {
        return this.status;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Transient
    public List<FunctionDO> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionDO> children) {
        this.children = children;
    }

    @Transient
    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }
}