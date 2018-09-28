package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;

/**
 * @author yeshijie on 2018/9/28.
 */
@ApiModel(value = "InterfaceVO", description = "接口错误说明")
public class InterfaceErrorCodeVO extends UuidIdentityVO {

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
