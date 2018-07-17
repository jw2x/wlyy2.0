package com.yihu.jw.entity.health.bank;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wang zhinan on 2018/4/26.
 */
@Entity
@Table(name = "wlyy_health_bank_account")
public class AccountDO extends IdEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId; //saasId

    @Column(name = "patient_id")
    private String patientId;//居民id

    @Column(name = "account_name")
    private String accountName;//账户名

    @Column(name = "total")
    private Integer total;//积分总数

    @Column(name = "password")
    private String password;//密码

    @Column(name = "card_number")
    private String cardNumber;//卡号

    @Column(name = "hospital_name")
    private String hospitalName;//社区名字

    @Column(name = "hospital")
    private String hospital;//社区

    @Column(name = "status")
    private Integer status;//状态（1有效，-1失效）

    @Transient
    private String idCard;//身份证号码

    @Transient
    private Long sum;//总积分（已用积分和剩余积分）

    @Transient
    private Integer usedTotal;//已用积分

    @Transient
    private Long nowTotal;//今日获取积分

    @Transient
    private Long activityTotal;//参与活动数;

    @Transient
    private Long taskTotal;//参与任务数;

    @Transient
    private Integer teamRanking;//团队排名

    @Transient
    private Integer cityRanking;//全市排名

    @Transient
    private List<String> patientIds;//团队居民id

    @Transient
    private String taskId;//任务id

    @Transient
    private Long activityRanking;//活动排名

    @Transient
    private Long activityIntegrate;//活动积分

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Integer getUsedTotal() {
        return usedTotal;
    }

    public void setUsedTotal(Integer usedTotal) {
        this.usedTotal = usedTotal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getNowTotal() {
        return nowTotal;
    }

    public void setNowTotal(Long nowTotal) {
        this.nowTotal = nowTotal;
    }

    public Long getActivityTotal() {
        return activityTotal;
    }

    public void setActivityTotal(Long activityTotal) {
        this.activityTotal = activityTotal;
    }

    public Long getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(Long taskTotal) {
        this.taskTotal = taskTotal;
    }

    public Integer getTeamRanking() {
        return teamRanking;
    }

    public void setTeamRanking(Integer teamRanking) {
        this.teamRanking = teamRanking;
    }

    public Integer getCityRanking() {
        return cityRanking;
    }

    public void setCityRanking(Integer cityRanking) {
        this.cityRanking = cityRanking;
    }

    public List<String> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<String> patientIds) {
        this.patientIds = patientIds;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getActivityRanking() {
        return activityRanking;
    }

    public void setActivityRanking(Long activityRanking) {
        this.activityRanking = activityRanking;
    }

    public Long getActivityIntegrate() {
        return activityIntegrate;
    }

    public void setActivityIntegrate(Long activityIntegrate) {
        this.activityIntegrate = activityIntegrate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
