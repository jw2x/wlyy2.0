package com.yihu.jw.restmodel.base.module;

import com.yihu.jw.restmodel.UuidIdentityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yeshijie on 2018/9/28.
 */
@ApiModel(value = "InterfaceParamVO", description = "接口入参出参")
public class InterfaceParamVO extends UuidIdentityVO {

    @ApiModelProperty(value = "接口id", example = "接口id")
    private String interfaceId;
    @ApiModelProperty(value = "参数名", example = "user")
    private String name;
    @ApiModelProperty(value = "参数类型", example = "HEADER")
    private String paramType;
    @ApiModelProperty(value = "数据类型", example = "VERCHAR")
    private String dataType;
    @ApiModelProperty(value = "是否必填(1是，0否)", example = "1")
    private Integer isRequire;
    @ApiModelProperty(value = "最大长度", example = "1")
    private Integer maxLength;
    @ApiModelProperty(value = "是否公共（1是，0不是）", example = "1")
    private Integer common;
    @ApiModelProperty(value = "描述", example = "")
    private String description;
    @ApiModelProperty(value = "示例", example = "")
    private String example;
    @ApiModelProperty(value = "类型（1入参，2出参）", example = "1")
    private Integer type;
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;
    @ApiModelProperty(value = "删除标志", example = "1")
    private Integer del;

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

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getIsRequire() {
        return isRequire;
    }

    public void setIsRequire(Integer isRequire) {
        this.isRequire = isRequire;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getCommon() {
        return common;
    }

    public void setCommon(Integer common) {
        this.common = common;
    }
}
