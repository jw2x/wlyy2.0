package com.yihu.jw.entity.wlyy.agreement;



import com.yihu.jw.entity.UuidIdentityEntityWithOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Entity
@Table(name = "wlyy_agreement_kpi")
public class WlyyAgreementKpiDO extends UuidIdentityEntityWithOperator {
    private String saasId;//saasId
    private String agreementCode;//套餐代码
    private String kpiName;//服务项名称
    private String type;//服务项类型
    private String kpiTimes;//服务次数
    private Integer status;//状态  -1删除 0 冻结 1可用
    private String kpiContent;//服务内容描述
    private String keyword;//关键字

    @Column(name="saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name="agreement_code")
    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }


    @Column(name = "kpi_name")
    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "kpi_times")
    public String getKpiTimes() {
        return kpiTimes;
    }

    public void setKpiTimes(String kpiTimes) {
        this.kpiTimes = kpiTimes;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "kpi_content")
    public String getKpiContent() {
        return kpiContent;
    }

    public void setKpiContent(String kpiContent) {
        this.kpiContent = kpiContent;
    }

    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
