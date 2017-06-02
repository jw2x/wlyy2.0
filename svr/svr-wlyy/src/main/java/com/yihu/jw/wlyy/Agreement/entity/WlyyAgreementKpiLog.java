package com.yihu.jw.wlyy.Agreement.entity;


import com.yihu.jw.wlyy.Agreement.entity.base.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/6/1 0001.
 */
@Entity
@Table(name = "wlyy_agreement_kpi_log")
public class WlyyAgreementKpiLog extends IdEntity {
    private String code;//业务code
    private String patientCode;//患者code
    private String signCode;
    private String kpiCode;
    private String agreementCode;//套餐代码
    private String kpiName;//服务项名称
    private Timestamp createTime;
    private String createUser;


    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

}
