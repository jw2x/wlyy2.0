package com.yihu.jw.restmodel.rehabilitation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 就诊信息表
 * @author humingfen on 2018/4/27.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "治疗方案表", description = "治疗方案表")
public class RehabilitationTreatmentProgramVO extends BaseVO implements Serializable {
    @ApiModelProperty("方案名称")
    private String name;
    @ApiModelProperty("训练次数/时间")
    private String frequency;
    @ApiModelProperty("方案名称")
    private Integer timesDaily;
    @ApiModelProperty("附加说明")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getTimesDaily() {
        return timesDaily;
    }

    public void setTimesDaily(Integer timesDaily) {
        this.timesDaily = timesDaily;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
