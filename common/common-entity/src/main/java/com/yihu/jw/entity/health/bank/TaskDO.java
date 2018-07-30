package com.yihu.jw.entity.health.bank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wamg zhinan 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_task")
public class TaskDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId; //saasid

    @Column(name = "title")
    private String title; //任务标题

    @Column(name = "content")
    private String content; //任务内容

    @Column(name = "type")
    private String type; //交易类型

    @Column(name = "transaction_id")
    private String transactionId; // 业务id

    @Column(name = "period")
    private Integer period; //周期性

    @Column(name = "task_code")
    private String taskCode ; // 标识是什么任务

    @Column(name = "status")
    private Integer status; //状态

    @Column(name = "rule_code")
    private String ruleCode;//规则code

    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;//开始时间

    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date endTime; //结束时间

    @Transient
    private ActivityDO activityDO;//活动详情
    @Transient
    private String patientId;//居民id
    @Transient
    private String openId;//微信编码
    @Transient
    private Long total;//参加总数
    @Transient
    private List<TaskPatientDetailDO> taskPatientDetailDOS;//参与人详情
    @Transient
    private String ruleName;//规则名称

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ActivityDO getActivityDO() {
        return activityDO;
    }

    public void setActivityDO(ActivityDO activityDO) {
        this.activityDO = activityDO;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<TaskPatientDetailDO> getTaskPatientDetailDOS() {
        return taskPatientDetailDOS;
    }

    public void setTaskPatientDetailDOS(List<TaskPatientDetailDO> taskPatientDetailDOS) {
        this.taskPatientDetailDOS = taskPatientDetailDOS;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
