package com.yihu.jw.restmodel.iot.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业证书表
 * @author yeshijie on 2018/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "ListResult", description = "企业证书表")
public class IotCompanyCertificateVO implements Serializable {

    @ApiModelProperty("证书名称")
    private String name;
    @ApiModelProperty("生产厂家名称")
    private String manufacturerName;
    @ApiModelProperty("生产厂家id")
    private String manufacturerId;
    @ApiModelProperty("生产厂家营业执照号")
    private String manufacturerBusinessLicense;
    @ApiModelProperty("发起企业名称")
    private String companyName;
    @ApiModelProperty("发起企业id")
    private String companyId;
    @ApiModelProperty("发起企业营业执照号")
    private String companyBusinessLicense;
    @ApiModelProperty("有效期开始时间")
    private Date startTime;
    @ApiModelProperty("有效结束时间")
    private Date endTime;
    @ApiModelProperty("授权书扫描件")
    private String certificateOfAuthorizationImg;
    @ApiModelProperty("删除标志")
    private Integer del;

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

    public String getCompanyBusinessLicense() {
        return companyBusinessLicense;
    }

    public void setCompanyBusinessLicense(String companyBusinessLicense) {
        this.companyBusinessLicense = companyBusinessLicense;
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
