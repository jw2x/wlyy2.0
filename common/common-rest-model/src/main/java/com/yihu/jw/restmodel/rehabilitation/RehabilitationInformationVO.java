package com.yihu.jw.restmodel.rehabilitation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yihu.jw.restmodel.iot.common.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 就诊信息表
 * @author humingfen on 2018/4/26.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "就诊记录表", description = "就诊记录表")
public class RehabilitationInformationVO extends BaseVO implements Serializable {

    @ApiModelProperty("居民id")
    private String patientId;
    @ApiModelProperty("就诊医院")
    private String hospital;
    @ApiModelProperty("就诊科室")
    private String department;
    @ApiModelProperty("诊断小结")
    private String summary;
    @ApiModelProperty("医嘱")
    private String advice;
    @ApiModelProperty("疾病标签")
    private String disease;
    @ApiModelProperty("出院时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    private Date dischargeTime;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(Date dischargeTime) {
        this.dischargeTime = dischargeTime;
    }
}