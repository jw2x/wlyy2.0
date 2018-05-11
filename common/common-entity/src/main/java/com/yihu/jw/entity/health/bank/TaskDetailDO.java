package com.yihu.jw.entity.health.bank;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wamg zhinan 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_task_detail")
public class TaskDetailDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId;

    @Column(name = "status")
    private String status;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "doctor_id")
    private String doctorId;

    @Column(name = "integrate")
    private int integrate;

    @Column(name = "trade_direction")
    private int tradeDirection;

    @Column(name = "task_id")
    private String taskId;

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public int getIntegrate() {
        return integrate;
    }

    public void setIntegrate(int integrate) {
        this.integrate = integrate;
    }

    public int getTradeDirection() {
        return tradeDirection;
    }

    public void setTradeDirection(int tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
