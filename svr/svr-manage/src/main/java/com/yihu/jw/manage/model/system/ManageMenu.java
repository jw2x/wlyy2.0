package com.yihu.jw.manage.model.system;// default package

import com.yihu.jw.manage.model.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Map;

/**
 * ManageMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manage_menu")
public class ManageMenu extends IdEntity implements java.io.Serializable {

    // Fields
    private String parentCode;
    private String name;
    private Integer status;
    private Integer sort;
    private String remark;
    private Integer type;
    @Transient
    private String text;
    @Transient
    private List<ManageMenu> children;
    @Transient
    private List<String> url;
    @Transient
    private List<String> method;
    @Transient
    private String state;

    @Transient
    private List<Map<String,Object>> req;//用于取值

    /**
     * default constructor
     */
    public ManageMenu() {
    }

    public List<Map<String, Object>> getReq() {
        return req;
    }

    public void setReq(List<Map<String, Object>> req) {
        this.req = req;
    }

    public List<String> getMethod() {
        return method;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getText() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ManageMenu> getChildren() {
        return children;
    }

    public void setChildren(List<ManageMenu> children) {
        this.children = children;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "parent_code", length = 100)
    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "remark", length = 1500)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
