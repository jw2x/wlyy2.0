package com.yihu.jw.restmodel.base.module;

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
    @ApiModelProperty(value = "url", example = "/doctor/*")
    private String url;
    @ApiModelProperty(value = "类型", example = "0通用，1医生端你，2居民端")
    private String type;
    @ApiModelProperty(value = "状态", example = "1有效，0失效")
    private Integer status;
    @ApiModelProperty(value = "备注", example = "我是备注")
    private String remark;
    @ApiModelProperty(value = "节点信息（0-表示有子节点，1-表示没有子节点)", example = "1")
    private Integer isEnd ;
    @ApiModelProperty(value = "节点信息（0-表示非必选，1-表示必选)", example = "1")
    private Integer isMust ;
    @ApiModelProperty(value = "是否选中（0-表示未选，1-表示已选)", example = "1")
    private Integer isCheck ;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsMust() {
        return isMust;
    }

    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }

    public List<ModuleVO> getChildren() {
        return children;
    }

    public void setChildren(List<ModuleVO> children) {
        this.children = children;
    }

    public Integer getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }
}
