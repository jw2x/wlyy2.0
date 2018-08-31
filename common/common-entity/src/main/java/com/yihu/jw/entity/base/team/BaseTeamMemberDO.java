package com.yihu.jw.entity.base.team;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Trick on 2018/8/31.
 */
@Entity
@Table(name = "base_team_member")
public class BaseTeamMemberDO extends UuidIdentityEntityWithOperator {

    private String teamId;//团队ID',
    private String orgId;//机构标识',
    private String doctorId;//医生标识，多个医生以逗号分开',
    private String del;//作废标识，1正常，0作废',

    @Column(name = "team_id")
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Column(name = "doctor_id")
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Column(name = "del")
    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
