package com.yihu.jw.entity.health.bank;

import com.yihu.jw.IdEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wang zhinan on 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_activity_rule")
public class ActivityRuleDO extends IdEntityWithOperation implements Serializable {
    @Column(name = "saas_id")
    private String saasId;//saasid

    @Column(name = "activity_id")
    private String activityId;//活动id

    @Column(name = "rule_code")
    private String ruleCode;//规则代码

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }
}
