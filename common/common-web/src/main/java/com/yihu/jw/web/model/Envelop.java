package com.yihu.jw.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by chenweida on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "基础实体")
public class Envelop implements Serializable {

    protected static final long serialVersionUID = -67188388306700736L;

    @ApiModelProperty("信息")
    protected String desc;
    @ApiModelProperty("状态（200成功，-1是失败）")
    protected int code;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
