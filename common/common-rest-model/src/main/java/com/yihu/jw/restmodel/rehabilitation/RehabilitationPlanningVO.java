package com.yihu.jw.restmodel.rehabilitation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 康复计划表
 * @author humingfen on 2018/5/2.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "康复计划表", description = "康复计划表")
public class RehabilitationPlanningVO extends BaseVO implements Serializable {
    @ApiModelProperty("居民id")
    private String patientId;

    @ApiModelProperty("治疗方案id")
    private String programId;

    @ApiModelProperty("复检时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date recheckTime;

    @ApiModelProperty("附加说明")
    private String description;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Date getRecheckTime() {
        return recheckTime;
    }

    public void setRecheckTime(Date recheckTime) {
        this.recheckTime = recheckTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
