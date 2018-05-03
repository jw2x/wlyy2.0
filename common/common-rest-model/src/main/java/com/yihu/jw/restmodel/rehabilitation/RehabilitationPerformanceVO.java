package com.yihu.jw.restmodel.rehabilitation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Date;

/**
 * 康复计划执行情况表
 * @author humingfen on 2018/5/2.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "康复计划执行情况表", description = "康复计划执行情况表")
public class RehabilitationPerformanceVO extends BaseVO implements Serializable {
    @ApiModelProperty("治疗方案id")
    private String programId;
    @ApiModelProperty("开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date startTime;
    @ApiModelProperty("结束执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date endTime;
    @ApiModelProperty("执行状态")
    private Integer status;
    @ApiModelProperty("身体健康状态说明")
    private String description;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
