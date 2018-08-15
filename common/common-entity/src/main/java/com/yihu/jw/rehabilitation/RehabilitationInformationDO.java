package com.yihu.jw.rehabilitation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 就诊信息表
 * @author humingfen on 2018/4/25.
 */
@Entity
@Table(name = "rehabilitation_information")
public class RehabilitationInformationDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "patient_id")
    private String patientId;//居民id
    @Column(name = "hospital")
    private String hospital;//就诊医院
    @Column(name = "departmen")
    private String departmen;//就诊科室
    @Column(name = "summary")
    private String summary;//诊断小结
    @Column(name = "advice")
    private String advice;//医嘱
    @Column(name = "disease")
    private String disease;//疾病标签
    @Column(name = "discharge_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date dischargeTime;//出院时间

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartmen() {
        return departmen;
    }

    public void setDepartmen(String departmen) {
        this.departmen = departmen;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(Date dischargeTime) {
        this.dischargeTime = dischargeTime;
    }
}
