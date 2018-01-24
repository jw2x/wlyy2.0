package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 设备数据传输方式表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "设备数据传输方式表")
public class IotProductDataTransmissionVO extends BaseVO implements Serializable {

    @ApiModelProperty("传输协议类型")
    private String type;
    @ApiModelProperty("传输协议类型名称")
    private String typeName;

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
