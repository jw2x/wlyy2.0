package com.yihu.jw.restmodel.specialist;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Trick on 2018/7/4.
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
@ApiModel(value = "专科医生团队与居民匹配关系", description = "专科医生团队与居民匹配关系")
public class SpecialistTeamVO {

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

    public List<AdminTeamMemberVO> getMembers() {
        return members;
    }

    public void setMembers(List<AdminTeamMemberVO> members) {
        this.members = members;
    }
}
