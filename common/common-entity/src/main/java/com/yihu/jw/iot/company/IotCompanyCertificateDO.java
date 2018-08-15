package com.yihu.jw.iot.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yihu.jw.UuidIdentityEntityWithOperation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业证书表
 * @author yeshijie on 2018/1/16.
 */
@Entity
@Table(name = "iot_company_certificate")
public class IotCompanyCertificateDO extends UuidIdentityEntityWithOperation implements Serializable {

    @Column(name = "saas_id")
    private String saasId;//
    @Column(name = "name")
    private String name;//证书名称
    @Column(name = "manufacturer_name")
    private String manufacturerName;//生产厂家名称
    @Column(name = "manufacturer_id")
    private String manufacturerId;//生产厂家id
    @Column(name = "manufacturer_business_license")
    private String manufacturerBusinessLicense;//生产厂家营业执照号
    @Column(name = "launch_company_name")
    private String launchCompanyName;//发起企业名称
    @Column(name = "launch_company_id")
    private String launchCompanyId;//发起企业id
    @Column(name = "launch_company_business_license")
    private String launchCompanyBusinessLicense;//发起企业营业执照号
    @Column(name = "company_name")
    private String companyName;//归属企业名称
    @Column(name = "company_id")
    private String companyId;//归属企业id
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;//有效期开始时间
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date endTime;//有效结束时间
    @Column(name = "certificate_of_authorization_img")
    private String certificateOfAuthorizationImg;//授权书扫描件
    @Column(name = "del")
    private Integer del;//删除标志

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

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerBusinessLicense() {
        return manufacturerBusinessLicense;
    }

    public void setManufacturerBusinessLicense(String manufacturerBusinessLicense) {
        this.manufacturerBusinessLicense = manufacturerBusinessLicense;
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

    public String getLaunchCompanyName() {
        return launchCompanyName;
    }

    public void setLaunchCompanyName(String launchCompanyName) {
        this.launchCompanyName = launchCompanyName;
    }

    public String getLaunchCompanyId() {
        return launchCompanyId;
    }

    public void setLaunchCompanyId(String launchCompanyId) {
        this.launchCompanyId = launchCompanyId;
    }

    public String getLaunchCompanyBusinessLicense() {
        return launchCompanyBusinessLicense;
    }

    public void setLaunchCompanyBusinessLicense(String launchCompanyBusinessLicense) {
        this.launchCompanyBusinessLicense = launchCompanyBusinessLicense;
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

    public String getCertificateOfAuthorizationImg() {
        return certificateOfAuthorizationImg;
    }

    public void setCertificateOfAuthorizationImg(String certificateOfAuthorizationImg) {
        this.certificateOfAuthorizationImg = certificateOfAuthorizationImg;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
