package com.yihu.jw.entity.specialist.rehabilitation;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 刘文彬 on 2018/8/29.
 */
@Entity
@Table(name = "wlyy_rehabilitation_operate_records")
public class RehabilitationOperateRecordsDO extends UuidIdentityEntityWithOperator implements Serializable {
    private String saasId;
    private String rehabilitationDetailId;//康复计划明细表id
    private String patientCode;//居民code
    private String patientName;//居民名称
    private String doctorCode;//执行医生code
    private String doctorName;//执行医生名称
    private Integer relationRecordType;//关联记录类型（1、随访记录，2、健康指导，3、健康教育）
    private String relationRecordCode;//关联记录code
    private Date reserveTime;//服务预定完成时间
    private Date completeTime;//服务完成时间
    private Integer status;//是否确认完成（0、未确认，1、已确认）

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "rehabilitation_detail_id")
    public String getRehabilitationDetailId() {
        return rehabilitationDetailId;
    }

    public void setRehabilitationDetailId(String rehabilitationDetailId) {
        this.rehabilitationDetailId = rehabilitationDetailId;
    }

    @Column(name = "patient_code")
    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    @Column(name = "patient_name")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Column(name = "doctor_code")
    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    @Column(name = "doctor_name")
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Column(name = "relation_record_type")
    public Integer getRelationRecordType() {
        return relationRecordType;
    }

    public void setRelationRecordType(Integer relationRecordType) {
        this.relationRecordType = relationRecordType;
    }

    @Column(name = "relation_record_code")
    public String getRelationRecordCode() {
        return relationRecordCode;
    }

    public void setRelationRecordCode(String relationRecordCode) {
        this.relationRecordCode = relationRecordCode;
    }

    @Column(name = "reserve_time")
    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    @Column(name = "complete_time")
    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
