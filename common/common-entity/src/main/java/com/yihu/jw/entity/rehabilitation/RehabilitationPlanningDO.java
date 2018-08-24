package com.yihu.jw.entity.rehabilitation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 康复计划表
 * @author humingfen on 2018/4/25.
 */
@Entity
@Table(name = "rehabilitation_planning")
public class RehabilitationPlanningDO extends UuidIdentityEntityWithOperator implements Serializable {

    @Column(name = "saas_id")
    private String saasId;
    @Column(name = "patient_id")
    private String patientId;//居民ID
    @Column(name = "program_id")
    private String programId;//治疗方案ID,可多个用逗号隔开
    @Column(name = "status")
    private Integer status;//计划执行状态，0执行1终止
    @Column(name = "recheck_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date recheckTime;//复检时间
    @Column(name = "description")
    private String description;//附加说明，如先执行哪个方案，什么时间执行最佳

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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRecheckTime() {
        return recheckTime;
    }

    public void setRecheckTime(Date recheckTime) {
        this.recheckTime = recheckTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
