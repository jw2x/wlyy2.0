package com.yihu.jw.restmodel.iot.datainput;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepInfoVO {

    @ApiModelProperty(value = "时间戳",example = "yyyy-MM-dd HH:mm:ss",hidden = true)
    private String timestamp;
    @ApiModelProperty(value = "运动步数",example = "到某一时刻走了多少步",hidden = true)
    private Integer step;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
