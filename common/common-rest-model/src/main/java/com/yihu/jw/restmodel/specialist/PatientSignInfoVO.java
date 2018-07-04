package com.yihu.jw.restmodel.specialist;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
