package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Trick on 2018/7/4.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "专科医生团队与居民匹配关系", description = "专科医生团队与居民匹配关系")
public class SpecialistTeamVO {

    @ApiModelProperty("签约code")
    private String relationCode;
    @ApiModelProperty("居民code")
    private String patient;
    @ApiModelProperty("居民姓名")
    private String patientName;
    @ApiModelProperty("团队id")
    private Long teamCode;
    @ApiModelProperty("团队名称")
    private String name;
    @ApiModelProperty("医生头像")
    private String photo;
    @ApiModelProperty("医生code")
    private String doctor;
    @ApiModelProperty("医生")
    private String doctorName;
    @ApiModelProperty("科室code")
    private String dept;
    @ApiModelProperty("科室")
    private String deptName;
    @ApiModelProperty("社区code")
    private String hospital;
    @ApiModelProperty("社区")
    private String hospitalName;
    @ApiModelProperty("医生团队成员")
    private List<AdminTeamMemberVO> members;


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

    public Long getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Long teamCode) {
        this.teamCode = teamCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }

    public List<AdminTeamMemberVO> getMembers() {
        return members;
    }

    public void setMembers(List<AdminTeamMemberVO> members) {
        this.members = members;
    }
}
