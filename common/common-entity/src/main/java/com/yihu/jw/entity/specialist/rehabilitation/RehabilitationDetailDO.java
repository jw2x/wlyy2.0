package com.yihu.jw.entity.specialist.rehabilitation;


import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by humingfen on 2018/8/15.
 */
@Entity
@Table(name = "wlyy_rehabilitation_detail")
public class RehabilitationDetailDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "program_id")
    private String programId;//居民康复套餐code
    @Column(name = "service_item_id")
    private String serviceItemId;//服务项目id
    @Column(name = "hospital")
    private String hospital;//医院code
    @Column(name = "hospital_name")
    private String hospitalName;//医院名称
    @Column(name = "type")
    private Integer type;//执行者类型（1家医，2行政团队）
    @Column(name = "doctor")
    private String doctor;//计划完成者标识
    @Column(name = "doctor_name")
    private String doctorName;//计划完成者标识
    @Column(name = "execute_time")
    private String executeTime;//服务项目执行时间
    @Column(name = "status")
    private Integer status;//状态（0未开始，1进行中，2已完成）

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getServiceItemId() {
        return serviceItemId;
    }

    public void setServiceItemId(String serviceItemId) {
        this.serviceItemId = serviceItemId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}
