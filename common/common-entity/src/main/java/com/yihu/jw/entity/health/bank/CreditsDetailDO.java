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
    private String tradeType; //交易类型交易类型:普通任务(NORMAL_TASK）、活动任务(ACTIVITY_TASK)、兑换商品(EXCHANGE_GOODS)

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

    @Column(name = "description")
    private String description;//积分获取说明

    @Transient
    private TaskDO taskDO;//任务对象

    @Transient
    private String activityId;//活动ID

    @Transient
    private ActivityDO activityDO;//活动对象

    @Transient
    private String flag ; // 标识是什么任务

    @Transient
    private Integer total;  //总积分

    @Transient
    private String name; //居民名称

    @Transient
    private String idCard; //身份证号码

    @Transient
    private String openId;//微信编号

    @Transient
    private Long stepNumber;//步数

    @Transient
    private String unionId;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Long stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
