package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Trick on 2018/4/25.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "专科医生与居民匹配关系", description = "专科医生与居民匹配关系")
public class SpecialistPatientRelationVO {

    @ApiModelProperty("saasid")
    private String saasId;
    @ApiModelProperty("最新出院记录")
    private String dischargeRecord;//最新出院记录
    @ApiModelProperty("专科医生")
    private String doctor;//专科医生
    @ApiModelProperty("专科医生姓名")
    private String doctorName;//专科医生姓名
    @ApiModelProperty("居民（患者）")
    private String patient;// 居民（患者）
    @ApiModelProperty("居民（患者）姓名")
    private String patientName;//居民（患者）姓名
    @ApiModelProperty("1.已经分配，0，待分配")
    private String status;//1.已经分配，0，待分配
    @ApiModelProperty("签约code")
    private String signCode;//签约code
    @ApiModelProperty("签约医生")
    private String signDoctor;//签约医生
    @ApiModelProperty("签约医生")
    private String signDoctorName;//签约医生
    @ApiModelProperty("健康管理师")
    private String healthDoctor;//健康管理师
    @ApiModelProperty("健康管理师姓名")
    private String healthDoctorName;//健康管理师姓名
    @ApiModelProperty("签约年度")
    private String signYear;//签约年度
    @ApiModelProperty("签约团队")
    private Integer teamCode;//签约团队
    @ApiModelProperty("签约日期")
    private Date signDate;//签约日期

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getDischargeRecord() {
        return dischargeRecord;
    }

    public void setDischargeRecord(String dischargeRecord) {
        this.dischargeRecord = dischargeRecord;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public String getSignDoctor() {
        return signDoctor;
    }

    public void setSignDoctor(String signDoctor) {
        this.signDoctor = signDoctor;
    }

    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
    }

    public String getHealthDoctor() {
        return healthDoctor;
    }

    public void setHealthDoctor(String healthDoctor) {
        this.healthDoctor = healthDoctor;
    }

    public String getHealthDoctorName() {
        return healthDoctorName;
    }

    public void setHealthDoctorName(String healthDoctorName) {
        this.healthDoctorName = healthDoctorName;
    }

    public String getSignYear() {
        return signYear;
    }

    public void setSignYear(String signYear) {
        this.signYear = signYear;
    }

    public Integer getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Integer teamCode) {
        this.teamCode = teamCode;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
