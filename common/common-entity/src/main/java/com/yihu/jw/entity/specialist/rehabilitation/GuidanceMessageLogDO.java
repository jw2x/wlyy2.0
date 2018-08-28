package com.yihu.jw.entity.specialist.rehabilitation;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 刘文彬 on 2018/8/23.
 */
@Entity
@Table(name = "wlyy_guidance_message_log")
public class GuidanceMessageLogDO extends UuidIdentityEntityWithOperator implements Serializable {
    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "plan_detail_id")
    private String planDetailId;
    @Column(name = "content")
    private String content;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "doctor_type")
    private Integer doctorType;
    @Column(name = "admin_team_code")
    private String adminTeamCode;
    @Column(name = "admin_team_name")
    private String adminTeamName;

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

    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }

    public String getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(String adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    public String getAdminTeamName() {
        return adminTeamName;
    }

    public void setAdminTeamName(String adminTeamName) {
        this.adminTeamName = adminTeamName;
    }

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(String planDetailId) {
        this.planDetailId = planDetailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
