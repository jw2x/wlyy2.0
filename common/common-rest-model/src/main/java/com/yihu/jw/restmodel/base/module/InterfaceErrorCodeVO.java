package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;

/**
 * @author yeshijie on 2018/9/28.
 */
@ApiModel(value = "InterfaceErrorCodeVO", description = "接口错误说明")
public class InterfaceErrorCodeVO extends UuidIdentityVO {

    private String interfaceId;//接口id
    private String name;//错误码名称
    private String description;//错误码描述
    private String solution;//解决方案
    private Integer sort;//排序
    private Integer del;//删除标志

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
