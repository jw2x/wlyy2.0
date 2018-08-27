package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.entity.base.module.ModuleDO;
import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * VO - 模块
 * Created by Progr1mmer on 2018/8/24.
 */
@ApiModel(value = "ModuleVO", description = "模块")
public class ModuleVO extends UuidIdentityVOWithOperator {

    @ApiModelProperty(value = "模块名称", example = "模块1")
    private String name;
    @ApiModelProperty(value = "父ID", example = "402803ee656498890165649ad2da0000")
    private String parentId;
    @ApiModelProperty(value = "状态", example = "disable")
    private ModuleDO.Status status;
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;
    @ApiModelProperty(value = "节点信息（closed-表示有子节点，open-表示没有子节点)", example = "open")
    private String state ;
    @ApiModelProperty(value = "子集")
    private List<ModuleVO> children = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ModuleDO.Status getStatus() {
        return status;
    }

    public void setStatus(ModuleDO.Status status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ModuleVO> getChildren() {
        return children;
    }

    public void setChildren(List<ModuleVO> children) {
        this.children = children;
    }
}
