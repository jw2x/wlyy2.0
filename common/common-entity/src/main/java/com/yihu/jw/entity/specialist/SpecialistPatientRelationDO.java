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

    private String saasId;
    private String dischargeRecord;//最新出院记录
    private String doctor;//专科医生
    private String doctorName;//专科医生姓名
    private String patient;// 居民（患者）
    private String patientName;//居民（患者）姓名
    private String healthAssistant;//计管师
    private String healthAssistantName;//计管师
    private String status;//1.已经分配，0，待分配
    private String pkCode; //服务包code
    private String signCode;//签约code
    private String signDoctor;//签约医生
    private String signDoctorName;//签约医生
    private String healthDoctor;//健康管理师
    private String healthDoctorName;//健康管理师姓名
    private String signYear;//签约年度
    private Integer teamCode;//签约团队
    private Date signDate;//签约日期

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "discharge_record")
    public String getDischargeRecord() {
        return dischargeRecord;
    }

    public void setDischargeRecord(String dischargeRecord) {
        this.dischargeRecord = dischargeRecord;
    }

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

    @Column(name = "patient")
    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Column(name = "patient_name")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "sign_code")
    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    @Column(name = "sign_doctor")
    public String getSignDoctor() {
        return signDoctor;
    }

    public void setSignDoctor(String signDoctor) {
        this.signDoctor = signDoctor;
    }

    @Column(name = "sign_doctor_name")
    public String getSignDoctorName() {
        return signDoctorName;
    }

    public void setSignDoctorName(String signDoctorName) {
        this.signDoctorName = signDoctorName;
    }

    @Column(name = "health_doctor")
    public String getHealthDoctor() {
        return healthDoctor;
    }

    public void setHealthDoctor(String healthDoctor) {
        this.healthDoctor = healthDoctor;
    }

    @Column(name = "health_doctor_name")
    public String getHealthDoctorName() {
        return healthDoctorName;
    }

    public void setHealthDoctorName(String healthDoctorName) {
        this.healthDoctorName = healthDoctorName;
    }

    @Column(name = "sign_year")
    public String getSignYear() {
        return signYear;
    }

    public void setSignYear(String signYear) {
        this.signYear = signYear;
    }



    @Column(name = "sign_date")
    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    @Column(name = "team_code")
    public Integer getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Integer teamCode) {
        this.teamCode = teamCode;
    }

    @Column(name = "health_assistant")
    public String getHealthAssistant() {
        return healthAssistant;
    }

    public void setHealthAssistant(String healthAssistant) {
        this.healthAssistant = healthAssistant;
    }

    @Column(name = "health_assistant_name")
    public String getHealthAssistantName() {
        return healthAssistantName;
    }

    public void setHealthAssistantName(String healthAssistantName) {
        this.healthAssistantName = healthAssistantName;
    }

    @Column(name = "pk_code")
    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }
}
