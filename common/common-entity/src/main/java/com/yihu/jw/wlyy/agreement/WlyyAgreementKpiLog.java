package com.yihu.jw.wlyy.agreement;


import com.yihu.jw.IdEntity;
import com.yihu.jw.IdEntityWithOperation;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Entity
@Table(name = "wlyy_agreement_kpi_log")
public class WlyyAgreementKpiLog extends IdEntity {

    private static final long serialVersionUID = -3196907595969778396L;
    private String saasId;
    private String patientCode;//患者code
    private String signCode;
    private String kpiCode;
    private String agreementCode;//套餐代码
    private String kpiName;//服务项名称

    @CreatedDate
    @Column(name = "create_time", nullable = false, length = 0,updatable = false)
    protected Date createTime;

    @CreatedBy
    @Column(name = "create_user",updatable = false)
    protected String createUser;


    @Column(name="saas_id")
    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    @Column(name = "patient_code")
    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    @Column(name = "sign_code")
    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    @Column(name = "kpi_code")
    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    @Column(name = "agreement_code")
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
