package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Trick on 2018/7/4.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "居民专病签约信息", description = "居民专病签约信息")
public class PatientSignInfoVO {

    @ApiModelProperty("签约Code")
    private String relationCode;
    @ApiModelProperty("居民code")
    private String patient;
    @ApiModelProperty("居民")
    private String patientName;
    @ApiModelProperty("医生code")
    private String doctor;
    @ApiModelProperty("医生")
    private String doctorName;
    @ApiModelProperty("社区")
    private String hospital;
    @ApiModelProperty("社区名称")
    private String hospitalName;
    @ApiModelProperty("头像")
    private String photo;
    @ApiModelProperty("科室code")
    private String dept;
    @ApiModelProperty("科室")
    private String deptName;
    @ApiModelProperty("团队")
    private String teamName;
    @ApiModelProperty("团队code")
    private Long teamCode;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("签约状态")
    private String signStatus;
    @ApiModelProperty("记录状态")
    private String status;
    @ApiModelProperty("医生角色")
    private String level;
    @ApiModelProperty("计管师code")
    private String healthAssistant;
    @ApiModelProperty("计管师")
    private String healthAssistantName;

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Long teamCode) {
        this.teamCode = teamCode;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHealthAssistant() {
        return healthAssistant;
    }

    public void setHealthAssistant(String healthAssistant) {
        this.healthAssistant = healthAssistant;
    }

    public String getHealthAssistantName() {
        return healthAssistantName;
    }

    public void setHealthAssistantName(String healthAssistantName) {
        this.healthAssistantName = healthAssistantName;
    }
}
