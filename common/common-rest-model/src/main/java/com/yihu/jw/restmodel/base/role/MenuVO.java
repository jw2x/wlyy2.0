package com.yihu.jw.restmodel.base.role;

import com.yihu.jw.restmodel.UuidIdentityVOWithOperator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yeshijie on 2018/9/26.
 */
@ApiModel(value = "MenuVO", description = "菜单")
public class MenuVO extends UuidIdentityVOWithOperator {

    @ApiModelProperty(value = "名称", example = "预约挂号")
    private String name;
    @ApiModelProperty(value = "请求路径", example = "/usr/var")
    private String url;
//    @ApiModelProperty(value = "请求方式", example = "get,post")
//    private String method;
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;
    @ApiModelProperty(value = "父级ID", example = "父级ID")
    private String parentId;
    @ApiModelProperty(value = "状态", example = "1生效，0失效")
    private Integer status;
    @ApiModelProperty(value = "备注", example = "说明")
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
