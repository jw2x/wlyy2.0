package com.yihu.jw.entity.health.bank;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by wamg zhinan 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_task_patient_detail")
public class TaskPatientDetailDO extends UuidIdentityEntityWithOperator implements Serializable{

    @Column(name = "saas_id")
    private String saasId; //saasid

    @Column(name = "status")
    private Integer status;//任务完整状态：1完成，0参与，-1作废

    @Column(name = "patient_id")
    private String patientId;//居民id

    @Column(name = "patient_idcard")
    private String patientIdcard;//居民身份证

    @Column(name = "patient_openid")
    private String patientOpenid;//居民openid

    @Column(name = "doctor_id")
    private String doctorId;//医生id

    @Column(name = "task_id")
    private String taskId;//任务id

    @Column(name = "activity_id")
    private String activityId;//活动id

    @Column(name = "total")
    private Long total;//活动中获取的积分

    @Column(name = "union_id")
    private String unionId;

    @Transient
    private AccountDO accountDO;//账户信息

    @Transient
    private Integer isFlag;//标识是否为当前用户

    @Transient
    private String taskCode;//任务标识

    @Transient
    private String name;//居民名称

    @Transient
    private String hospital;//社区

    @Transient
    private String hospitalName;//社区名称

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientIdcard() {
        return patientIdcard;
    }

    public void setPatientIdcard(String patientIdcard) {
        this.patientIdcard = patientIdcard;
    }

    public String getPatientOpenid() {
        return patientOpenid;
    }

    public void setPatientOpenid(String patientOpenid) {
        this.patientOpenid = patientOpenid;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public AccountDO getAccountDO() {
        return accountDO;
    }

    public void setAccountDO(AccountDO accountDO) {
        this.accountDO = accountDO;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
