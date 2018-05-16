package com.yihu.jw.entity.health.bank;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by wang zhinan on 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_credits_detail")
public class CreditsDetailDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId; //saasid

    @Column(name = "trade_type")
    private String tradeType; //交易类型

    @Column(name = "transaction_id")
    private String transactionId;//业务ID

    @Column(name="integrate")
    private int integrate;//积分

    @Column(name = "status")
    private int status;//状态（有效/无效）

    @Column(name = "trade_direction")
    private int tradeDirection;//交易方向

    @Column(name = "account_id")
    private String accountId;//账户id

    @Column(name = "patient_id")
    private String patientId;//居民id

    @Column(name = "hospital")
    private String hospital;//社区

    @Transient
    private TaskDO taskDO;//任务对象

    @Transient
    private ActivityDO activityDO;//活动对象

    @Transient
    private String flag ; // 标识是什么任务

    @Transient
    private Integer total;  //总积分

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public int getIntegrate() {
        return integrate;
    }

    public void setIntegrate(int integrate) {
        this.integrate = integrate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public int getTradeDirection() {
        return tradeDirection;
    }

    public void setTradeDirection(int tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public TaskDO getTaskDO() {
        return taskDO;
    }

    public void setTaskDO(TaskDO taskDO) {
        this.taskDO = taskDO;
    }

    public ActivityDO getActivityDO() {
        return activityDO;
    }

    public void setActivityDO(ActivityDO activityDO) {
        this.activityDO = activityDO;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
