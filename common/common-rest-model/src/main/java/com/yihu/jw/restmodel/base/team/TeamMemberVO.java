package com.yihu.jw.restmodel.base.team;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Trick on 2018/8/31.
 */
@ApiModel(value = "TeamMemberVO", description = "团队成员")
public class TeamMemberVO {

    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "团队ID")
    private String teamId;//团队ID',
    @ApiModelProperty(value = "机构标识")
    private String orgId;//机构标识',
    @ApiModelProperty(value = "医生标识，多个医生以逗号分开")
    private String doctorId;//医生标识，多个医生以逗号分开',
    @ApiModelProperty(value = "作废标识，1正常，0作废'")
    private String del;//作废标识，1正常，0作废',

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
