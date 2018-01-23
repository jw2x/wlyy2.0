package com.yihu.jw.restmodel.iot.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yeshijie on 2018/1/18.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "是否存在")
public class ExistVO implements Serializable{

    @ApiModelProperty("是否存在(1已经存在，0不存在)")
    private Integer isExist;

    public ExistVO() {
    }

    public ExistVO(Integer isExist) {
        this.isExist = isExist;
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}
