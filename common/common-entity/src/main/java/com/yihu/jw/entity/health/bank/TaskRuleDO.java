package com.yihu.jw.entity.health.bank;

import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wamg zhinan 2018/4/27.
 */
@Entity
@Table(name = "wlyy_health_bank_task_rule")
public class TaskRuleDO extends UuidIdentityEntityWithOperator implements Serializable{

    @Column(name = "saas_id")
    private String saasId; //saasid

    @Column(name = "name")
    private String name;//规则名称

    @Column(name = "description")
    private String description;//规则描述

    @Column(name = "integrate")
    private Integer integrate; //积分

    @Column(name = "trade_direction")
    private Integer tradeDirection;//交易方向 1增、-1减、0清零

    @Column(name = "type")
    private String type;//规则类型：NORMAL-手动,AUTO-自动

    @Column(name = "status")
    private Integer status; //状态

    @Column(name = "java_class_path")
    private String javaClassPath;//java反射类路径

    @Column(name = "period")
    private Integer period;//周期性


    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntegrate() {
        return integrate;
    }

    public void setIntegrate(Integer integrate) {
        this.integrate = integrate;
    }

    public Integer getTradeDirection() {
        return tradeDirection;
    }

    public void setTradeDirection(Integer tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
