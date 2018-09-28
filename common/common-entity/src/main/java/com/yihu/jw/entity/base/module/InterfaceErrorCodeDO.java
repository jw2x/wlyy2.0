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
    private String describe;//错误码描述
    private String solution;//解决方案

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

    @Column(name = "describe")
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Column(name = "solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
