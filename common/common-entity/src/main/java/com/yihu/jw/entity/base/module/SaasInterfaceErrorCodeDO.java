package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接口错误说明
 * @author yeshijie on 2018/10/11.
 */
@Entity
@Table(name = "base_saas_interface_error_code")
public class SaasInterfaceErrorCodeDO extends UuidIdentityEntity {

    private String saasId;//saas_id
    private String saasInterfaceId;//接口id
    private String name;//错误码名称
    private String description;//错误码描述
    private String solution;//解决方案
    private Integer sort;//排序
    private Integer del;//删除标志

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "saas_interface_id")
    public String getSaasInterfaceId() {
        return saasInterfaceId;
    }

    public void setSaasInterfaceId(String saasInterfaceId) {
        this.saasInterfaceId = saasInterfaceId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "del")
    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
