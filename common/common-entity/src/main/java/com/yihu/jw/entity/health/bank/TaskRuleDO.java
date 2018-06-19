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
@Table(name = "wlyy_health_bank_task_rule")
public class TaskRuleDO extends IdEntityWithOperation implements Serializable{

    @Column(name = "saas_id")
    private String saasId; //saasid

    @Column(name = "name")
    private String name;//规则名称

    @Column(name = "description")
    private String description;//规则描述

    @Column(name = "integrate")
    private int integrate; //积分

    @Column(name = "trade_direction")
    private int tradeDirection;//交易方向 1增、-1减、0清零

    @Column(name = "type")
    private String type;//规则类型：NORMAL-手动,AUTO-自动

    @Column(name = "status")
    private int status; //状态

    @Column(name = "java_class_path")
    private String javaClassPath;//java反射类路径


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }
}
