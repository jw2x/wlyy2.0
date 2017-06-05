package com.yihu.jw.quota.model.jpa.rule;// default package

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import javax.transaction.Transactional;

/**
 * TjQuotaRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tj_quota_rule")
public class TjQuotaRule implements java.io.Serializable {

    // Fields

    private Integer id;
    private String quotaCode;
    private String ruleCode;
    private String type;// 1数据非空

    // Constructors

    /**
     * default constructor
     */
    public TjQuotaRule() {
    }

    /**
     * full constructor
     */
    public TjQuotaRule(String quotaCode, String ruleCode) {
        this.quotaCode = quotaCode;
        this.ruleCode = ruleCode;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "quota_code", length = 100)
    public String getQuotaCode() {
        return this.quotaCode;
    }

    public void setQuotaCode(String quotaCode) {
        this.quotaCode = quotaCode;
    }

    @Column(name = "rule_code", length = 100)
    public String getRuleCode() {
        return this.ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    @Transient
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}