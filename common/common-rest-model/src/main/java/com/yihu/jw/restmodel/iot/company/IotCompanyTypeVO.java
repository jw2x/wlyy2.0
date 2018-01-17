package com.yihu.jw.restmodel.iot.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 企业类型表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "企业三证变更记录", description = "企业三证变更记录")
public class IotCompanyTypeVO implements Serializable {

    @ApiModelProperty("企业类型code")
    private String type;//
    @ApiModelProperty("企业类型名称")
    private String typeName;//

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
