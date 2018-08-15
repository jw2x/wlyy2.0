package com.yihu.jw.iot.company;

import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 企业三证变更记录表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_company_certificate_change_record")
public class IotCompanyCertificateChangeRecordDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "company_name")
    private String companyName;//企业名称
    @Column(name = "company_id")
    private String companyId;//企业id
    @Column(name = "type")
    private String type;//类型(1营业执照，2组织机构代码证，3税务登记证)
    @Column(name = "license_old")
    private String licenseOld;//原证书号码
    @Column(name = "certificate_old")
    private String certificateOld;//原证书扫描件
    @Column(name = "license_new")
    private String licenseNew;//新证书号码
    @Column(name = "certificate_new")
    private String certificateNew;//新证书扫描件

    public String getSaasId() {
        return saasId;
    }

    public void setSaasId(String saasId) {
        this.saasId = saasId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicenseOld() {
        return licenseOld;
    }

    public void setLicenseOld(String licenseOld) {
        this.licenseOld = licenseOld;
    }

    public String getCertificateOld() {
        return certificateOld;
    }

    public void setCertificateOld(String certificateOld) {
        this.certificateOld = certificateOld;
    }

    public String getLicenseNew() {
        return licenseNew;
    }

    public void setLicenseNew(String licenseNew) {
        this.licenseNew = licenseNew;
    }

    public String getCertificateNew() {
        return certificateNew;
    }

    public void setCertificateNew(String certificateNew) {
        this.certificateNew = certificateNew;
    }
}
