package com.yihu.jw.restmodel.iot.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 设备测量数据表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(description = "产品附件表")
public class IotProductMeasuredDataVO extends BaseVO implements Serializable {

    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("编码")
    private String dataCode;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("说明")
    private String instruction;

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
