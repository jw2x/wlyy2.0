package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 接口错误说明
 * @author yeshijie on 2018/9/28.
 */
@ApiModel(value = "SaasInterfaceErrorCodeVO", description = "接口错误说明")
public class SaasInterfaceErrorCodeVO extends UuidIdentityVO {

    @ApiModelProperty(value = "saas_id", example = "saas_id")
    private String saasId;
    @ApiModelProperty(value = "接口id", example = "接口id")
    private String saasInterfaceId;
    @ApiModelProperty(value = "错误码名称", example = "错误码名称")
    private String name;
    @ApiModelProperty(value = "错误码描述", example = "错误码描述")
    private String description;
    @ApiModelProperty(value = "解决方案", example = "解决方案")
    private String solution;
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;
    @ApiModelProperty(value = "删除标志", example = "1")
    private Integer del;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getSaasInterfaceId() {
        return saasInterfaceId;
    }

    public void setSaasInterfaceId(String saasInterfaceId) {
        this.saasInterfaceId = saasInterfaceId;
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
