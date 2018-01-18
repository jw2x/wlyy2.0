package com.yihu.jw.restmodel.iot.dict;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 系统字典表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "系统字典表")
public class IotSystemDictVO implements Serializable {

    @ApiModelProperty("字典项代码")
    private String code;//
    @ApiModelProperty("字典项值")
    private String value;//

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
