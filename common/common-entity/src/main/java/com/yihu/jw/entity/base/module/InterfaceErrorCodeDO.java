package com.yihu.jw.entity.base.module;

import com.yihu.jw.entity.UuidIdentityEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接口错误说明
 * @author yeshijie on 2018/9/28.
 */
@Entity
@Table(name = "base_interface_error_code")
public class InterfaceErrorCodeDO extends UuidIdentityEntity {

    private String interfaceId;//接口id
    private String name;//错误码名称
    private String description;//错误码描述
    private String solution;//解决方案
    private Integer sort;//排序
    private Integer del;//删除标志

    @Column(name = "interface_id")
    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
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
