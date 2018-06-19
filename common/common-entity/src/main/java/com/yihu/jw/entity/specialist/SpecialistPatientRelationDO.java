package com.yihu.jw.entity.specialist;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Trick on 2018/4/24.
 */
@Entity
@Table(name = "wlyy_specialist_patient_relation")
public class SpecialistPatientRelationDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "discharge_record")
    private String dischargeRecord;//最新出院记录

    @Column(name = "doctor")
    private String doctor;//专科医生

    @Column(name = "doctor_name")
    private String doctorName;//专科医生姓名

    @Column(name = "patient")
    private String patient;// 居民（患者）

    @Column(name = "patient_name")
    private String patientName;//居民（患者）姓名

    @Column(name = "health_assistant")
    private String healthAssistant;//计管师

    @Column(name = "health_assistant_name")
    private String healthAssistantName;//计管师

    @Column(name = "status")
    private String status;//1.已经分配，0，待分配

    @Column(name = "pk_code")
    private String pkCode; //服务包code

    @Column(name = "sign_code")
    private String signCode;//签约code

    @Column(name = "sign_doctor")
    private String signDoctor;//签约医生

    @Column(name = "sign_doctor_name")
    private String signDoctorName;//签约医生

    @Column(name = "health_doctor")
    private String healthDoctor;//健康管理师

    @Column(name = "health_doctor_name")
    private String healthDoctorName;//健康管理师姓名

    @Column(name = "sign_year")
    private String signYear;//签约年度

    @Column(name = "team_code")
    private Integer teamCode;//签约团队

    @Column(name = "sign_date")
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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Integer teamCode) {
        this.teamCode = teamCode;
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

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }
}
