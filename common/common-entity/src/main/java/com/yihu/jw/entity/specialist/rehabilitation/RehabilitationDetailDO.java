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
@Table(name = "wlyy_rehabilitation_plan_detail")
public class RehabilitationDetailDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "plan_id")
    private String planId;//居民康复套餐code
    @Column(name = "hospital_service_item_id")
    private String hospitalServiceItemId;//机构服务项目id
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
    private Date executeTime;//服务项目执行时间
    @Column(name = "status")
    private Integer status;//状态（0未完成，1已完成，2已预约）
    @Column(name = "expense")
    private Integer expense;

    @Column(name = "saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "plan_id")
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Column(name = "hospital_service_item_id")
    public String getHospitalServiceItemId() {
        return hospitalServiceItemId;
    }

    public void setHospitalServiceItemId(String hospitalServiceItemId) {
        this.hospitalServiceItemId = hospitalServiceItemId;
    }

    @Column(name = "hospital")
    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Column(name = "execute_time")
    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "hospital_name")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    @Column(name = "expense")
    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }
}
