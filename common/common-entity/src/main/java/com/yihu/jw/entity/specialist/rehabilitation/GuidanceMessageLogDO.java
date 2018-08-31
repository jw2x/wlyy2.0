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

    private String saasId;
    private String messageId;
    private String planDetailId;
    private String content;
    private String doctor;
    private String doctorName;
    private Integer doctorType;
    private Integer adminTeamCode;
    private String adminTeamName;

    @Column(name = "doctor")
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "doctor_type")
    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }

    @Column(name = "admin_team_code")
    public Integer getAdminTeamCode() {
        return adminTeamCode;
    }

    public void setAdminTeamCode(Integer adminTeamCode) {
        this.adminTeamCode = adminTeamCode;
    }

    public String getAdminTeamName() {
        return adminTeamName;
    }

    @Column(name = "admin_team_name")
    public void setAdminTeamName(String adminTeamName) {
        this.adminTeamName = adminTeamName;
    }

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "plan_detail_id")
    public String getPlanDetailId() {
        return planDetailId;
    }

    public void setPlanDetailId(String planDetailId) {
        this.planDetailId = planDetailId;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "message_id")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
